package avdw.java.captain.sonar.protocol;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.sun.xml.internal.ws.util.StringUtils;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Protocol {
    private EndPoint endPoint;
    private String basePackage;

    @Inject
    public Protocol(EndPoint endPoint) {
        this.endPoint = endPoint;
    }

    public void registerMessages() {
        Reflections reflections = new Reflections("avdw.java.captain.sonar.protocol");

        Kryo kryo = endPoint.getKryo();
        reflections.getTypesAnnotatedWith(Message.class).stream().forEach(aClass -> {
            kryo.register(aClass);
        });
    }

    public void registerListeners(Injector injector, String basePackage) {
        Reflections reflections = new Reflections(basePackage);

        reflections.getSubTypesOf(Listener.class).stream()
                .map(aClass -> injector.getInstance(aClass))
                .forEach(listener -> {
                    endPoint.addListener(listener);
                    Logger.debug(String.format("registered %s", listener));
                });
    }

    public static void generateListeners(String pckg) {
        Arrays.stream(new String[]{"captain", "engineer", "officer", "setup"}).forEach(role -> {
            MethodSpec.Builder configureBuilder = MethodSpec.methodBuilder("configure")
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC);

            Reflections reflections = new Reflections(String.format("avdw.java.captain.sonar.protocol.%s", role));
            reflections.getTypesAnnotatedWith(Message.class).stream().forEach(aClass -> {
                MethodSpec connected = MethodSpec.methodBuilder("connected")
                        .addAnnotation(Override.class)
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(Connection.class, "connection")
                        .addStatement("$T.debug(\"connected\")", Logger.class)
                        .build();

                MethodSpec received = MethodSpec.methodBuilder("received")
                        .addAnnotation(Override.class)
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(Connection.class, "connection")
                        .addParameter(Object.class, "object")
                        .beginControlFlow("if ($T.class.isInstance(object))", aClass)
                        .addStatement("$T.debug(\"received\")", Logger.class)
                        .addStatement("$T request = ($T) object", aClass, aClass)
                        .addStatement("connection.sendTCP(\"ack\")")
                        .endControlFlow()
                        .build();

                MethodSpec disconnected = MethodSpec.methodBuilder("disconnected")
                        .addAnnotation(Override.class)
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(Connection.class, "connection")
                        .addStatement("$T.debug(\"disconnected\")", Logger.class)
                        .build();

                TypeSpec listener = TypeSpec.classBuilder(String.format("%sListener", aClass.getAnnotation(Message.class).value()))
                        .addModifiers(Modifier.PUBLIC)
                        .superclass(Listener.class)
                        .addMethod(connected)
                        .addMethod(received)
                        .addMethod(disconnected)
                        .build();

                JavaFile javaFile = JavaFile.builder(String.format("%s.%s.listener", pckg, role), listener)
                        .indent("    ")
                        .build();
                configureBuilder.addStatement("bind($N.class).in($T.class)", javaFile.typeSpec, Singleton.class);

                try {
                    if (!Files.exists(Paths.get(String.format("src/main/java/%s",
                            javaFile.toJavaFileObject().getName())))) {
                        javaFile.writeTo(Paths.get("src/main/java/"));
                        Logger.info(String.format("created {src/main/java/%s}", javaFile.toJavaFileObject().getName()));
                    } else {
                        Logger.debug(String.format("file exists {src/main/java/%s}", javaFile.toJavaFileObject().getName()));
                    }
                } catch (IOException e) {
                    Logger.error(e);
                }
            });

            MethodSpec configure = configureBuilder.build();

            TypeSpec module = TypeSpec.classBuilder(String.format("%sListenerModule", StringUtils.capitalize(role)))
                    .addModifiers(Modifier.PUBLIC)
                    .superclass(AbstractModule.class)
                    .addMethod(configure)
                    .build();

            JavaFile moduleFile = JavaFile.builder(String.format("%s.%s.listener", pckg, role), module)
                    .indent("    ")
                    .build();

            try {
                moduleFile.writeTo(Paths.get("src/main/java/"));
                Logger.info(String.format("created {src/main/java/%s}", moduleFile.toJavaFileObject().getName()));
            } catch (IOException e) {
                Logger.error(e);
            }
        });
        Logger.info("done");
    }
}

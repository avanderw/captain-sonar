package avdw.java.captain.sonar.core.generator;

import avdw.java.captain.sonar.core.messages.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.apache.commons.lang3.StringUtils;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

import javax.annotation.Generated;
import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

class ListenerGenerator {
    public static void generate(String pckg) {
        Map<String, List<JavaFile>> roleListenerFiles = new HashMap();
        Reflections reflections = new Reflections(String.format("avdw.java.captain.sonar.core.messages"));
        reflections.getTypesAnnotatedWith(Message.class).stream().forEach(aClass -> {
            String role = aClass.getPackage().getName()
                    .replace("avdw.java.captain.sonar.core.messages.", "")
                    .replace(".message", "");

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
                    .addStatement("$T message = ($T) object", aClass, aClass)
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

            roleListenerFiles.putIfAbsent(role, new ArrayList());
            roleListenerFiles.get(role).add(javaFile);
        });

        roleListenerFiles.entrySet().stream().forEach(entry->{
            String role = entry.getKey();
            List<JavaFile> listenerFiles = entry.getValue();

            MethodSpec.Builder configureBuilder = MethodSpec.methodBuilder("configure")
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC);

            listenerFiles.stream().forEach(javaFile -> {
                configureBuilder.addStatement("bind($N.class).in($T.class)", javaFile.typeSpec, Singleton.class);
            });

            configureBuilder.addCode("\n");

            configureBuilder.addStatement("$T<$T> listenerBinder = $T.newSetBinder(binder(), $T.class)",
                    Multibinder.class, Listener.class, Multibinder.class, Listener.class);
            listenerFiles.stream().forEach(javaFile -> {
                configureBuilder.addStatement("listenerBinder.addBinding().to($N.class)", javaFile.typeSpec);
            });
            MethodSpec configure = configureBuilder.build();

            TypeSpec module = TypeSpec.classBuilder(String.format("%sListenerModule", StringUtils.capitalize(role)))
                    .addAnnotation(AnnotationSpec.builder(Generated.class)
                            .addMember("value", "\"$L\"", ListenerGenerator.class.getName())
                            .addMember("date", "\"$L\"", LocalDateTime.now().toString())
                            .build())
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

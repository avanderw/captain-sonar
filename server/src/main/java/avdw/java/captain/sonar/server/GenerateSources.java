package avdw.java.captain.sonar.server;

import avdw.java.captain.sonar.protocol.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class GenerateSources {
    public static void main(String[] args) {
        Configurator.currentConfig()
                .formatPattern("{date:yyyy-MM-dd HH:mm:ss} [{thread}] {class}.{method}() {level}: {message}")
                .level(Level.INFO)
                .activate();

        generateListeners();
        Logger.info("done");
    }

    private static void generateListeners() {
        Arrays.stream(new String[]{"captain", "engineer", "officer"}).forEach(role -> {
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
                        .beginControlFlow("if (object instanceof $T)", aClass)
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

                JavaFile javaFile = JavaFile.builder(String.format("%s.%s.listener", GenerateSources.class.getPackage().getName(), role), listener)
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
                    e.printStackTrace();
                }
            });
        });
        Logger.info("done");
    }
}

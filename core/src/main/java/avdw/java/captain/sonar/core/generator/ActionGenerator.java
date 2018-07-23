package avdw.java.captain.sonar.core.generator;

import avdw.java.captain.sonar.core.messages.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import com.squareup.javapoet.*;
import org.apache.commons.lang3.StringUtils;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

import javax.annotation.Generated;
import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ActionGenerator {
    public static void generate(String pckg) {
        Map<String, List<JavaFile>> roleActionFiles = new HashMap();
        Reflections reflections = new Reflections(String.format("avdw.java.captain.sonar.core.messages"));
        reflections.getTypesAnnotatedWith(Message.class).stream().forEach(aClass -> {
            String role = aClass.getPackage().getName()
                    .replace("avdw.java.captain.sonar.core.messages.", "")
                    .replace(".message", "");

            String message = aClass.getAnnotation(Message.class).value();

            MethodSpec constructor = MethodSpec.constructorBuilder()
                    .addAnnotation(Inject.class)
                    .addParameter(Connection.class, "connection")
                    .addParameter(ParameterizedTypeName.get(Provider.class, aClass), "messageProvider")
                    .addStatement("this.connection = connection")
                    .addStatement("this.messageProvider = messageProvider")
                    .build();

            MethodSpec actionMethod = MethodSpec.methodBuilder(StringUtils.uncapitalize(message))
                    .addModifiers(Modifier.PUBLIC)
                    .addStatement("connection.sendTCP(messageProvider.get())")
                    .build();

            TypeSpec action = TypeSpec.classBuilder(String.format("%sAction", message))
                    .addModifiers(Modifier.PUBLIC)
                    .addField(Connection.class, "connection")
                    .addField(ParameterizedTypeName.get(Provider.class, aClass), "messageProvider")
                    .addMethod(constructor)
                    .addMethod(actionMethod)
                    .build();

            JavaFile javaFile = JavaFile.builder(String.format("%s.%s.action", pckg, role), action)
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

            roleActionFiles.putIfAbsent(role, new ArrayList());
            roleActionFiles.get(role).add(javaFile);
        });

        roleActionFiles.entrySet().stream().forEach(entry->{
            String role = entry.getKey();
            List<JavaFile> listenerFiles = entry.getValue();

            MethodSpec.Builder configureBuilder = MethodSpec.methodBuilder("configure")
                    .addAnnotation(Override.class)
                    .addModifiers(Modifier.PUBLIC);

            listenerFiles.stream().forEach(javaFile -> {
                configureBuilder.addStatement("bind($N.class).in($T.class)", javaFile.typeSpec, Singleton.class);
            });

            TypeSpec module = TypeSpec.classBuilder(String.format("%sActionModule", StringUtils.capitalize(role)))
                    .addAnnotation(AnnotationSpec.builder(Generated.class)
                            .addMember("value", "\"$L\"", ListenerGenerator.class.getName())
                            .addMember("date", "\"$L\"", LocalDateTime.now().toString())
                            .build())
                    .addModifiers(Modifier.PUBLIC)
                    .superclass(AbstractModule.class)
                    .addMethod(configureBuilder.build())
                    .build();

            JavaFile moduleFile = JavaFile.builder(String.format("%s.%s.action", pckg, role), module)
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

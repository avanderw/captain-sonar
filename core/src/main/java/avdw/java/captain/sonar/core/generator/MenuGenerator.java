package avdw.java.captain.sonar.core.generator;

import avdw.java.captain.sonar.core.messages.Message;
import avdw.java.cli.menu.Action;
import avdw.java.cli.menu.Menu;
import com.google.inject.Inject;
import com.squareup.javapoet.*;
import org.apache.commons.lang3.StringUtils;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

import javax.annotation.Generated;
import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuGenerator {
    public static void generate(String aPackage) {
        Map<String, List<String>> roleActions = new HashMap();
        Reflections reflections = new Reflections(String.format("avdw.java.captain.sonar.core.messages"));
        reflections.getTypesAnnotatedWith(Message.class).stream().forEach(aClass -> {
            String role = aClass.getPackage().getName()
                    .replace("avdw.java.captain.sonar.core.messages.", "")
                    .replace(".message", "");

            String message = aClass.getAnnotation(Message.class).value();

            roleActions.putIfAbsent(role, new ArrayList());
            roleActions.get(role).add(message);
        });

        roleActions.entrySet().stream().forEach(entry -> {
            String role = entry.getKey();
            TypeSpec.Builder menu = TypeSpec.classBuilder(String.format("%sMenu", StringUtils.capitalize(role)))
                    .addAnnotation(AnnotationSpec.builder(Generated.class)
                            .addMember("value", "\"$L\"", ListenerGenerator.class.getName())
                            .addMember("date", "\"$L\"", LocalDateTime.now().toString())
                            .build())
                    .superclass(Menu.class)
                    .addModifiers(Modifier.PUBLIC);
            MethodSpec.Builder contructor = MethodSpec.constructorBuilder()
                    .addAnnotation(Inject.class)
                    .addModifiers(Modifier.PUBLIC)
                    .addStatement("super()");

            entry.getValue().stream().forEach(action -> {
                ClassName actionClass = ClassName.get(String.format("%s.%s.action", aPackage, role), String.format("%sAction", action));

                menu.addField(actionClass, StringUtils.uncapitalize(action), Modifier.PRIVATE);
                contructor.addParameter(actionClass, StringUtils.uncapitalize(action));
                contructor.addStatement(String.format("this.%s = %s", StringUtils.uncapitalize(action), StringUtils.uncapitalize(action)));

                MethodSpec methodSpec = MethodSpec.methodBuilder(StringUtils.uncapitalize(action))
                        .addAnnotation(Action.class)
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement(String.format("%s.%s()", StringUtils.uncapitalize(action), StringUtils.uncapitalize(action)))
                        .build();
                menu.addMethod(methodSpec);
            });

            menu.addMethod(contructor.build());

            JavaFile javaFile = JavaFile.builder(String.format("%s.%s", aPackage, role), menu.build())
                    .indent("    ")
                    .build();

            try {
                javaFile.writeTo(Paths.get("src/main/java/"));
                Logger.info(String.format("created {src/main/java/%s}", javaFile.toJavaFileObject().getName()));
            } catch (IOException e) {
                Logger.error(e);
            }
        });


        Logger.info("done");
    }
}

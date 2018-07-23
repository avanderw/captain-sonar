package avdw.java.captain.sonar.core.generator;

import avdw.java.captain.sonar.core.messages.Message;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

import javax.annotation.Generated;
import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

class EventGenerator {
    public static void generate(String pckg) {
        Reflections reflections = new Reflections(String.format("avdw.java.captain.sonar.core.messages"));
        reflections.getTypesAnnotatedWith(Message.class).stream().forEach(aClass -> {
            String role = aClass.getPackage().getName()
                    .replace("avdw.java.captain.sonar.core.messages.", "")
                    .replace(".message", "");

            String message = aClass.getAnnotation(Message.class).value();

            TypeSpec action = TypeSpec.classBuilder(String.format("%sEvent", message))
                    .addAnnotation(AnnotationSpec.builder(Generated.class)
                            .addMember("value", "\"$L\"", ListenerGenerator.class.getName())
                            .addMember("date", "\"$L\"", LocalDateTime.now().toString())
                            .build())
                    .addModifiers(Modifier.PUBLIC)
                    .build();

            JavaFile javaFile = JavaFile.builder(String.format("%s.%s.event", pckg, role), action)
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

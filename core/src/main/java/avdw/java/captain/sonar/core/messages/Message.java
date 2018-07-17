package avdw.java.captain.sonar.core.messages;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Message {
    String value();
}

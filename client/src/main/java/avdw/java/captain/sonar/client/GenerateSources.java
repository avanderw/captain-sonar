package avdw.java.captain.sonar.client;

import avdw.java.captain.sonar.core.config.StaticConfig;
import avdw.java.captain.sonar.core.messages.ListenerGenerator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

public class GenerateSources {
    public static void main(String[] args) {
        StaticConfig.configureLoggers(Level.DEBUG);
        ListenerGenerator.generateListeners(GenerateSources.class.getPackage().getName());
        Logger.info("done");
    }


}

package avdw.java.captain.sonar.server;

import avdw.java.captain.sonar.core.config.DynamicConfig;
import avdw.java.captain.sonar.core.generator.Generator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

public class GenerateSources {
    public static void main(String[] args) {
        DynamicConfig.configureLoggers(Level.DEBUG);

        Generator generator = new Generator(GenerateSources.class.getPackage().getName());
        generator.generateListeners();
        generator.generateEvents();

        Logger.info("done");
    }
}

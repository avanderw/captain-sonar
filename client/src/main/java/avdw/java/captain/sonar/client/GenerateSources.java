package avdw.java.captain.sonar.client;

import avdw.java.captain.sonar.lib.Config;
import avdw.java.captain.sonar.protocol.Protocol;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

public class GenerateSources {
    public static void main(String[] args) {
        Config.configureLoggers(Level.DEBUG);

        Protocol.generateListeners(GenerateSources.class.getPackage().getName());
        Logger.info("done");
    }


}

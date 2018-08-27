package avdw.java.captain.sonar.launcher;

import avdw.java.captain.sonar.client.ClientMain;
import avdw.java.captain.sonar.core.config.DynamicConfig;
import avdw.java.captain.sonar.server.ServerMain;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

public class Main {
    public static void main(String[] args) {
        DynamicConfig.configureLoggers(Level.DEBUG);
        Logger.info("Launching as client");
        if (!ClientMain.connect()) {
            Logger.info("Client could not find a connection. Launching as server");
            ServerMain.create();
            Logger.info("Connecting to the server that was just created");
            ClientMain.connect();
        }
    }
}

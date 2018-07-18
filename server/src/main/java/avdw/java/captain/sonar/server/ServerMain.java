package avdw.java.captain.sonar.server;

import avdw.java.captain.sonar.core.config.StaticConfig;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

public class ServerMain {
    public static void main(String[] args) {
        StaticConfig.configureLoggers(Level.DEBUG);
        Logger.debug("started");

        Injector injector = Guice.createInjector(new ServerModule());

        ServerConnection serverConnection = injector.getInstance(ServerConnection.class);
        serverConnection.start();
    }
}

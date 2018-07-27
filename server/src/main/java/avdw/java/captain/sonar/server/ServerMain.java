package avdw.java.captain.sonar.server;

import avdw.java.captain.sonar.core.config.DynamicConfig;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

public class ServerMain {
    public static void main(String[] args) {
        DynamicConfig.configureLoggers(Level.INFO);
        Logger.debug("started");

        Injector injector = Guice.createInjector(new ServerModule());

        ServerEndpoint serverEndpoint = injector.getInstance(ServerEndpoint.class);
        serverEndpoint.start();

        ServerMenu menu = injector.getInstance(ServerMenu.class);
        menu.display();

        serverEndpoint.stop();
    }
}

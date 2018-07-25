package avdw.java.captain.sonar.client;

import avdw.java.captain.sonar.core.config.DynamicConfig;
import avdw.java.cli.menu.Menu;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

public class ClientMain {
    public static void main(String[] args) {
        DynamicConfig.configureLoggers(Level.INFO);
        Logger.debug("started");

        Injector injector = Guice.createInjector(new ClientModule());

        ClientEndpoint client = injector.getInstance(ClientEndpoint.class);
        client.start();
        client.connect();

        Menu menu = injector.getInstance(ClientMenu.class);
        menu.display();

        client.stop();

        Logger.debug("stopped");
    }
}

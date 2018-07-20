package avdw.java.captain.sonar.client;

import avdw.java.captain.sonar.core.config.StaticConfig;
import avdw.java.cli.menu.Menu;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

public class ClientMain {
    public static void main(String[] args) {
        StaticConfig.configureLoggers(Level.DEBUG);
        Logger.debug("started");

        Injector injector = Guice.createInjector(new ClientModule());

        ClientConnection client = injector.getInstance(ClientConnection.class);
        client.start();
        client.connect();

        Menu menu = new Menu();

        menu.display();

        client.stop();

        Logger.debug("stopped");
    }
}

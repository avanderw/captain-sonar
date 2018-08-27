package avdw.java.captain.sonar.client;

import avdw.java.captain.sonar.client.exception.ClientConnectException;
import avdw.java.captain.sonar.core.config.DynamicConfig;
import avdw.java.cli.menu.Menu;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

public class ClientMain {
    public static void main(String[] args) {
        connect();
    }

    public static Boolean connect() {
        DynamicConfig.configureLoggers(Level.INFO);
        Logger.debug("started");

        Injector injector = Guice.createInjector(new ClientModule());

        ClientEndpoint client = injector.getInstance(ClientEndpoint.class);
        client.start();
        try {
            client.connect();
        } catch (ClientConnectException e) {
            return Boolean.FALSE;
        }

        Menu menu = injector.getInstance(ClientMenu.class);
        menu.display();

        client.stop();

        Logger.debug("stopped");
        return Boolean.TRUE;
    }
}

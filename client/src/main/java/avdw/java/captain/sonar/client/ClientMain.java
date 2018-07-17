package avdw.java.captain.sonar.client;

import avdw.java.captain.sonar.core.config.StaticConfig;
import avdw.java.captain.sonar.core.messages.captain.message.ActivateDroneMessage;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

import java.util.concurrent.TimeUnit;

public class ClientMain {
    public static void main(String[] args) {
        StaticConfig.configureLoggers(Level.DEBUG);
        Logger.debug("started");

        Injector injector = Guice.createInjector(new ClientModule());

        ClientEndpoint client = injector.getInstance(ClientEndpoint.class);
        client.start();

        client.connect();
        client.sendTCP(injector.getProvider(ActivateDroneMessage.class).get());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            Logger.error(e);
        }
        client.stop();

        Logger.debug("stopped");
    }
}

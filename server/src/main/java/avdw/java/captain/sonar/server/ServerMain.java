package avdw.java.captain.sonar.server;

import avdw.java.captain.sonar.lib.Config;
import avdw.java.captain.sonar.protocol.Protocol;
import avdw.java.captain.sonar.server.captain.listener.ActivateDroneListener;
import com.esotericsoftware.kryonet.Server;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) {
        Config.configureLoggers(Level.DEBUG);

        Logger.debug("server");

        Server server = new Server();
        server.start();
        try {
            server.bind(54555, 54777);
        } catch (IOException e) {
            Logger.error(e);
        }

        Injector injector = Guice.createInjector(new ServerModule());
        Protocol.registerMessages(server);
        Protocol.registerListeners(server, "avdw.java.captain.sonar.server", injector);
        System.out.println(injector.getInstance(ActivateDroneListener.class));
    }
}

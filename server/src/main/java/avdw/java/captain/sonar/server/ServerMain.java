package avdw.java.captain.sonar.server;

import avdw.java.captain.sonar.protocol.Protocol;
import com.esotericsoftware.kryonet.Server;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) {
        Configurator.currentConfig()
                .formatPattern("{date:yyyy-MM-dd HH:mm:ss} [{thread}] {class}.{method}() {level}: {message}")
                .level(Level.TRACE)
                .activate();

        Logger.debug("server");

        Server server = new Server();
        server.start();
        try {
            server.bind(54555, 54777);
        } catch (IOException e) {
            Logger.error(e);
        }

        Protocol.setup(server);
        server.addListener(new ServerListener());
        server.addListener(new ServerListener());
    }
}

package avdw.java.captain.sonar.client;

import avdw.java.captain.sonar.protocol.Protocol;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ClientMain {
    public static void main(String[] args) {
        Configurator.currentConfig()
                .formatPattern("{date:yyyy-MM-dd HH:mm:ss} [{thread}] {class}.{method}() {level}: {message}")
                .level(Level.TRACE)
                .activate();

        Logger.debug("client");

        Client client = new Client();
        client.start();

        Protocol.setup(client);

        client.addListener(new Listener() {
            @Override
            public void idle(Connection connection) {
                Logger.debug("idling");
            }

            @Override
            public void connected(Connection connection) {
                Logger.debug("connected");
                client.sendTCP("");
                client.sendTCP("");
                client.sendTCP("");
            }

            @Override
            public void received(Connection connection, Object object) {
                Logger.debug("received");
//                if (object instanceof TestMessage) {
//                    TestMessage response = (TestMessage) object;
//                    Logger.debug(response.message);
//                } else {
//                    Logger.error("unhandled object");
//                }
            }

            @Override
            public void disconnected(Connection connection) {
                Logger.debug("disconnected");
            }
        });

        try {
            client.connect(1000, "127.0.0.1", 54555, 54777);
            TimeUnit.SECONDS.sleep(1);
            client.stop();
        } catch (IOException | InterruptedException e) {
            Logger.error(e);
        }
    }
}

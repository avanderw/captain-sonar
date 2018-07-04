package avdw.java.captain.sonar.server;

import avdw.java.captain.sonar.protocol.TestMessage;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
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

        Kryo kryo = server.getKryo();
        kryo.register(TestMessage.class);

        server.addListener(new Listener(){
            @Override
            public void connected(Connection connection) {
                Logger.debug("connected");
            }

            @Override
            public void received(Connection connection, Object object) {
                Logger.debug("received");
                if (object instanceof TestMessage) {
                    TestMessage request = (TestMessage) object;
                    Logger.debug(request.message);

                    TestMessage response = kryo.copy(request);
                    response.message = "response";
                    connection.sendTCP(response);
                } else {
                    Logger.error("unhandled object");
                }
            }

            @Override
            public void disconnected (Connection connection) {
                Logger.debug("disconnected");
                server.stop();
            }
        });

    }
}

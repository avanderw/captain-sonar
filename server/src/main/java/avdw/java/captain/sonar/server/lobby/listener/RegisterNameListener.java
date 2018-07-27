package avdw.java.captain.sonar.server.lobby.listener;

import avdw.java.captain.sonar.core.messages.lobby.message.RegisterNameMessage;
import avdw.java.captain.sonar.server.ServerConnection;
import avdw.java.captain.sonar.server.ServerEndpoint;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.lang.Object;
import java.lang.Override;
import org.pmw.tinylog.Logger;

public class RegisterNameListener extends Listener {
    @Override
    public void connected(Connection connection) {
        Logger.debug("connected");
    }

    @Override
    public void received(Connection connection, Object object) {
        if (RegisterNameMessage.class.isInstance(object)) {
            ServerConnection server = (ServerConnection) connection;
            Logger.debug("received");
            RegisterNameMessage message = (RegisterNameMessage) object;
            Logger.info(String.format("client-%s %s (%s) registering name { %s }", server.getID(), server.getRemoteAddressTCP(), server.name, message.name));
            server.name = message.name;

            Logger.debug(String.format("server endpoint %s", server.getEndPoint()));
            ServerEndpoint endpoint = (ServerEndpoint) server.getEndPoint();
            message.clientId = server.getID();
            endpoint.sendToAllTCP(message);
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Logger.debug("disconnected");
    }
}

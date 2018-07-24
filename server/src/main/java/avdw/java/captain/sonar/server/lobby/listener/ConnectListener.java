package avdw.java.captain.sonar.server.lobby.listener;

import avdw.java.captain.sonar.core.messages.lobby.message.ConnectMessage;
import avdw.java.captain.sonar.server.ServerConnection;
import avdw.java.captain.sonar.server.ServerEndpoint;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.lang.Object;
import java.lang.Override;

import com.esotericsoftware.kryonet.Server;
import org.pmw.tinylog.Logger;

public class ConnectListener extends Listener {
    @Override
    public void connected(Connection connection) {
        Logger.debug("connected");
        ServerConnection conn = (ServerConnection) connection;
        Logger.info(String.format("%s: %s connected", conn.getID(), conn.name));
        ServerEndpoint server = (ServerEndpoint) connection.getEndPoint();
        server.sendToAllTCP(new ConnectMessage());
    }

    @Override
    public void received(Connection connection, Object object) {
        if (ConnectMessage.class.isInstance(object)) {
            Logger.debug("received");
            ConnectMessage message = (ConnectMessage) object;
            connection.sendTCP("ack");
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Logger.debug("disconnected");
    }
}

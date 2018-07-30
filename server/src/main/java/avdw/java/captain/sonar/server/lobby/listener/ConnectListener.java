package avdw.java.captain.sonar.server.lobby.listener;

import avdw.java.captain.sonar.core.messages.captain.message.ActivateDroneMessage;
import avdw.java.captain.sonar.core.messages.lobby.message.ConnectMessage;
import avdw.java.captain.sonar.core.messages.lobby.message.DisconnectMessage;
import avdw.java.captain.sonar.server.ServerConnection;
import avdw.java.captain.sonar.server.ServerEndpoint;
import avdw.java.captain.sonar.server.captain.event.ActivateDroneEvent;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.lang.Object;
import java.lang.Override;
import java.util.Arrays;

import com.esotericsoftware.kryonet.Server;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.pmw.tinylog.Logger;

public class ConnectListener extends Listener {
    private Provider<ConnectMessage> connectMessageProvider;

    @Inject
    ConnectListener(Provider<ConnectMessage> connectMessageProvider) {
        this.connectMessageProvider = connectMessageProvider;
    }

    @Override
    public void connected(Connection connection) {
        Logger.debug("connected");
        ServerConnection conn = (ServerConnection) connection;
        Logger.info(String.format("client-%s %s connected", conn.getID(), conn.getRemoteAddressTCP()));
        ServerEndpoint server = (ServerEndpoint) connection.getEndPoint();

        ConnectMessage message = connectMessageProvider.get();
        message.clientId = connection.getID();
        message.clientName = ((ServerConnection)connection).name;
        Arrays.stream(((ServerEndpoint) connection.getEndPoint()).getConnections())
                .map(existingConn->(ServerConnection)existingConn)
                .forEach(existingConn-> {
                    ConnectMessage.ClientData clientData =new ConnectMessage.ClientData( );
                    clientData.id = existingConn.getID();
                    clientData.name = existingConn.name;
                    message.alreadyConnected.add(clientData);
                });

        server.sendToAllTCP(message);
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
        ServerConnection conn = (ServerConnection) connection;
        DisconnectMessage disconnectMessage = new DisconnectMessage();
        disconnectMessage.id = conn.getID();
        ((ServerEndpoint) connection.getEndPoint()).sendToAllTCP(disconnectMessage);
        Logger.info(String.format("client-%s (%s) disconnected", conn.getID(), conn.name));
    }
}

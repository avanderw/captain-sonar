package avdw.java.captain.sonar.client.lobby.listener;

import avdw.java.captain.sonar.client.lobby.LobbyData;
import avdw.java.captain.sonar.core.messages.lobby.message.ConnectMessage;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.lang.Object;
import java.lang.Override;

import com.google.inject.Inject;
import org.pmw.tinylog.Logger;

public class ConnectListener extends Listener {
    private LobbyData lobbyData;

    @Inject
    ConnectListener(LobbyData lobbyData) {
        this.lobbyData = lobbyData;
    }

    @Override
    public void connected(Connection connection) {
        Logger.debug("connected");
    }

    @Override
    public void received(Connection connection, Object object) {
        if (ConnectMessage.class.isInstance(object)) {
            Logger.info("received");
            ConnectMessage message = (ConnectMessage) object;
            message.alreadyConnected.stream().forEach(conn -> lobbyData.connected.put(conn.id, new LobbyData.ClientData(conn.id, conn.name)));
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Logger.info("disconnected");
    }
}

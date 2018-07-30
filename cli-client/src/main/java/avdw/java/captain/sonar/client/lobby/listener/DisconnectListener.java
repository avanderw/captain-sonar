package avdw.java.captain.sonar.client.lobby.listener;

import avdw.java.captain.sonar.client.lobby.LobbyData;
import avdw.java.captain.sonar.core.messages.lobby.message.DisconnectMessage;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.lang.Object;
import java.lang.Override;

import com.google.inject.Inject;
import org.pmw.tinylog.Logger;

public class DisconnectListener extends Listener {
    private LobbyData lobbyData;

    @Inject
    DisconnectListener(LobbyData lobbyData) {
        this.lobbyData = lobbyData;
    }

    @Override
    public void connected(Connection connection) {
        Logger.debug("connected");
    }

    @Override
    public void received(Connection connection, Object object) {
        if (DisconnectMessage.class.isInstance(object)) {
            DisconnectMessage message = (DisconnectMessage) object;
            Logger.info(String.format("client-%s (%s) disconnected", message.id, lobbyData.connected.get(message.id).name));
            lobbyData.connected.remove(message.id);
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Logger.debug("disconnected");
    }
}

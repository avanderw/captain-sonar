package avdw.java.captain.sonar.client.lobby.listener;

import avdw.java.captain.sonar.client.lobby.LobbyData;
import avdw.java.captain.sonar.core.messages.lobby.message.RegisterNameMessage;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.google.inject.Inject;
import org.pmw.tinylog.Logger;

public class RegisterNameListener extends Listener {
    private final LobbyData lobbyData;

    @Inject
    RegisterNameListener(LobbyData lobbyData) {
        this.lobbyData = lobbyData;
    }

    @Override
    public void connected(Connection connection) {
        Logger.debug("connected");
    }

    @Override
    public void received(Connection connection, Object object) {
        if (RegisterNameMessage.class.isInstance(object)) {
            Logger.debug("received");
            RegisterNameMessage message = (RegisterNameMessage) object;

            Logger.info(String.format("client-%s (%s) registering name { %s }", message.clientId, lobbyData.connected.get(message.clientId), message.name));
            lobbyData.connected.get(message.clientId).name = message.name;
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Logger.debug("disconnected");
    }
}

package avdw.java.captain.sonar.client.lobby.action;

import avdw.java.captain.sonar.core.messages.lobby.message.DisconnectMessage;
import com.esotericsoftware.kryonet.Connection;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class DisconnectAction {
    Connection connection;

    Provider<DisconnectMessage> messageProvider;

    @Inject
    DisconnectAction(Connection connection, Provider<DisconnectMessage> messageProvider) {
        this.connection = connection;
        this.messageProvider = messageProvider;
    }

    public void disconnect() {
        connection.sendTCP(messageProvider.get());
    }
}

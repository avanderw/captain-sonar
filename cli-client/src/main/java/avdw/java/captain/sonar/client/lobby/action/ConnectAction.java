package avdw.java.captain.sonar.client.lobby.action;

import avdw.java.captain.sonar.core.messages.lobby.message.ConnectMessage;
import com.esotericsoftware.kryonet.Connection;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ConnectAction {
    Connection connection;

    Provider<ConnectMessage> messageProvider;

    @Inject
    ConnectAction(Connection connection, Provider<ConnectMessage> messageProvider) {
        this.connection = connection;
        this.messageProvider = messageProvider;
    }

    public void connect() {
        System.out.println(connection.getClass());
        connection.sendTCP(messageProvider.get());
    }
}

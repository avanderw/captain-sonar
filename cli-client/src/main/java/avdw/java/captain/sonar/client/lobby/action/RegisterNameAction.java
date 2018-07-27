package avdw.java.captain.sonar.client.lobby.action;

import avdw.java.captain.sonar.core.messages.lobby.message.RegisterNameMessage;
import com.esotericsoftware.kryonet.Connection;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class RegisterNameAction {
    Connection connection;

    Provider<RegisterNameMessage> messageProvider;

    @Inject
    RegisterNameAction(Connection connection, Provider<RegisterNameMessage> messageProvider) {
        this.connection = connection;
        this.messageProvider = messageProvider;
    }

    public void registerName(String name) {
        RegisterNameMessage message = messageProvider.get();
        message.name = name;
        connection.sendTCP(message);
    }
}

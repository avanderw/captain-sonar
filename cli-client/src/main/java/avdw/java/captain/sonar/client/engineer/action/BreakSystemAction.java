package avdw.java.captain.sonar.client.engineer.action;

import avdw.java.captain.sonar.core.messages.engineer.message.BreakSystemMessage;
import com.esotericsoftware.kryonet.Connection;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class BreakSystemAction {
    Connection connection;

    Provider<BreakSystemMessage> messageProvider;

    @Inject
    BreakSystemAction(Connection connection, Provider<BreakSystemMessage> messageProvider) {
        this.connection = connection;
        this.messageProvider = messageProvider;
    }

    public void breakSystem() {
        connection.sendTCP(messageProvider.get());
    }
}

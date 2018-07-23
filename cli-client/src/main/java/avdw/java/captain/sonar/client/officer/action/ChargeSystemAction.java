package avdw.java.captain.sonar.client.officer.action;

import avdw.java.captain.sonar.core.messages.officer.message.ChargeSystemMessage;
import com.esotericsoftware.kryonet.Connection;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ChargeSystemAction {
    Connection connection;

    Provider<ChargeSystemMessage> messageProvider;

    @Inject
    ChargeSystemAction(Connection connection, Provider<ChargeSystemMessage> messageProvider) {
        this.connection = connection;
        this.messageProvider = messageProvider;
    }

    public void chargeSystem() {
        connection.sendTCP(messageProvider.get());
    }
}

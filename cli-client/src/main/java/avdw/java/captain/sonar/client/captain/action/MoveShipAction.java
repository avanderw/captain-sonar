package avdw.java.captain.sonar.client.captain.action;

import avdw.java.captain.sonar.core.messages.captain.message.MoveShipMessage;
import com.esotericsoftware.kryonet.Connection;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class MoveShipAction {
    Connection connection;

    Provider<MoveShipMessage> messageProvider;

    @Inject
    MoveShipAction(Connection connection, Provider<MoveShipMessage> messageProvider) {
        this.connection = connection;
        this.messageProvider = messageProvider;
    }

    public void moveShip() {
        connection.sendTCP(messageProvider.get());
    }
}

package avdw.java.captain.sonar.client.captain.action;

import avdw.java.captain.sonar.core.messages.captain.message.PlaceShipMessage;
import com.esotericsoftware.kryonet.Connection;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class PlaceShipAction {
    Connection connection;

    Provider<PlaceShipMessage> messageProvider;

    @Inject
    PlaceShipAction(Connection connection, Provider<PlaceShipMessage> messageProvider) {
        this.connection = connection;
        this.messageProvider = messageProvider;
    }

    public void placeShip() {
        connection.sendTCP(messageProvider.get());
    }
}

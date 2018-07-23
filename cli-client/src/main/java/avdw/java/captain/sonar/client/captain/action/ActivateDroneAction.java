package avdw.java.captain.sonar.client.captain.action;

import avdw.java.captain.sonar.core.messages.captain.message.ActivateDroneMessage;
import com.esotericsoftware.kryonet.Connection;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ActivateDroneAction {
    Connection connection;

    Provider<ActivateDroneMessage> messageProvider;

    @Inject
    ActivateDroneAction(Connection connection, Provider<ActivateDroneMessage> messageProvider) {
        this.connection = connection;
        this.messageProvider = messageProvider;
    }

    public void activateDrone() {
        connection.sendTCP(messageProvider.get());
    }
}

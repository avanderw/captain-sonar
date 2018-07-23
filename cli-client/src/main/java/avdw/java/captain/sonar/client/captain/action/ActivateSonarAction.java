package avdw.java.captain.sonar.client.captain.action;

import avdw.java.captain.sonar.core.messages.captain.message.ActivateSonarMessage;
import com.esotericsoftware.kryonet.Connection;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ActivateSonarAction {
    Connection connection;

    Provider<ActivateSonarMessage> messageProvider;

    @Inject
    ActivateSonarAction(Connection connection, Provider<ActivateSonarMessage> messageProvider) {
        this.connection = connection;
        this.messageProvider = messageProvider;
    }

    public void activateSonar() {
        connection.sendTCP(messageProvider.get());
    }
}

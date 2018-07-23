package avdw.java.captain.sonar.client.captain.action;

import avdw.java.captain.sonar.core.messages.captain.message.ActivateSilentRunningMessage;
import com.esotericsoftware.kryonet.Connection;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ActivateSilentRunningAction {
    Connection connection;

    Provider<ActivateSilentRunningMessage> messageProvider;

    @Inject
    ActivateSilentRunningAction(Connection connection,
            Provider<ActivateSilentRunningMessage> messageProvider) {
        this.connection = connection;
        this.messageProvider = messageProvider;
    }

    public void activateSilentRunning() {
        connection.sendTCP(messageProvider.get());
    }
}

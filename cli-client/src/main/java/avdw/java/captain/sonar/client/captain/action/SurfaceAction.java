package avdw.java.captain.sonar.client.captain.action;

import avdw.java.captain.sonar.core.messages.captain.message.SurfaceMessage;
import com.esotericsoftware.kryonet.Connection;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class SurfaceAction {
    Connection connection;

    Provider<SurfaceMessage> messageProvider;

    @Inject
    SurfaceAction(Connection connection, Provider<SurfaceMessage> messageProvider) {
        this.connection = connection;
        this.messageProvider = messageProvider;
    }

    public void surface() {
        connection.sendTCP(messageProvider.get());
    }
}

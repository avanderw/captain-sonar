package avdw.java.captain.sonar.client.setup.action;

import avdw.java.captain.sonar.core.messages.setup.message.SetupMapMessage;
import com.esotericsoftware.kryonet.Connection;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class SetupMapAction {
    Connection connection;

    Provider<SetupMapMessage> messageProvider;

    @Inject
    SetupMapAction(Connection connection, Provider<SetupMapMessage> messageProvider) {
        this.connection = connection;
        this.messageProvider = messageProvider;
    }

    public void setupMap() {
        connection.sendTCP(messageProvider.get());
    }
}

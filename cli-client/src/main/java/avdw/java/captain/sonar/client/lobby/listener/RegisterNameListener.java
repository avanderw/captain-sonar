package avdw.java.captain.sonar.client.lobby.listener;

import avdw.java.captain.sonar.core.messages.lobby.message.RegisterNameMessage;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.lang.Object;
import java.lang.Override;
import org.pmw.tinylog.Logger;

public class RegisterNameListener extends Listener {
    @Override
    public void connected(Connection connection) {
        Logger.debug("connected");
    }

    @Override
    public void received(Connection connection, Object object) {
        if (RegisterNameMessage.class.isInstance(object)) {
            Logger.debug("received");
            RegisterNameMessage message = (RegisterNameMessage) object;
            Logger.info(String.format("client-%s registered name { %s }", message.clientId, message.name));
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Logger.debug("disconnected");
    }
}

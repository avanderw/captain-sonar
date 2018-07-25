package avdw.java.captain.sonar.client.lobby.listener;

import avdw.java.captain.sonar.core.messages.lobby.message.ConnectMessage;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.lang.Object;
import java.lang.Override;
import org.pmw.tinylog.Logger;

public class ConnectListener extends Listener {
    @Override
    public void connected(Connection connection) {
        Logger.debug("connected");
    }

    @Override
    public void received(Connection connection, Object object) {
        if (ConnectMessage.class.isInstance(object)) {
            Logger.debug("received");
            ConnectMessage message = (ConnectMessage) object;

        }
    }

    @Override
    public void disconnected(Connection connection) {
        Logger.debug("disconnected");
    }
}

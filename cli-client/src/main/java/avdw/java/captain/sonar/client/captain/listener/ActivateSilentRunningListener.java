package avdw.java.captain.sonar.client.captain.listener;

import avdw.java.captain.sonar.core.messages.captain.message.ActivateSilentRunningMessage;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.lang.Object;
import java.lang.Override;
import org.pmw.tinylog.Logger;

public class ActivateSilentRunningListener extends Listener {
    @Override
    public void connected(Connection connection) {
        Logger.debug("connected");
    }

    @Override
    public void received(Connection connection, Object object) {
        if (ActivateSilentRunningMessage.class.isInstance(object)) {
            Logger.debug("received");
            ActivateSilentRunningMessage message = (ActivateSilentRunningMessage) object;
            connection.sendTCP("ack");
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Logger.debug("disconnected");
    }
}

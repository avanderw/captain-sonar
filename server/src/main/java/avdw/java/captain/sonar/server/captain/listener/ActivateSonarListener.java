package avdw.java.captain.sonar.server.captain.listener;

import avdw.java.captain.sonar.core.messages.captain.message.ActivateSonarMessage;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.lang.Object;
import java.lang.Override;
import org.pmw.tinylog.Logger;

public class ActivateSonarListener extends Listener {
    @Override
    public void connected(Connection connection) {
        Logger.debug("connected");
    }

    @Override
    public void received(Connection connection, Object object) {
        if (ActivateSonarMessage.class.isInstance(object)) {
            Logger.debug("received");
            ActivateSonarMessage message = (ActivateSonarMessage) object;
            connection.sendTCP("ack");
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Logger.debug("disconnected");
    }
}

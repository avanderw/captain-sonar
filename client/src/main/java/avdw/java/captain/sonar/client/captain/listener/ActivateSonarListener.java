package avdw.java.captain.sonar.client.captain.listener;

import avdw.java.captain.sonar.protocol.captain.message.ActivateSonarMessage;
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
            ActivateSonarMessage request = (ActivateSonarMessage) object;
            connection.sendTCP("ack");
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Logger.debug("disconnected");
    }
}

package avdw.java.captain.sonar.server.captain.listener;

import avdw.java.captain.sonar.protocol.captain.message.MoveShipMessage;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.lang.Object;
import java.lang.Override;
import org.pmw.tinylog.Logger;

public class MoveShipListener extends Listener {
    @Override
    public void connected(Connection connection) {
        Logger.debug("connected");
    }

    @Override
    public void received(Connection connection, Object object) {
        if (MoveShipMessage.class.isInstance(object)) {
            Logger.debug("received");
            MoveShipMessage request = (MoveShipMessage) object;
            connection.sendTCP("ack");
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Logger.debug("disconnected");
    }
}

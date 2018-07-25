package avdw.java.captain.sonar.server.captain.listener;

import avdw.java.captain.sonar.core.messages.captain.message.PlaceShipMessage;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.lang.Object;
import java.lang.Override;
import org.pmw.tinylog.Logger;

public class PlaceShipListener extends Listener {
    @Override
    public void connected(Connection connection) {
        Logger.debug("connected");
    }

    @Override
    public void received(Connection connection, Object object) {
        if (PlaceShipMessage.class.isInstance(object)) {
            Logger.debug("received");
            PlaceShipMessage message = (PlaceShipMessage) object;
            connection.sendTCP("ack");
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Logger.debug("disconnected");
    }
}

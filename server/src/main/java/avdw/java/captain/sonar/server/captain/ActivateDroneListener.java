package avdw.java.captain.sonar.server.captain;

import avdw.java.captain.sonar.protocol.captain.ActivateDroneMessage;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.pmw.tinylog.Logger;

public class ActivateDroneListener extends Listener {
    @Override
    public void idle(Connection connection) {
        Logger.debug("idling");
    }

    @Override
    public void connected(Connection connection) {
        Logger.debug("connected");
    }

    @Override
    public void received(Connection connection, Object object) {
        Logger.debug("received");
        if (object instanceof ActivateDroneMessage) {
            ActivateDroneMessage request = (ActivateDroneMessage) object;

            connection.sendTCP(this.getClass().getSimpleName());
        }
    }

    @Override
    public void disconnected (Connection connection) {
        Logger.debug("disconnected");
//        server.stop();
    }
}

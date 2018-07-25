package avdw.java.captain.sonar.server.engineer.listener;

import avdw.java.captain.sonar.core.messages.engineer.message.BreakSystemMessage;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.lang.Object;
import java.lang.Override;
import org.pmw.tinylog.Logger;

public class BreakSystemListener extends Listener {
    @Override
    public void connected(Connection connection) {
        Logger.debug("connected");
    }

    @Override
    public void received(Connection connection, Object object) {
        if (BreakSystemMessage.class.isInstance(object)) {
            Logger.debug("received");
            BreakSystemMessage message = (BreakSystemMessage) object;
            connection.sendTCP("ack");
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Logger.debug("disconnected");
    }
}

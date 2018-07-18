package avdw.java.captain.sonar.client.officer.listener;

import avdw.java.captain.sonar.core.messages.officer.message.ChargeSystemMessage;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.lang.Object;
import java.lang.Override;
import org.pmw.tinylog.Logger;

public class ChargeSystemListener extends Listener {
  @Override
  public void connected(Connection connection) {
    Logger.debug("connected");
  }

  @Override
  public void received(Connection connection, Object object) {
    if (object instanceof ChargeSystemMessage) {
      Logger.debug("received");
      ChargeSystemMessage request = (ChargeSystemMessage) object;
      connection.sendTCP("ack");
    }
  }

  @Override
  public void disconnected(Connection connection) {
    Logger.debug("disconnected");
  }
}

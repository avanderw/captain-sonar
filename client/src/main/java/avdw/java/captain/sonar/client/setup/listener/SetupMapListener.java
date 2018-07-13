package avdw.java.captain.sonar.client.setup.listener;

import avdw.java.captain.sonar.protocol.setup.message.SetupMapMessage;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.lang.Object;
import java.lang.Override;
import org.pmw.tinylog.Logger;

public class SetupMapListener extends Listener {
  @Override
  public void connected(Connection connection) {
    Logger.debug("connected");
  }

  @Override
  public void received(Connection connection, Object object) {
    if (object instanceof SetupMapMessage) {
      Logger.debug("received");
      SetupMapMessage request = (SetupMapMessage) object;
      connection.sendTCP("ack");
    }
  }

  @Override
  public void disconnected(Connection connection) {
    Logger.debug("disconnected");
  }
}

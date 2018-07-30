package avdw.java.captain.sonar.core.messages.lobby.message;

import avdw.java.captain.sonar.core.messages.Message;

import java.util.ArrayList;
import java.util.List;

@Message("Connect")
public class ConnectMessage {
    public Integer clientId;
    public String clientName;
    public List<ClientData> alreadyConnected = new ArrayList();

    public static class ClientData {
        public Integer id;
        public String name;

        public ClientData() {}
    }
}

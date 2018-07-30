package avdw.java.captain.sonar.client.lobby;

import java.util.HashMap;
import java.util.Map;

public class LobbyData {
    public Map<Integer, ClientData> connected = new HashMap();

    public static class ClientData {
        public Integer id;
        public String name;

        public ClientData(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("%s:%s", id, name);
        }
    }
}

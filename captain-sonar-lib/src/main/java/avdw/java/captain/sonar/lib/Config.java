package avdw.java.captain.sonar.lib;

import avdw.java.captain.sonar.lib.network.NetworkConfig;
import avdw.java.captain.sonar.lib.network.PacketHandler;

import java.util.HashMap;
import java.util.Map;

public abstract class Config implements NetworkConfig {
    final Map<String, PacketHandler> packetHandlers;

    public Config() {
        packetHandlers = new HashMap();
    }

    public PacketHandler packetHandler(String key) {
        PacketHandler packetHandler = packetHandlers.get(key);
        if (packetHandler == null) {
            throw new UnsupportedOperationException(String.format("no packet handler registered for %s", key));
        } else {
            return packetHandler;
        }
    }
}

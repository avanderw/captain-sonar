package avdw.java.captain.sonar.lib.network;

public interface NetworkConfig {
    PacketHandler packetHandler(String key);
}

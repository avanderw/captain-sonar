package avdw.java.captain.sonar.lib.network;

import java.net.DatagramPacket;

public interface PacketHandler {
    void handle(DatagramPacket packet);
}

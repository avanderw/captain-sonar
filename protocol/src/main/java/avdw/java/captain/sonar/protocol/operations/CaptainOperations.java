package avdw.java.captain.sonar.protocol.operations;

import avdw.java.captain.sonar.protocol.Address;

import java.io.IOException;
import java.net.DatagramPacket;

@Address("captain")
public interface CaptainOperations {
    void goNorth(DatagramPacket packet) throws IOException;

    void goSouth(DatagramPacket packet) throws IOException;

    void goEast(DatagramPacket packet) throws IOException;

    void goWest(DatagramPacket packet) throws IOException;

    void acknowledge(DatagramPacket packet) throws IOException;
}

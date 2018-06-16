package avdw.java.captain.sonar.protocol.message;

import avdw.java.captain.sonar.protocol.Address;
import avdw.java.captain.sonar.protocol.operations.CaptainOperations;

import java.net.DatagramPacket;

@Address("captain")
public class CaptainMessage extends Envelope implements CaptainOperations {
    public CaptainMessage() {
        super();
    }

    @Override
    public void goNorth(DatagramPacket packet) {
        operation = "goNorth";
    }

    @Override
    public void goSouth(DatagramPacket packet) {
        operation = "goSouth";

    }

    @Override
    public void goEast(DatagramPacket packet) {
        operation = "goEast";

    }

    @Override
    public void goWest(DatagramPacket packet) {
        operation = "goWest";
    }

    @Override
    public void acknowledge(DatagramPacket packet) {
        operation = "acknowledge";
    }
}

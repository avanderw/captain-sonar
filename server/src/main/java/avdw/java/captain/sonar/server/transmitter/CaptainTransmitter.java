package avdw.java.captain.sonar.server.transmitter;

import avdw.java.captain.sonar.lib.network.Transmitter;
import avdw.java.captain.sonar.protocol.operations.CaptainOperations;
import avdw.java.captain.sonar.server.receiver.CaptainReceiver;

import java.net.DatagramPacket;

public class CaptainTransmitter implements CaptainOperations {

    private Transmitter transmitter;

    public CaptainTransmitter(Transmitter transmitter) {
        this.transmitter = transmitter;
    }

    @Override
    public void goNorth(DatagramPacket packet) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public void goSouth(DatagramPacket packet) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public void goEast(DatagramPacket packet) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public void goWest(DatagramPacket packet) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    public void acknowledge(DatagramPacket packet) {
        throw new UnsupportedOperationException("implemented in " + CaptainReceiver.class);
    }
}

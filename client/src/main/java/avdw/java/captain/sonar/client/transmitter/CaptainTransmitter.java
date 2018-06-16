package avdw.java.captain.sonar.client.transmitter;

import avdw.java.captain.sonar.client.receiver.CaptainReceiver;
import avdw.java.captain.sonar.lib.network.Transmitter;
import avdw.java.captain.sonar.protocol.message.CaptainMessage;
import avdw.java.captain.sonar.protocol.operations.CaptainOperations;

import java.io.IOException;
import java.net.DatagramPacket;

public class CaptainTransmitter implements CaptainOperations {

    private Transmitter transmitter;

    public CaptainTransmitter(Transmitter transmitter) {
        this.transmitter = transmitter;
    }

    @Override
    public void goNorth(DatagramPacket packet) throws IOException {
        CaptainMessage captainMessage = new CaptainMessage();
        captainMessage.goNorth(packet);
        transmitter.send(captainMessage);
    }

    @Override
    public void goSouth(DatagramPacket packet) throws IOException {
        CaptainMessage captainMessage = new CaptainMessage();
        captainMessage.goSouth(packet);
        transmitter.send(captainMessage);

    }

    @Override
    public void goEast(DatagramPacket packet) throws IOException {
        CaptainMessage captainMessage = new CaptainMessage();
        captainMessage.goEast(packet);
        transmitter.send(captainMessage);

    }

    @Override
    public void goWest(DatagramPacket packet) throws IOException {
        CaptainMessage captainMessage = new CaptainMessage();
        captainMessage.goWest(packet);
        transmitter.send(captainMessage);

    }

    @Override
    public void acknowledge(DatagramPacket packet) {
        throw new UnsupportedOperationException("implemented in " + CaptainReceiver.class);
    }
}

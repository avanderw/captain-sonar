package avdw.java.captain.sonar.server.receiver;

import avdw.java.captain.sonar.lib.network.PacketReceiver;
import avdw.java.captain.sonar.lib.network.Transmitter;
import avdw.java.captain.sonar.protocol.Address;
import avdw.java.captain.sonar.protocol.message.CaptainMessage;
import avdw.java.captain.sonar.protocol.operations.CaptainOperations;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.net.DatagramPacket;

@Address("captain")
public class CaptainReceiver extends PacketReceiver implements CaptainOperations {

    private Transmitter transmitter;

    public CaptainReceiver(Transmitter transmitter) {
        this.transmitter = transmitter;
    }

    @Override
    public void goNorth(DatagramPacket packet) throws IOException {
        Logger.debug("going north");
        sendAcknowledge(packet);
    }

    @Override
    public void goSouth(DatagramPacket packet) throws IOException {
        Logger.debug("going south");
        sendAcknowledge(packet);
    }

    @Override
    public void goEast(DatagramPacket packet) throws IOException {
        Logger.debug("going east");
        sendAcknowledge(packet);
    }

    @Override
    public void goWest(DatagramPacket packet) throws IOException {
        Logger.debug("going west");
        sendAcknowledge(packet);
    }

    @Override
    public void acknowledge(DatagramPacket packet) {
        Logger.debug("confirmed receipt of last message");
    }

    private void sendAcknowledge(DatagramPacket packet) throws IOException {
        CaptainMessage message = new CaptainMessage();
        message.acknowledge(packet);
        transmitter.send(message, packet);
    }

}

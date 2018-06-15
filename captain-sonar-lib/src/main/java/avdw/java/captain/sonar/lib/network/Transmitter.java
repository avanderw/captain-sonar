package avdw.java.captain.sonar.lib.network;

import avdw.java.captain.sonar.protocol.message.Envelope;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Transmitter {
    private final DatagramSocket socket;

    public Transmitter(DatagramSocket socket) {
        this.socket = socket;
    }

    public void send(Envelope envelope, InetAddress inetAddress, Integer port) throws IOException {
        byte[] buffer = envelope.serialize();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
        Logger.trace(String.format("sending %s", new String(buffer)));
        socket.send(packet);
    }
}

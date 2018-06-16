package avdw.java.captain.sonar.lib.network;

import avdw.java.captain.sonar.protocol.message.Envelope;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Transmitter {
    private final DatagramSocket socket;
    private InetAddress inetAddress;
    private Integer port;

    public Transmitter(DatagramSocket socket, InetAddress inetAddress, Integer port) {
        this.socket = socket;
        this.inetAddress = inetAddress;
        this.port = port;
    }

    public Transmitter(DatagramSocket socket) {
        this.socket = socket;
    }

    public void send(Envelope envelope) throws IOException {
        send(envelope, inetAddress, port);
    }

    public void send(Envelope envelope, DatagramPacket packet) throws IOException {
        send(envelope, packet.getAddress(), packet.getPort());
    }

    private void send(Envelope envelope, InetAddress inetAddress, Integer port) throws IOException {
        byte[] buffer = envelope.serialize();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
        Logger.trace(String.format("sending %s:%s message %s", inetAddress, port, new String(buffer)));
        socket.send(packet);
    }
}

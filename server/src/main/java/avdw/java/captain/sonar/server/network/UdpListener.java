package avdw.java.captain.sonar.server.network;

import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpListener extends PortListener {
    DatagramSocket datagramSocket;

    public UdpListener(Integer port) throws SocketException {
        datagramSocket = new DatagramSocket(port);
    }

    @Override
    PacketRouter listenForPacket() throws ListenException {
        try {
            DatagramPacket datagramPacket = new DatagramPacket(new byte[508], 508);
            datagramSocket.receive(datagramPacket);

            return new PacketRouter(datagramPacket.getAddress(), datagramPacket.getPort(), new String(datagramPacket.getData()), new UdpTransmitter(datagramSocket));
        } catch (IOException e) {
            Logger.error(e.getMessage());
            throw new ListenException();
        }
    }
}

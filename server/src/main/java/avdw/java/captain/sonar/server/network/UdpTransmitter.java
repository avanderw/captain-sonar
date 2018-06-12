package avdw.java.captain.sonar.server.network;

import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpTransmitter implements PortTransmitter {
    private DatagramSocket datagramSocket;

    public UdpTransmitter(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void transmit(String data) {
        DatagramPacket datagramPacket = new DatagramPacket(data.getBytes(), data.getBytes().length, datagramSocket.getInetAddress(), datagramSocket.getPort());
        try {
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            Logger.error(e.getMessage());
        }
    }
}

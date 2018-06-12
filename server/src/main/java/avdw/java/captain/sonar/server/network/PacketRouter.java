package avdw.java.captain.sonar.server.network;

import java.net.InetAddress;

public class PacketRouter implements Runnable {
    private final InetAddress address;
    private final Integer port;
    private final String data;
    private final PortTransmitter portTransmitter;

    public PacketRouter(InetAddress address, Integer port, String data, PortTransmitter portTransmitter) {
        this.address = address;
        this.port = port;
        this.data = data;
        this.portTransmitter = portTransmitter;
    }

    @Override
    public void run() {
        portTransmitter.transmit(data.toUpperCase());
    }
}

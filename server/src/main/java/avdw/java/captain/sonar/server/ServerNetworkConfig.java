package avdw.java.captain.sonar.server;

import avdw.java.captain.sonar.lib.Property;
import avdw.java.captain.sonar.lib.network.NetworkConfig;
import avdw.java.captain.sonar.lib.network.Receiver;
import avdw.java.captain.sonar.lib.network.Transmitter;
import org.pmw.tinylog.Logger;

import java.net.DatagramSocket;
import java.net.SocketException;

class ServerNetworkConfig extends NetworkConfig {

    public Transmitter transmitter;
    public Receiver receiver;

    ServerNetworkConfig() {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(Property.SERVER_PORT);
        } catch (SocketException e) {
            Logger.error(e);
        }

        transmitter = new Transmitter(socket);
        registerReceivers("avdw.java.captain.sonar.server.receiver", transmitter);

        receiver = new Receiver(this, socket);
        receiver.start();
    }
}

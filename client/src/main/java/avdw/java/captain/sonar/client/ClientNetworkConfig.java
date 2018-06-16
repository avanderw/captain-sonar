package avdw.java.captain.sonar.client;

import avdw.java.captain.sonar.lib.Property;
import avdw.java.captain.sonar.lib.network.NetworkConfig;
import avdw.java.captain.sonar.lib.network.Receiver;
import avdw.java.captain.sonar.lib.network.Transmitter;
import org.pmw.tinylog.Logger;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientNetworkConfig extends NetworkConfig {

    public Transmitter transmitter;
    public Receiver receiver;

    public ClientNetworkConfig() {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            Logger.error(e);
        }

        try {
            transmitter = new Transmitter(socket, InetAddress.getByName(Property.HOST), Property.SERVER_PORT);
            registerReceivers("avdw.java.captain.sonar.client.receiver", transmitter);
        } catch (UnknownHostException e) {
            Logger.error(e);
        }

        receiver = new Receiver(this, socket);
        receiver.start();
    }

}

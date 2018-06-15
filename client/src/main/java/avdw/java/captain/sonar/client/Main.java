package avdw.java.captain.sonar.client;

import avdw.java.captain.sonar.lib.Property;
import avdw.java.captain.sonar.lib.network.NetworkConfig;
import avdw.java.captain.sonar.lib.network.Receiver;
import avdw.java.captain.sonar.lib.network.Transmitter;
import avdw.java.captain.sonar.protocol.message.Envelope;
import avdw.java.captain.sonar.protocol.message.CaptainMessage;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        Configurator.currentConfig()
                .formatPattern("{date:yyyy-MM-dd HH:mm:ss} [{thread}] {class}.{method}() {level}: {message}")
                .level(Level.TRACE)
                .activate();

        Logger.debug("client");

        DatagramSocket socket = new DatagramSocket();

        Transmitter transmitter = new Transmitter(socket);
        Envelope envelope = new CaptainMessage();
        transmitter.send(envelope, InetAddress.getByName(Property.HOST), Property.UDP_PORT);

        NetworkConfig networkConfig = new ClientConfig();
        Receiver receiver = new Receiver(networkConfig, socket);
        receiver.start();

    }
}

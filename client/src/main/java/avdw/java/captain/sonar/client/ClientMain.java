package avdw.java.captain.sonar.client;

import avdw.java.captain.sonar.lib.Config;
import avdw.java.captain.sonar.protocol.Protocol;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class ClientMain {
    public static void main(String[] args) {
        Config.configureLoggers(Level.DEBUG);

        Logger.debug("started");
        Integer udpPort = 54777;
        Integer tcpPort = 54555;
        Integer timeout = 1000;

        Client client = new Client();
        client.start();

        Injector injector = Guice.createInjector(new ClientModule());
        Protocol.registerMessages(client);
        Protocol.registerListeners(client, "avdw.java.captain.sonar.client", injector);

        Logger.debug("discovering host");
        InetAddress address = client.discoverHost(udpPort, timeout);
        if (address == null) {
            Logger.warn(String.format("could not find a host, timeout %sms, UDP port %s", timeout, udpPort));
        } else {
            Logger.info(String.format("found host at %s:%s", address.getHostAddress(), udpPort));

            try {
                client.connect(timeout, address.getHostAddress(), tcpPort, udpPort);
                TimeUnit.SECONDS.sleep(1);
                client.stop();
            } catch (IOException | InterruptedException e) {
                Logger.warn(String.format("could not connect to host %s", address.getHostAddress()));
                Logger.error(e);
            }
        }

        Logger.debug("stopped");
    }
}

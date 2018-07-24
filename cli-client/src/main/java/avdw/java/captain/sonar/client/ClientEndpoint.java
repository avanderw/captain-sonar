package avdw.java.captain.sonar.client;

import avdw.java.captain.sonar.core.messages.Message;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Comparator;
import java.util.Set;

public class ClientEndpoint extends Client {
    private final Integer udpPort;
    private final Integer tcpPort;
    private final Integer timeout;

    @Inject
    ClientEndpoint(@Named("tcp-port")Integer tcpPort, @Named("udp-port")Integer udpPort, @Named("network-timeout") Integer timeout, Set<Listener> listeners) {
        this.udpPort = udpPort;
        this.tcpPort = tcpPort;
        this.timeout = timeout;

        final Kryo kryo = getKryo();
        new Reflections("avdw.java.captain.sonar.core").getTypesAnnotatedWith(Message.class).stream()
                .sorted(Comparator.comparing(Class::getSimpleName))
                .forEach(aClass -> kryo.register(aClass));

        listeners.stream().forEach(listener -> {
            addListener(listener);
            Logger.debug(String.format("registered %s", listener));
        });
    }

    public void connect() {
        Logger.debug("discovering host");
        InetAddress address = discoverHost(udpPort, timeout);
        if (address == null) {
            Logger.warn(String.format("could not find a host, timeout %sms, UDP port %s", timeout, udpPort));
        } else {
            Logger.info(String.format("found host at %s:%s", address.getHostAddress(), udpPort));

            try {
                connect(timeout, address.getHostAddress(), tcpPort, udpPort);
            } catch (IOException e) {
                Logger.warn(String.format("could not connect to host %s", address.getHostAddress()));
                Logger.error(e);
            }
        }
    }
}

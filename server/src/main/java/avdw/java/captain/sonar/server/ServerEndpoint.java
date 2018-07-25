package avdw.java.captain.sonar.server;

import avdw.java.captain.sonar.core.messages.Message;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

import javax.management.MBeanServerConnection;
import java.io.IOException;
import java.util.Comparator;
import java.util.Set;

public class ServerEndpoint extends Server {
    private final Integer tcpPort;
    private final Integer udpPort;

    @Inject
    public ServerEndpoint(@Named("tcp-port") Integer tcpPort, @Named("udp-port") Integer udpPort, Set<Listener> listeners) {
        super();
        this.tcpPort = tcpPort;
        this.udpPort = udpPort;

        final Kryo kryo = getKryo();
        new Reflections("avdw.java.captain.sonar.core").getTypesAnnotatedWith(Message.class).stream()
                .sorted(Comparator.comparing(Class::getSimpleName))
                .forEach(aClass -> kryo.register(aClass));

        listeners.stream().forEach(listener -> {
            addListener(listener);
            Logger.debug(String.format("registered %s", listener));
        });
    }

    @Override
    protected Connection newConnection() {
        return new ServerConnection();
    }

    @Override
    public void start() {
        super.start();
        try {
            bind(tcpPort, udpPort);
        } catch (IOException e) {
            Logger.error(e);
        }
        Logger.info(String.format("endpoint exposed on tcp { %s }, udp { %s }", tcpPort, udpPort));
    }
}

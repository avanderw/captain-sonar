package avdw.java.captain.sonar.server;

import avdw.java.captain.sonar.core.messages.Message;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

import java.io.IOException;
import java.util.Set;

public class ServerConnection extends Server {
    private final Integer tcpPort;
    private final Integer udpPort;

    @Inject
    public ServerConnection(@Named("tcp-port")Integer tcpPort, @Named("udp-port")Integer udpPort, Set<Listener> listeners) {
        super();
        this.tcpPort = tcpPort;
        this.udpPort = udpPort;

        final Kryo kryo = getKryo();
        new Reflections("avdw.java.captain.sonar.core").getTypesAnnotatedWith(Message.class).stream().forEach(aClass -> {
            kryo.register(aClass);
        });

        listeners.stream().forEach(listener -> {
                    addListener(listener);
                    Logger.debug(String.format("registered %s", listener));
                });
    }

    @Override
    public void start() {
        super.start();
        try {
            bind(tcpPort, udpPort);
        } catch (IOException e) {
            Logger.error(e);
        }
        Logger.debug("endpoint exposed");
    }
}

package avdw.java.captain.sonar.server;

import com.esotericsoftware.kryonet.Server;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.pmw.tinylog.Logger;

import java.io.IOException;

public class ServerEndpoint extends Server {
    private final Integer tcpPort;
    private final Integer udpPort;

    @Inject
    public ServerEndpoint(@Named("tcp-port")Integer tcpPort, @Named("udp-port")Integer udpPort) {
        super();
        this.tcpPort = tcpPort;
        this.udpPort = udpPort;
    }

    @Override
    public void start() {
        super.start();
        try {
            bind(tcpPort, udpPort);
        } catch (IOException e) {
            Logger.error(e);
        }
    }
}

package avdw.java.captain.sonar.server;

import avdw.java.captain.sonar.server.network.PortListener;
import avdw.java.captain.sonar.server.network.TcpListener;
import avdw.java.captain.sonar.server.network.UdpListener;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Configurator.currentConfig()
                .formatPattern("{date:yyyy-MM-dd HH:mm:ss} [{thread}] {class}.{method}() {level}: {message}")
                .level(Level.TRACE)
                .activate();

        PortListener tcpListener = new TcpListener(43396);
        tcpListener.startListening();
        PortListener udpListener = new UdpListener(43397);
        udpListener.startListening();
    }
}

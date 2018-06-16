package avdw.java.captain.sonar.client;

import avdw.java.captain.sonar.client.transmitter.CaptainTransmitter;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Configurator.currentConfig()
                .formatPattern("{date:yyyy-MM-dd HH:mm:ss} [{thread}] {class}.{method}() {level}: {message}")
                .level(Level.TRACE)
                .activate();

        Logger.debug("client");

        ClientNetworkConfig networkConfig = new ClientNetworkConfig();

        CaptainTransmitter captainTransmitter = new CaptainTransmitter(networkConfig.transmitter);
        captainTransmitter.goNorth(null);
    }
}

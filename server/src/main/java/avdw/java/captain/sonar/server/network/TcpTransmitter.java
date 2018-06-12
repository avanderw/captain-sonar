package avdw.java.captain.sonar.server.network;

import org.pmw.tinylog.Logger;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TcpTransmitter implements PortTransmitter {
    private Socket socket;

    public TcpTransmitter(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void transmit(String data) {
        try (DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
            output.writeBytes(data);
        } catch (IOException e) {
            Logger.error(e.getMessage());
        }

        try {
            socket.close();
        } catch (IOException e) {
            Logger.error(e.getMessage());
        }
    }
}

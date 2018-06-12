package avdw.java.captain.sonar.server.network;

import avdw.java.captain.sonar.server.Constant;
import org.pmw.tinylog.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpListener extends PortListener {
    ServerSocket serverSocket;

    public TcpListener(Integer port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    PacketRouter listenForPacket() throws ListenException {
        try {
            Socket socket = serverSocket.accept();
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            char[] buffer = new char[Constant.PACKET_SIZE];
            input.read(buffer, 0, Constant.PACKET_SIZE);

            return new PacketRouter(socket.getInetAddress(), socket.getPort(), new String(buffer), new TcpTransmitter(socket));
        } catch (IOException e) {
            Logger.error(e);
            throw new ListenException();
        }
    }
}

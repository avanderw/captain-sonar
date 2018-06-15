package avdw.java.captain.sonar.server.network;


import avdw.java.captain.sonar.lib.Constant;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class EchoServer implements Runnable {
    private DatagramSocket socket;
    private volatile Thread thread;

    public EchoServer() throws SocketException {
        socket = new DatagramSocket(4445);
    }

    void start() {
        thread = new Thread(this);
        thread.start();
    }

    void stop() {
        thread = null;
    }

    @Override
    public void run() {
        byte[] buf = new byte[Constant.PACKET_SIZE];
        while (thread == Thread.currentThread()) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port);
            String received
                    = new String(packet.getData(), 0, packet.getLength());

            if (received.equals("end")) {
                continue;
            }
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }
}

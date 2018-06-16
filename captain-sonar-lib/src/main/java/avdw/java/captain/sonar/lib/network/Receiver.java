package avdw.java.captain.sonar.lib.network;

import avdw.java.captain.sonar.lib.Constant;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Receiver extends Daemon {
    private final DatagramSocket socket;
    private final NetworkConfig networkConfig;

    public Receiver(NetworkConfig networkConfig, DatagramSocket socket) {
        this.networkConfig = networkConfig;
        this.socket = socket;
    }

    public void run() {
        Logger.debug("started");
        ExecutorService routers = Executors.newCachedThreadPool();
        byte[] buffer = new byte[Constant.PACKET_SIZE];
        while (thread == Thread.currentThread()) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                routers.execute(new Router(networkConfig, packet));
            } catch (IOException e) {
                Logger.error(e.getMessage());
            }
        }
        Logger.debug("stopped");
    }
}

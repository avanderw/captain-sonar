package avdw.java.captain.sonar.server.network;

import org.pmw.tinylog.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class PortListener implements Runnable {
    private volatile Thread thread;

    public void startListening() {
        thread = new Thread(this);
        thread.start();
    }

    public void stopListening() {
        thread = null;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(getClass().getSimpleName());
        Logger.debug("started");

        ExecutorService packetRouters = Executors.newCachedThreadPool();
        while (thread == Thread.currentThread()) {
            try {
                packetRouters.execute(listenForPacket());
            } catch (ListenException e) {
                Logger.error(e.getMessage());
            }
        }
        Logger.debug("stopped");
    }

    abstract PacketRouter listenForPacket() throws ListenException;
}

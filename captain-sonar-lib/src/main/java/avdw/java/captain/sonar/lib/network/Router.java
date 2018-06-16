package avdw.java.captain.sonar.lib.network;

import avdw.java.captain.sonar.protocol.message.Envelope;
import com.google.gson.Gson;
import org.pmw.tinylog.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.DatagramPacket;

class Router implements Runnable {
    private final DatagramPacket packet;
    private final NetworkConfig networkConfig;
    private final Gson gson = new Gson();

    public Router(NetworkConfig networkConfig, DatagramPacket packet) {
        this.packet = packet;
        this.networkConfig = networkConfig;
    }

    public void run() {
        String json = new String(packet.getData()).trim();
        Envelope envelope = gson.fromJson(json, Envelope.class);

        Logger.trace(String.format("routing to address <%s:%s>", envelope.address, envelope.operation));
        PacketReceiver packetReceiver = networkConfig.address(envelope.address);
        Method method = packetReceiver.operation(envelope.operation);
        try {
            method.invoke(packetReceiver, packet);
        } catch (IllegalAccessException | InvocationTargetException e) {
            Logger.error(e);
        }
    }
}

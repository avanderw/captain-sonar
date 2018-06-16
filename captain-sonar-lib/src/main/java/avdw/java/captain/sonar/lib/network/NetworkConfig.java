package avdw.java.captain.sonar.lib.network;

import avdw.java.captain.sonar.protocol.Address;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class NetworkConfig {
    protected final Map<String, PacketReceiver> receiverMap;

    public NetworkConfig() {
        receiverMap = new HashMap();
    }

    PacketReceiver address(String address) {
        PacketReceiver packetReceiver = receiverMap.get(address);
        if (packetReceiver == null) {
            throw new UnsupportedOperationException(String.format("address <%s> not registered", address));
        } else {
            return packetReceiver;
        }
    }


    protected void registerReceivers(String prefix, Transmitter transmitter) {
        Reflections reflections = new Reflections(prefix);
        Set<Class<?>> handlers = reflections.getTypesAnnotatedWith(Address.class);
        handlers.forEach(handler -> {
            Address handlerAnnotation = handler.getAnnotation(Address.class);
            try {
                Logger.debug(String.format("registering receiver [ %s => %s ]", handlerAnnotation.value(), handler.getSimpleName()));

                PacketReceiver packetReceiver = (PacketReceiver) handler.getConstructor(Transmitter.class).newInstance(transmitter);
                Arrays.stream(packetReceiver.getClass().getInterfaces())
                        .filter(i -> i.isAnnotationPresent(Address.class))
                        .flatMap(i -> Arrays.stream(i.getDeclaredMethods()))
                        .forEach(m -> {
                            Logger.trace(String.format("registering operation [ %s ]", m.getName()));
                            packetReceiver.putOperation(m.getName(), m);
                        });

                receiverMap.put(handlerAnnotation.value(), packetReceiver);
            } catch (InstantiationException | IllegalAccessException e) {
                Logger.error(e);
            } catch (NoSuchMethodException | InvocationTargetException e) {
                Logger.error(e);
            }
        });
    }
}

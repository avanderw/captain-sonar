package avdw.java.captain.sonar.protocol;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import org.reflections.Reflections;

public class Protocol {
    public static void setup(EndPoint endPoint) {

        Reflections reflections = new Reflections("avdw.java.captain.sonar.protocol");

        Kryo kryo = endPoint.getKryo();
        reflections.getTypesAnnotatedWith(Message.class).stream().forEach(aClass -> {
            kryo.register(aClass);
        });
    }
}

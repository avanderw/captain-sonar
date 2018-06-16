package avdw.java.captain.sonar.protocol.message;

import avdw.java.captain.sonar.protocol.Address;
import com.google.gson.Gson;

public class Envelope {
    public String address;
    public String operation;

    public Envelope() {
        if (getClass().getAnnotation(Address.class) != null) {
            address = getClass().getAnnotation(Address.class).value();
        }
    }

    public byte[] serialize() {
        return new Gson().toJson(this).getBytes();
    }

    @Override
    public String toString() {
        return String.format("%s", address);
    }

}

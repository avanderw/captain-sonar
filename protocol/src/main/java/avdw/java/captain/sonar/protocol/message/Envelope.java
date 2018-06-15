package avdw.java.captain.sonar.protocol.message;

import com.google.gson.Gson;

public class Envelope {
    public String target;

    public Envelope(String target) {
        this.target = target;
    }

    public byte[] serialize() {
        return new Gson().toJson(this).getBytes();
    }
}

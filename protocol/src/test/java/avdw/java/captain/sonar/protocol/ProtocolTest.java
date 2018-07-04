package avdw.java.captain.sonar.protocol;

import avdw.java.captain.sonar.protocol.captain.PlaceShipMessage;
import com.esotericsoftware.kryonet.Client;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ProtocolTest {

    @Test
    public void test_setupMessages_Successful() {
        Client client = new Client();
        Protocol.setup(client);
        assertNotNull(client.getKryo().getRegistration(PlaceShipMessage.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_setupMessages_Exception() {
        Client client = new Client();
        Protocol.setup(client);
        assertNotNull(client.getKryo().getRegistration(Protocol.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_setupMessages_Unsuccessful() {
        Client client = new Client();
        client.getKryo().getRegistration(PlaceShipMessage.class);
    }
}
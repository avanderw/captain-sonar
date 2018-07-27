package avdw.java.captain.sonar.core.messages.lobby.message;

import avdw.java.captain.sonar.core.messages.Message;

@Message("RegisterName")
public class RegisterNameMessage {
    public String name;
    public Integer clientId;
}

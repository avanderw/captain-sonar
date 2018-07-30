package avdw.java.captain.sonar.client.lobby;

import avdw.java.captain.sonar.client.lobby.action.ConnectAction;
import avdw.java.captain.sonar.client.lobby.action.DisconnectAction;
import avdw.java.captain.sonar.client.lobby.action.RegisterNameAction;
import avdw.java.cli.menu.Action;
import avdw.java.cli.menu.Menu;
import com.google.inject.Inject;
import javax.annotation.Generated;

@Generated(
        value = "avdw.java.captain.sonar.core.generator.ListenerGenerator",
        date = "2018-07-30T16:16:00.585"
)
public class LobbyMenu extends Menu {
    private RegisterNameAction registerName;

    private ConnectAction connect;

    private DisconnectAction disconnect;

    @Inject
    public LobbyMenu(RegisterNameAction registerName, ConnectAction connect,
            DisconnectAction disconnect) {
        super();
        this.registerName = registerName;
        this.connect = connect;
        this.disconnect = disconnect;
    }

    @Action
    public void registerName(String name) {
        registerName.registerName(name);
    }

    @Action
    public void connect() {
        connect.connect();
    }

    @Action
    public void disconnect() {
        disconnect.disconnect();
    }
}

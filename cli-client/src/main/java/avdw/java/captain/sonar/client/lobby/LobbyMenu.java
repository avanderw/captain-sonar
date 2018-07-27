package avdw.java.captain.sonar.client.lobby;

import avdw.java.captain.sonar.client.lobby.action.ConnectAction;
import avdw.java.captain.sonar.client.lobby.action.RegisterNameAction;
import avdw.java.cli.menu.Action;
import avdw.java.cli.menu.Menu;
import com.google.inject.Inject;
import javax.annotation.Generated;

@Generated(
        value = "avdw.java.captain.sonar.core.generator.ListenerGenerator",
        date = "2018-07-27T10:04:48.023"
)
public class LobbyMenu extends Menu {
    private RegisterNameAction registerName;

    private ConnectAction connect;

    @Inject
    public LobbyMenu(RegisterNameAction registerName, ConnectAction connect) {
        super();
        this.registerName = registerName;
        this.connect = connect;
    }

    @Action
    public void registerName(String name) {
        registerName.registerName(name);
    }

    @Action
    public void connect() {
        connect.connect();
    }
}

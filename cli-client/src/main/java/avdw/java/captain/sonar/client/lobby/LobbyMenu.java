package avdw.java.captain.sonar.client.lobby;

import avdw.java.captain.sonar.client.lobby.action.ConnectAction;
import avdw.java.cli.menu.Action;
import avdw.java.cli.menu.Menu;
import com.google.inject.Inject;
import javax.annotation.Generated;

@Generated(
        value = "avdw.java.captain.sonar.core.generator.ListenerGenerator",
        date = "2018-07-23T14:45:23.714"
)
public class LobbyMenu extends Menu {
    private ConnectAction connect;

    @Inject
    public LobbyMenu(ConnectAction connect) {
        super();
        this.connect = connect;
    }

    @Action
    public void connect() {
        connect.connect();
    }
}

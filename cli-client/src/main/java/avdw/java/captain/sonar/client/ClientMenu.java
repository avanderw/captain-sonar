package avdw.java.captain.sonar.client;

import avdw.java.captain.sonar.client.lobby.LobbyData;
import avdw.java.cli.menu.Action;
import avdw.java.cli.menu.Menu;
import com.google.inject.Inject;
import org.pmw.tinylog.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientMenu extends Menu {
    private final LobbyData lobbyData;

    @Inject
    ClientMenu(Set<Menu> items, LobbyData lobbyData) {
        this.lobbyData = lobbyData;
        items.forEach(item->{
            add(item);
        });
    }

    @Action
    public void connected() {
        Logger.debug(String.format("%s has %s connections", lobbyData, lobbyData.connected.size()));
        Logger.info(String.format("connected clients %s", lobbyData.connected));
    }
}

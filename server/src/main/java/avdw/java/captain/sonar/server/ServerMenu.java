package avdw.java.captain.sonar.server;

import avdw.java.cli.menu.Action;
import avdw.java.cli.menu.Menu;
import com.google.inject.Inject;
import org.pmw.tinylog.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ServerMenu extends Menu {
    private ServerEndpoint endpoint;

    @Inject
    ServerMenu(ServerEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    @Action
    public void connected() {
        List<String> connected = Arrays.stream(endpoint.getConnections())
                .map(conn -> (ServerConnection) conn)
                .map(conn -> String.format("%s:%s", conn.getID(), conn.name))
                .collect(Collectors.toList());

        Logger.debug(String.format("%s has %s connections", endpoint, endpoint.getConnections().length));
        Logger.info(String.format("connected clients %s", connected));
    }
}

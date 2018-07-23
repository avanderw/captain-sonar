package avdw.java.captain.sonar.client;

//import avdw.java.captain.sonar.client.captain.CaptainMenu;
//import avdw.java.captain.sonar.client.captain.action.ActivateDroneAction;
//import avdw.java.captain.sonar.client.engineer.EngineerMenu;
//import avdw.java.captain.sonar.client.lobby.LobbyMenu;
//import avdw.java.captain.sonar.client.officer.OfficerMenu;
//import avdw.java.captain.sonar.client.setup.SetupMenu;
import avdw.java.captain.sonar.core.config.StaticConfig;
import avdw.java.captain.sonar.core.messages.lobby.message.ConnectMessage;
import avdw.java.cli.menu.Menu;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;

public class ClientMain {
    public static void main(String[] args) {
        StaticConfig.configureLoggers(Level.DEBUG);
        Logger.debug("started");

        Injector injector = Guice.createInjector(new ClientModule());

        ClientConnection client = injector.getInstance(ClientConnection.class);
        client.start();
        client.connect();

        Menu menu = injector.getInstance(ClientMenu.class);
//        menu.add(injector.getInstance(CaptainMenu.class));
//        menu.add(injector.getInstance(EngineerMenu.class));
//        menu.add(injector.getInstance(LobbyMenu.class));
//        menu.add(injector.getInstance(OfficerMenu.class));
//        menu.add(injector.getInstance(SetupMenu.class));
        menu.display();

        client.stop();

        Logger.debug("stopped");
    }
}

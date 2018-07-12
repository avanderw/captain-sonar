package avdw.java.captain.sonar.server;

import avdw.java.captain.sonar.protocol.Protocol;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) {
        Configurator.currentConfig()
                .formatPattern("{date:yyyy-MM-dd HH:mm:ss} [{thread}] {class}.{method}() {level}: {message}")
                .level(Level.TRACE)
                .activate();

        Logger.debug("server");

        Server server = new Server();
        //server.start();
        try {
            server.bind(54555, 54777);
        } catch (IOException e) {
            Logger.error(e);
        }

        Protocol.setup(server);

        registerListeners(server);
    }

    private static void registerListeners(Server server) {
        Reflections reflections = new Reflections(String.format("avdw.java.captain.sonar.server"));

        reflections.getSubTypesOf(Listener.class).stream()
                .map(aClass -> {
                    try {
                        return aClass.newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        Logger.error(e);
                    }
                    return null;
                })
                .forEach(listener -> {
                    server.addListener(listener);
                    Logger.debug(String.format("registered %s", listener.getClass().getName()));
                });
    }
}

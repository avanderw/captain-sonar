package avdw.java.captain.sonar.server;

import avdw.java.captain.sonar.core.config.ConfigModule;
import com.esotericsoftware.kryonet.EndPoint;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

public class ServerModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ConfigModule());

        bind(ServerEndpoint.class).in(Singleton.class);
        bind(EndPoint.class).to(ServerEndpoint.class);

        Reflections modules = new Reflections(String.format("avdw.java.captain.sonar.server"));
        modules.getSubTypesOf(AbstractModule.class).stream()
                .filter(module -> !module.isAssignableFrom(ServerModule.class))
                .forEach(module -> {
                    try {
                        install(module.newInstance());
                        Logger.debug(String.format("installed module %s", module));
                    } catch (InstantiationException | IllegalAccessException e) {
                        Logger.error(e);
                    }
                });
    }
}

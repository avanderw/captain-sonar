package avdw.java.captain.sonar.client;

import avdw.java.captain.sonar.core.config.ConfigModule;
import avdw.java.captain.sonar.core.config.NetworkConfigModule;
import com.esotericsoftware.kryonet.EndPoint;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

public class ClientModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ConfigModule());

        bind(Integer.class).annotatedWith(Names.named("network-timeout")).toInstance(1000);
        bind(EndPoint.class).to(ClientEndpoint.class).in(Singleton.class);

        Reflections modules = new Reflections(String.format("avdw.java.captain.sonar.client"));
        modules.getSubTypesOf(AbstractModule.class).stream()
                .filter(module -> !module.isAssignableFrom(ClientModule.class))
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

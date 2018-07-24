package avdw.java.captain.sonar.client;

import avdw.java.captain.sonar.core.config.ConfigModule;
import avdw.java.cli.menu.Menu;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

public class ClientModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new ConfigModule());

        bind(Integer.class).annotatedWith(Names.named("network-timeout")).toInstance(1000);
        bind(ClientEndpoint.class).in(Singleton.class);
        bind(EndPoint.class).to(ClientEndpoint.class);
        bind(Connection.class).to(ClientEndpoint.class);

        Reflections reflections = new Reflections(String.format("avdw.java.captain.sonar.client"));
        reflections.getSubTypesOf(AbstractModule.class).stream()
                .filter(reflection -> !reflection.isAssignableFrom(ClientModule.class))
                .forEach(reflection -> {
                    try {
                        install(reflection.newInstance());
                        Logger.debug(String.format("installed module %s", reflection));
                    } catch (InstantiationException | IllegalAccessException e) {
                        Logger.error(e);
                    }
                });

        Multibinder<Menu> menuBinder = Multibinder.newSetBinder(binder(), Menu.class);
        reflections.getSubTypesOf(Menu.class).stream()
                .filter(reflection->!reflection.isAssignableFrom(ClientMenu.class))
                .forEach(reflection-> {
                    menuBinder.addBinding().to(reflection);
                    Logger.debug(String.format("added menu %s", reflection));
                });
    }
}

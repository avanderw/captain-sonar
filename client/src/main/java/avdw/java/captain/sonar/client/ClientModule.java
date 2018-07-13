package avdw.java.captain.sonar.client;

import com.google.inject.AbstractModule;
import org.pmw.tinylog.Logger;
import org.reflections.Reflections;

public class ClientModule extends AbstractModule {
    @Override
    protected void configure() {
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

package avdw.java.captain.sonar.core.config;

import com.google.inject.AbstractModule;

public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new NetworkConfigModule());
    }
}

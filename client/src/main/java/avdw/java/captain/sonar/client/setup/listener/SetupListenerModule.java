package avdw.java.captain.sonar.client.setup.listener;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import java.lang.Override;

public class SetupListenerModule extends AbstractModule {
    @Override
    public void configure() {
        bind(SetupMapListener.class).in(Singleton.class);
    }
}

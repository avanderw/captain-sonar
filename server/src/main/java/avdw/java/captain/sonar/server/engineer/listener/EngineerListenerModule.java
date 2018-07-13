package avdw.java.captain.sonar.server.engineer.listener;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import java.lang.Override;

public class EngineerListenerModule extends AbstractModule {
    @Override
    public void configure() {
        bind(BreakSystemListener.class).in(Singleton.class);
    }
}

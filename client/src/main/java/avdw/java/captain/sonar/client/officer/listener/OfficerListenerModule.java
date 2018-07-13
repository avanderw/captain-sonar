package avdw.java.captain.sonar.client.officer.listener;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import java.lang.Override;

public class OfficerListenerModule extends AbstractModule {
    @Override
    public void configure() {
        bind(ChargeSystemListener.class).in(Singleton.class);
    }
}

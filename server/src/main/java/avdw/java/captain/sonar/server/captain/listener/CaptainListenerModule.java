package avdw.java.captain.sonar.server.captain.listener;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import java.lang.Override;

public class CaptainListenerModule extends AbstractModule {
    @Override
    public void configure() {
        bind(ActivateDroneListener.class).in(Singleton.class);
        bind(MoveShipListener.class).in(Singleton.class);
        bind(PlaceShipListener.class).in(Singleton.class);
        bind(ActivateSilentRunningListener.class).in(Singleton.class);
        bind(SurfaceListener.class).in(Singleton.class);
        bind(ActivateSonarListener.class).in(Singleton.class);
    }
}

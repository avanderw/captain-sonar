package avdw.java.captain.sonar.client.captain.action;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import java.lang.Override;
import javax.annotation.Generated;

@Generated(
        value = "avdw.java.captain.sonar.core.generator.ListenerGenerator",
        date = "2018-07-18T13:25:20.801"
)
public class CaptainActionModule extends AbstractModule {
    @Override
    public void configure() {
        bind(ActivateDroneAction.class).in(Singleton.class);
        bind(ActivateSonarAction.class).in(Singleton.class);
        bind(SurfaceAction.class).in(Singleton.class);
        bind(ActivateSilentRunningAction.class).in(Singleton.class);
        bind(MoveShipAction.class).in(Singleton.class);
        bind(PlaceShipAction.class).in(Singleton.class);
    }
}

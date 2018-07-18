package avdw.java.captain.sonar.server.captain.listener;

import com.esotericsoftware.kryonet.Listener;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import java.lang.Override;
import javax.annotation.Generated;

@Generated(
        value = "avdw.java.captain.sonar.core.messages.ListenerGenerator",
        date = "2018-07-18T09:16:56.025"
)
public class CaptainListenerModule extends AbstractModule {
    @Override
    public void configure() {
        bind(MoveShipListener.class).in(Singleton.class);
        bind(ActivateDroneListener.class).in(Singleton.class);
        bind(SurfaceListener.class).in(Singleton.class);
        bind(PlaceShipListener.class).in(Singleton.class);
        bind(ActivateSonarListener.class).in(Singleton.class);
        bind(ActivateSilentRunningListener.class).in(Singleton.class);

        Multibinder<Listener> listenerBinder = Multibinder.newSetBinder(binder(), Listener.class);
        listenerBinder.addBinding().to(MoveShipListener.class);
        listenerBinder.addBinding().to(ActivateDroneListener.class);
        listenerBinder.addBinding().to(SurfaceListener.class);
        listenerBinder.addBinding().to(PlaceShipListener.class);
        listenerBinder.addBinding().to(ActivateSonarListener.class);
        listenerBinder.addBinding().to(ActivateSilentRunningListener.class);
    }
}

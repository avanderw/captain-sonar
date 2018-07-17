package avdw.java.captain.sonar.client.captain.listener;

import com.esotericsoftware.kryonet.Listener;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import java.lang.Override;
import javax.annotation.Generated;

@Generated(
        value = "avdw.java.captain.sonar.core.messages.ListenerGenerator",
        date = "2018-07-17T15:46:51.964"
)
public class CaptainListenerModule extends AbstractModule {
    @Override
    public void configure() {
        Multibinder<Listener> listenerBinder = Multibinder.newSetBinder(binder(), Listener.class);
        bind(SurfaceListener.class).in(Singleton.class);
        listenerBinder.addBinding().to(SurfaceListener.class);
        bind(PlaceShipListener.class).in(Singleton.class);
        listenerBinder.addBinding().to(PlaceShipListener.class);
        bind(ActivateSonarListener.class).in(Singleton.class);
        listenerBinder.addBinding().to(ActivateSonarListener.class);
        bind(ActivateDroneListener.class).in(Singleton.class);
        listenerBinder.addBinding().to(ActivateDroneListener.class);
        bind(ActivateSilentRunningListener.class).in(Singleton.class);
        listenerBinder.addBinding().to(ActivateSilentRunningListener.class);
        bind(MoveShipListener.class).in(Singleton.class);
        listenerBinder.addBinding().to(MoveShipListener.class);
    }
}

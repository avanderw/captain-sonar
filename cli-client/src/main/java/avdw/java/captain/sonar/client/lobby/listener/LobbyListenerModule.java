package avdw.java.captain.sonar.client.lobby.listener;

import com.esotericsoftware.kryonet.Listener;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import java.lang.Override;
import javax.annotation.Generated;

@Generated(
        value = "avdw.java.captain.sonar.core.generator.ListenerGenerator",
        date = "2018-07-30T16:16:00.149"
)
public class LobbyListenerModule extends AbstractModule {
    @Override
    public void configure() {
        bind(RegisterNameListener.class).in(Singleton.class);
        bind(ConnectListener.class).in(Singleton.class);
        bind(DisconnectListener.class).in(Singleton.class);

        Multibinder<Listener> listenerBinder = Multibinder.newSetBinder(binder(), Listener.class);
        listenerBinder.addBinding().to(RegisterNameListener.class);
        listenerBinder.addBinding().to(ConnectListener.class);
        listenerBinder.addBinding().to(DisconnectListener.class);
    }
}

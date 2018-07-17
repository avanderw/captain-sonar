package avdw.java.captain.sonar.client.officer.listener;

import com.esotericsoftware.kryonet.Listener;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import java.lang.Override;
import javax.annotation.Generated;

@Generated(
        value = "avdw.java.captain.sonar.core.messages.ListenerGenerator",
        date = "2018-07-17T15:46:52.037"
)
public class OfficerListenerModule extends AbstractModule {
    @Override
    public void configure() {
        Multibinder<Listener> listenerBinder = Multibinder.newSetBinder(binder(), Listener.class);
        bind(ChargeSystemListener.class).in(Singleton.class);
        listenerBinder.addBinding().to(ChargeSystemListener.class);
    }
}

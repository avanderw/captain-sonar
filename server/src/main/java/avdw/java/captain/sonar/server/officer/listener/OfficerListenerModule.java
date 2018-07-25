package avdw.java.captain.sonar.server.officer.listener;

import com.esotericsoftware.kryonet.Listener;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import java.lang.Override;
import javax.annotation.Generated;

@Generated(
        value = "avdw.java.captain.sonar.core.generator.ListenerGenerator",
        date = "2018-07-24T14:59:32.635"
)
public class OfficerListenerModule extends AbstractModule {
    @Override
    public void configure() {
        bind(ChargeSystemListener.class).in(Singleton.class);

        Multibinder<Listener> listenerBinder = Multibinder.newSetBinder(binder(), Listener.class);
        listenerBinder.addBinding().to(ChargeSystemListener.class);
    }
}

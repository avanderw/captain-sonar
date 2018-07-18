package avdw.java.captain.sonar.server.engineer.listener;

import com.esotericsoftware.kryonet.Listener;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import java.lang.Override;
import javax.annotation.Generated;

@Generated(
        value = "avdw.java.captain.sonar.core.messages.ListenerGenerator",
        date = "2018-07-18T09:16:56.037"
)
public class EngineerListenerModule extends AbstractModule {
    @Override
    public void configure() {
        bind(BreakSystemListener.class).in(Singleton.class);

        Multibinder<Listener> listenerBinder = Multibinder.newSetBinder(binder(), Listener.class);
        listenerBinder.addBinding().to(BreakSystemListener.class);
    }
}

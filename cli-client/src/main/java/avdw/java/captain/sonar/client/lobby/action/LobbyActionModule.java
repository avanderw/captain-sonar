package avdw.java.captain.sonar.client.lobby.action;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import java.lang.Override;
import javax.annotation.Generated;

@Generated(
        value = "avdw.java.captain.sonar.core.generator.ListenerGenerator",
        date = "2018-07-27T10:04:47.797"
)
public class LobbyActionModule extends AbstractModule {
    @Override
    public void configure() {
        bind(RegisterNameAction.class).in(Singleton.class);
        bind(ConnectAction.class).in(Singleton.class);
    }
}

package avdw.java.captain.sonar.client.lobby.action;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import java.lang.Override;
import javax.annotation.Generated;

@Generated(
        value = "avdw.java.captain.sonar.core.generator.ListenerGenerator",
        date = "2018-07-20T15:06:09.520"
)
public class LobbyActionModule extends AbstractModule {
    @Override
    public void configure() {
        bind(ConnectAction.class).in(Singleton.class);
    }
}

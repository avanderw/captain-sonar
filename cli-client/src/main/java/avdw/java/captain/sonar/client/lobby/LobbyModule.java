package avdw.java.captain.sonar.client.lobby;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class LobbyModule extends AbstractModule {
    @Override
    public void configure() {
        bind(LobbyData.class).in(Singleton.class);
    }
}

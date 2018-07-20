package avdw.java.captain.sonar.client.setup.action;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import java.lang.Override;
import javax.annotation.Generated;

@Generated(
        value = "avdw.java.captain.sonar.core.generator.ListenerGenerator",
        date = "2018-07-20T15:06:09.554"
)
public class SetupActionModule extends AbstractModule {
    @Override
    public void configure() {
        bind(SetupMapAction.class).in(Singleton.class);
    }
}

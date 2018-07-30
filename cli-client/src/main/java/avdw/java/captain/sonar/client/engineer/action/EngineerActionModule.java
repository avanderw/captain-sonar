package avdw.java.captain.sonar.client.engineer.action;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import java.lang.Override;
import javax.annotation.Generated;

@Generated(
        value = "avdw.java.captain.sonar.core.generator.ListenerGenerator",
        date = "2018-07-30T12:42:27.202"
)
public class EngineerActionModule extends AbstractModule {
    @Override
    public void configure() {
        bind(BreakSystemAction.class).in(Singleton.class);
    }
}

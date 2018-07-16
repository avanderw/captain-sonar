package avdw.java.captain.sonar.lib;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class NetworkConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Integer.class).annotatedWith(Names.named("tcp-port")).toInstance(54555);
        bind(Integer.class).annotatedWith(Names.named("udp-port")).toInstance(54777);
    }
}

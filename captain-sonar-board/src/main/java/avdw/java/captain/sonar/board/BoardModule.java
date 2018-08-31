package avdw.java.captain.sonar.board;

import com.google.inject.AbstractModule;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BoardModule extends AbstractModule {
    @Override
    public void configure() {
        bind(Path.class).annotatedWith(Names.named("base-path")).toInstance(Paths.get("captain-sonar-board/src/main/resources/"));
        bind(Path.class).annotatedWith(Names.named("schema-path")).toInstance(Paths.get("captain-sonar-board/src/main/resources/board.schema"));
    }
}

package avdw.java.captain.sonar.client.setup;

import avdw.java.captain.sonar.client.setup.action.SetupMapAction;
import avdw.java.cli.menu.Action;
import avdw.java.cli.menu.Menu;
import com.google.inject.Inject;
import javax.annotation.Generated;

@Generated(
        value = "avdw.java.captain.sonar.core.generator.ListenerGenerator",
        date = "2018-07-23T14:45:23.743"
)
public class SetupMenu extends Menu {
    private SetupMapAction setupMap;

    @Inject
    public SetupMenu(SetupMapAction setupMap) {
        super();
        this.setupMap = setupMap;
    }

    @Action
    public void setupMap() {
        setupMap.setupMap();
    }
}

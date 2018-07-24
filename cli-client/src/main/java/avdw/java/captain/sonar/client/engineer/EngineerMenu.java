package avdw.java.captain.sonar.client.engineer;

import avdw.java.captain.sonar.client.engineer.action.BreakSystemAction;
import avdw.java.cli.menu.Action;
import avdw.java.cli.menu.Menu;
import com.google.inject.Inject;
import javax.annotation.Generated;

@Generated(
        value = "avdw.java.captain.sonar.core.generator.ListenerGenerator",
        date = "2018-07-24T14:57:42.586"
)
public class EngineerMenu extends Menu {
    private BreakSystemAction breakSystem;

    @Inject
    public EngineerMenu(BreakSystemAction breakSystem) {
        super();
        this.breakSystem = breakSystem;
    }

    @Action
    public void breakSystem() {
        breakSystem.breakSystem();
    }
}

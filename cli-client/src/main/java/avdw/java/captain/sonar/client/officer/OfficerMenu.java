package avdw.java.captain.sonar.client.officer;

import avdw.java.captain.sonar.client.officer.action.ChargeSystemAction;
import avdw.java.cli.menu.Action;
import avdw.java.cli.menu.Menu;
import com.google.inject.Inject;
import javax.annotation.Generated;

@Generated(
        value = "avdw.java.captain.sonar.core.generator.ListenerGenerator",
        date = "2018-07-27T10:04:48.052"
)
public class OfficerMenu extends Menu {
    private ChargeSystemAction chargeSystem;

    @Inject
    public OfficerMenu(ChargeSystemAction chargeSystem) {
        super();
        this.chargeSystem = chargeSystem;
    }

    @Action
    public void chargeSystem() {
        chargeSystem.chargeSystem();
    }
}

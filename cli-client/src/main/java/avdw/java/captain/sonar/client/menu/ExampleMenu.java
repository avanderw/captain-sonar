package avdw.java.captain.sonar.client.menu;

import org.pmw.tinylog.Logger;

public class ExampleMenu extends Menu {
    public void someMethod() {

    }

    @Action
    public void someMenuAction() {
        Logger.info("someMenuAction");
    }
}

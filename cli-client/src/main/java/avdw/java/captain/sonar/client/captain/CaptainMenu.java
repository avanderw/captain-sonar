package avdw.java.captain.sonar.client.captain;

import avdw.java.captain.sonar.client.captain.action.ActivateDroneAction;
import avdw.java.captain.sonar.client.captain.action.ActivateSilentRunningAction;
import avdw.java.captain.sonar.client.captain.action.ActivateSonarAction;
import avdw.java.captain.sonar.client.captain.action.MoveShipAction;
import avdw.java.captain.sonar.client.captain.action.PlaceShipAction;
import avdw.java.captain.sonar.client.captain.action.SurfaceAction;
import avdw.java.cli.menu.Action;
import avdw.java.cli.menu.Menu;
import com.google.inject.Inject;
import javax.annotation.Generated;

@Generated(
        value = "avdw.java.captain.sonar.core.generator.ListenerGenerator",
        date = "2018-07-23T14:45:23.754"
)
public class CaptainMenu extends Menu {
    private MoveShipAction moveShip;

    private ActivateDroneAction activateDrone;

    private SurfaceAction surface;

    private PlaceShipAction placeShip;

    private ActivateSonarAction activateSonar;

    private ActivateSilentRunningAction activateSilentRunning;

    @Inject
    public CaptainMenu(MoveShipAction moveShip, ActivateDroneAction activateDrone,
            SurfaceAction surface, PlaceShipAction placeShip, ActivateSonarAction activateSonar,
            ActivateSilentRunningAction activateSilentRunning) {
        super();
        this.moveShip = moveShip;
        this.activateDrone = activateDrone;
        this.surface = surface;
        this.placeShip = placeShip;
        this.activateSonar = activateSonar;
        this.activateSilentRunning = activateSilentRunning;
    }

    @Action
    public void moveShip() {
        moveShip.moveShip();
    }

    @Action
    public void activateDrone() {
        activateDrone.activateDrone();
    }

    @Action
    public void surface() {
        surface.surface();
    }

    @Action
    public void placeShip() {
        placeShip.placeShip();
    }

    @Action
    public void activateSonar() {
        activateSonar.activateSonar();
    }

    @Action
    public void activateSilentRunning() {
        activateSilentRunning.activateSilentRunning();
    }
}

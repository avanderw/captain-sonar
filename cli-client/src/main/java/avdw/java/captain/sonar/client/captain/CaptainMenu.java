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
        date = "2018-07-27T10:04:48.070"
)
public class CaptainMenu extends Menu {
    private PlaceShipAction placeShip;

    private SurfaceAction surface;

    private MoveShipAction moveShip;

    private ActivateSonarAction activateSonar;

    private ActivateDroneAction activateDrone;

    private ActivateSilentRunningAction activateSilentRunning;

    @Inject
    public CaptainMenu(PlaceShipAction placeShip, SurfaceAction surface, MoveShipAction moveShip,
            ActivateSonarAction activateSonar, ActivateDroneAction activateDrone,
            ActivateSilentRunningAction activateSilentRunning) {
        super();
        this.placeShip = placeShip;
        this.surface = surface;
        this.moveShip = moveShip;
        this.activateSonar = activateSonar;
        this.activateDrone = activateDrone;
        this.activateSilentRunning = activateSilentRunning;
    }

    @Action
    public void placeShip() {
        placeShip.placeShip();
    }

    @Action
    public void surface() {
        surface.surface();
    }

    @Action
    public void moveShip() {
        moveShip.moveShip();
    }

    @Action
    public void activateSonar() {
        activateSonar.activateSonar();
    }

    @Action
    public void activateDrone() {
        activateDrone.activateDrone();
    }

    @Action
    public void activateSilentRunning() {
        activateSilentRunning.activateSilentRunning();
    }
}

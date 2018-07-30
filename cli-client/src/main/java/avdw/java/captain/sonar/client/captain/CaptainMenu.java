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
        date = "2018-07-30T12:42:27.419"
)
public class CaptainMenu extends Menu {
    private ActivateSonarAction activateSonar;

    private ActivateDroneAction activateDrone;

    private MoveShipAction moveShip;

    private PlaceShipAction placeShip;

    private ActivateSilentRunningAction activateSilentRunning;

    private SurfaceAction surface;

    @Inject
    public CaptainMenu(ActivateSonarAction activateSonar, ActivateDroneAction activateDrone,
            MoveShipAction moveShip, PlaceShipAction placeShip,
            ActivateSilentRunningAction activateSilentRunning, SurfaceAction surface) {
        super();
        this.activateSonar = activateSonar;
        this.activateDrone = activateDrone;
        this.moveShip = moveShip;
        this.placeShip = placeShip;
        this.activateSilentRunning = activateSilentRunning;
        this.surface = surface;
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
    public void moveShip() {
        moveShip.moveShip();
    }

    @Action
    public void placeShip() {
        placeShip.placeShip();
    }

    @Action
    public void activateSilentRunning() {
        activateSilentRunning.activateSilentRunning();
    }

    @Action
    public void surface() {
        surface.surface();
    }
}

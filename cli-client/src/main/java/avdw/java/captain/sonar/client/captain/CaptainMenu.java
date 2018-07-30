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
        date = "2018-07-30T16:16:00.616"
)
public class CaptainMenu extends Menu {
    private ActivateSonarAction activateSonar;

    private MoveShipAction moveShip;

    private SurfaceAction surface;

    private ActivateDroneAction activateDrone;

    private PlaceShipAction placeShip;

    private ActivateSilentRunningAction activateSilentRunning;

    @Inject
    public CaptainMenu(ActivateSonarAction activateSonar, MoveShipAction moveShip,
            SurfaceAction surface, ActivateDroneAction activateDrone, PlaceShipAction placeShip,
            ActivateSilentRunningAction activateSilentRunning) {
        super();
        this.activateSonar = activateSonar;
        this.moveShip = moveShip;
        this.surface = surface;
        this.activateDrone = activateDrone;
        this.placeShip = placeShip;
        this.activateSilentRunning = activateSilentRunning;
    }

    @Action
    public void activateSonar() {
        activateSonar.activateSonar();
    }

    @Action
    public void moveShip() {
        moveShip.moveShip();
    }

    @Action
    public void surface() {
        surface.surface();
    }

    @Action
    public void activateDrone() {
        activateDrone.activateDrone();
    }

    @Action
    public void placeShip() {
        placeShip.placeShip();
    }

    @Action
    public void activateSilentRunning() {
        activateSilentRunning.activateSilentRunning();
    }
}

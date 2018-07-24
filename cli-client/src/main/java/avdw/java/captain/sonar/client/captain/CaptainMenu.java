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
        date = "2018-07-24T14:57:42.574"
)
public class CaptainMenu extends Menu {
    private PlaceShipAction placeShip;

    private SurfaceAction surface;

    private MoveShipAction moveShip;

    private ActivateSilentRunningAction activateSilentRunning;

    private ActivateSonarAction activateSonar;

    private ActivateDroneAction activateDrone;

    @Inject
    public CaptainMenu(PlaceShipAction placeShip, SurfaceAction surface, MoveShipAction moveShip,
            ActivateSilentRunningAction activateSilentRunning, ActivateSonarAction activateSonar,
            ActivateDroneAction activateDrone) {
        super();
        this.placeShip = placeShip;
        this.surface = surface;
        this.moveShip = moveShip;
        this.activateSilentRunning = activateSilentRunning;
        this.activateSonar = activateSonar;
        this.activateDrone = activateDrone;
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
    public void activateSilentRunning() {
        activateSilentRunning.activateSilentRunning();
    }

    @Action
    public void activateSonar() {
        activateSonar.activateSonar();
    }

    @Action
    public void activateDrone() {
        activateDrone.activateDrone();
    }
}

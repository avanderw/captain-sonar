package avdw.java.captain.sonar.client.captain;

import avdw.java.captain.sonar.client.captain.action.ActivateDroneAction;

public class CaptainMenu {
    void display() {
        System.out.println("1. activate sonar");
    }

    void activateDrone(ActivateDroneAction action){
        action.activateDrone();
    }
}

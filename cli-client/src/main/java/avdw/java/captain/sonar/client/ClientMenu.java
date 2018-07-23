package avdw.java.captain.sonar.client;

import avdw.java.cli.menu.Menu;
import com.google.inject.Inject;

import java.util.Set;

public class ClientMenu extends Menu {
    @Inject
    ClientMenu(Set<Menu> items) {
        items.forEach(item->{
            add(item);
        });
    }
}

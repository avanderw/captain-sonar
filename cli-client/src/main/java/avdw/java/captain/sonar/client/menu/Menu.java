package avdw.java.captain.sonar.client.menu;

import org.pmw.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Menu {
    List<MenuItem> menuItemList = new ArrayList();

    public Menu() {

    }

    public void display() {
        IntStream.range(0, menuItemList.size()).forEach(idx -> {
            System.out.println(String.format("%4s: %s", idx, menuItemList.get(idx).title));
        });
    }

    public void add(Object menuItem) {
        if (Menu.class.isInstance(menuItem)) {

        } else {

        }
    }

    public void addSeparator() {

    }
}

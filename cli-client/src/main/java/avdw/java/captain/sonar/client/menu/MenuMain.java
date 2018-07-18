package avdw.java.captain.sonar.client.menu;

import avdw.java.captain.sonar.core.config.StaticConfig;
import org.pmw.tinylog.Level;

public class MenuMain {

    public static void main(String[] args) {
        StaticConfig.configureLoggers(Level.DEBUG);
        Menu menu = new Menu();
        menu.add(new ExampleMenu());
        menu.add(new ExampleClass());
        menu.add(new ExampleMenu());
        menu.add(new ExampleClass());
        menu.addSeparator();
        menu.add(new ExampleClass());
        menu.add(new ExampleClass());
        menu.display();
    }
}

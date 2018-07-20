package avdw.java.captain.sonar.core.generator;

public class Generator {
    private String aPackage;

    public Generator(String aPackage) {
        this.aPackage = aPackage;
    }

    public void generateListeners() {
        ListenerGenerator.generate(aPackage);
    }

    public void generateActions() {
        ActionGenerator.generate(aPackage);
    }

    public void generateEvents() {
        EventGenerator.generate(aPackage);
    }

    public void generateMenus() {
        MenuGenerator.generate(aPackage);
    }
}

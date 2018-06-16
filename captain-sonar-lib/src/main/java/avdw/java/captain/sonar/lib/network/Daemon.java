package avdw.java.captain.sonar.lib.network;

abstract class Daemon implements Runnable {
    volatile Thread thread;

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        thread = null;
    }
}

package avdw.java.captain.sonar.core.config;

import com.esotericsoftware.minlog.Log;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;

public class StaticConfig {
    public static void configureLoggers(Level level) {
        switch (level) {
            case TRACE:
                Log.set(Log.LEVEL_TRACE);
                break;
            case DEBUG:
                Log.set(Log.LEVEL_DEBUG);
                break;
            case INFO:
                Log.set(Log.LEVEL_INFO);
                break;
            case WARNING:
                Log.set(Log.LEVEL_WARN);
                break;
            case ERROR:
                Log.set(Log.LEVEL_ERROR);
                break;
            case OFF:
                Log.set(Log.LEVEL_NONE);
                break;
        }

        Configurator.currentConfig()
                .formatPattern("{date:yyyy-MM-dd HH:mm:ss} [{thread}] {class}.{method}() {level}: {message}")
                .level(level)
                .activate();
    }
}

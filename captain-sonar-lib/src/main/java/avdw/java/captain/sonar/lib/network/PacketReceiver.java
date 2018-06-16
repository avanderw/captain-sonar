package avdw.java.captain.sonar.lib.network;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class PacketReceiver {
    private final Map<String, Method> operationHandlers = new HashMap();

    protected PacketReceiver() {
    }

    Method operation(String operation) {
        Method operationHandler = operationHandlers.get(operation);
        if (operationHandler == null) {
            throw new UnsupportedOperationException(String.format("transmitter <%s> not registered", operation));
        } else {
            return operationHandler;
        }
    }

    public void putOperation(String name, Method operation) {
        operationHandlers.put(name, operation);
    }
}

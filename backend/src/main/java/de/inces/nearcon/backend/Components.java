package de.inces.nearcon.backend;

import java.util.HashMap;
import java.util.Map;

import de.inces.nearcon.backend.persistence.PersistenceManager;

public class Components {

    private static Map<String, Object> container = new HashMap<>();

    static {
        // Create persistence manager
        register(new PersistenceManager());
    }

    public static void register(Object obj) {
        container.put(obj.getClass().getName(), obj);
    }

    public static <T> T get(Class<T> type) {
        return type.cast(container.get(type.getName()));
    }

}

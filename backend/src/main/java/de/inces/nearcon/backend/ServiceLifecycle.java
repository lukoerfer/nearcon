package de.inces.nearcon.backend;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import de.inces.nearcon.backend.persistence.PersistenceManager;

public class ServiceLifecycle implements ServletContextListener {

    // @Inject
    private PersistenceManager persistenceManager;

    public ServiceLifecycle() {
        this.persistenceManager = Components.get(PersistenceManager.class);
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        this.persistenceManager.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        this.persistenceManager.close();
    }

}

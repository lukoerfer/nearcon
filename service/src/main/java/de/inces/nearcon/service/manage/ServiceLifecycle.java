package de.inces.nearcon.service.manage;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServiceLifecycle implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("Initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("Destroyed");
    }
}

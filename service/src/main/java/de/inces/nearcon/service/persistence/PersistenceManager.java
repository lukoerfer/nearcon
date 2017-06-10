package de.inces.nearcon.service.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lombok.Getter;

public class PersistenceManager {

    @Getter
    private static PersistenceManager Instance;

    private EntityManagerFactory entityManagerFactory;

    private PersistenceManager() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("default-persistence");
    }

    public static EntityManager getEntityManager() {
        return Instance.entityManagerFactory.createEntityManager();
    }

    public static void close() {
        Instance.entityManagerFactory.close();
    }

}

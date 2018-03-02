package de.inces.nearcon.backend.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {

    private EntityManagerFactory entityManagerFactory;

    public void init() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("default-persistence");
    }

    public EntityManager getEntityManager() {
        return this.entityManagerFactory.createEntityManager();
    }

    public EntityAccess readable() {
        return new EntityAccess(this.getEntityManager());
    }

    public EntityAccess writable() {
        return new EntityAccess(this.getEntityManager(), true);
    }

    public EntityAccess rollback() {
        return new EntityAccess(this.getEntityManager(), true, true);
    }

    public void close() {
        this.entityManagerFactory.close();
    }

}

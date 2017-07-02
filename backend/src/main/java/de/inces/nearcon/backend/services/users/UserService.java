package de.inces.nearcon.backend.services.users;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import de.inces.nearcon.backend.Components;
import de.inces.nearcon.backend.persistence.EntityAccess;
import de.inces.nearcon.backend.persistence.PersistenceManager;
import de.inces.nearcon.core.model.users.User;

public class UserService {

    private PersistenceManager persistenceManager;

    public UserService() {
        this.persistenceManager = Components.get(PersistenceManager.class);
    }

    public User create() {
        try (EntityAccess access = persistenceManager.writing()) {
            User user = new User();
            access.persist(user);
            return user;
        }
    }

    public User get(long id) {
        try (EntityAccess access = persistenceManager.reading()) {
            return access.find(User.class, id);
        }
    }

    public List<User> list() {
        try (EntityAccess access = persistenceManager.reading()) {
            CriteriaQuery<User> query = access.createCriteriaQuery(User.class);
            query.select(query.from(User.class));
            return access.createQuery(query).getResultList();
        }
    }

}

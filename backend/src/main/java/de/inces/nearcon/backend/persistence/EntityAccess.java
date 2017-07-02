package de.inces.nearcon.backend.persistence;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

import lombok.NonNull;

public class EntityAccess implements AutoCloseable, EntityManager, EntityTransaction {

    private EntityManager entityManager;
    private EntityTransaction transaction;

    public EntityAccess(@NonNull EntityManager entityManager) {
        this(entityManager, false);
    }

    public EntityAccess(@NonNull EntityManager entityManager, boolean beginTransaction) {
        this(entityManager, beginTransaction, false);
    }

    public EntityAccess(@NonNull EntityManager entityManager, boolean beginTransaction, boolean rollbackOnly) {
        this.entityManager = entityManager;
        this.transaction = entityManager.getTransaction();
        if (beginTransaction) {
            this.transaction.begin();
        }
        if (rollbackOnly) {
            this.transaction.setRollbackOnly();
        }
    }

    @Override
    public void close() {
        if (this.transaction.isActive()) {
            if (this.transaction.getRollbackOnly()) {
                this.transaction.rollback();
            } else {
                this.transaction.commit();
            }
        }
        this.entityManager.close();
    }

    public <T> CriteriaQuery<T> createCriteriaQuery(Class<T> resultClass) {
        return this.entityManager.getCriteriaBuilder().createQuery(resultClass);
    }

    //region | EntityManager |

    @Override
    public void persist(Object entity) {
        this.entityManager.persist(entity);
    }

    @Override
    public <T> T merge(T entity) {
        return this.entityManager.merge(entity);
    }

    @Override
    public void remove(Object entity) {
        this.entityManager.remove(entity);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey) {
        return this.entityManager.find(entityClass, primaryKey);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties) {
        return this.entityManager.find(entityClass, primaryKey, properties);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode) {
        return this.entityManager.find(entityClass, primaryKey, lockMode);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, Map<String, Object> properties) {
        return this.entityManager.find(entityClass, primaryKey, lockMode, properties);
    }

    @Override
    public <T> T getReference(Class<T> entityClass, Object primaryKey) {
        return this.entityManager.getReference(entityClass, primaryKey);
    }

    @Override
    public void flush() {
        this.entityManager.flush();
    }

    @Override
    public void setFlushMode(FlushModeType flushMode) {
        this.entityManager.setFlushMode(flushMode);
    }

    @Override
    public FlushModeType getFlushMode() {
        return this.entityManager.getFlushMode();
    }

    @Override
    public void lock(Object entity, LockModeType lockMode) {
        this.entityManager.lock(entity, lockMode);
    }

    @Override
    public void lock(Object entity, LockModeType lockMode, Map<String, Object> properties) {
        this.entityManager.lock(entity, lockMode, properties);
    }

    @Override
    public void refresh(Object entity) {
        this.entityManager.refresh(entity);
    }

    @Override
    public void refresh(Object entity, Map<String, Object> properties) {
        this.entityManager.refresh(entity, properties);
    }

    @Override
    public void refresh(Object entity, LockModeType lockMode) {
        this.entityManager.refresh(entity, lockMode);
    }

    @Override
    public void refresh(Object entity, LockModeType lockMode, Map<String, Object> properties) {
        this.entityManager.refresh(entity, lockMode, properties);
    }

    @Override
    public void clear() {
        this.entityManager.clear();
    }

    @Override
    public void detach(Object entity) {
        this.entityManager.detach(entity);
    }

    @Override
    public boolean contains(Object entity) {
        return this.entityManager.contains(entity);
    }

    @Override
    public LockModeType getLockMode(Object entity) {
        return this.entityManager.getLockMode(entity);
    }

    @Override
    public void setProperty(String propertyName, Object value) {
        this.entityManager.setProperty(propertyName, value);
    }

    @Override
    public Map<String, Object> getProperties() {
        return this.entityManager.getProperties();
    }

    @Override
    public Query createQuery(String qlString) {
        return this.entityManager.createQuery(qlString);
    }

    @Override
    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
        return this.entityManager.createQuery(criteriaQuery);
    }

    @Override
    public Query createQuery(CriteriaUpdate updateQuery) {
        return this.entityManager.createQuery(updateQuery);
    }

    @Override
    public Query createQuery(CriteriaDelete deleteQuery) {
        return this.entityManager.createQuery(deleteQuery);
    }

    @Override
    public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass) {
        return this.entityManager.createQuery(qlString, resultClass);
    }

    @Override
    public Query createNamedQuery(String name) {
        return this.entityManager.createNamedQuery(name);
    }

    @Override
    public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
        return this.entityManager.createNamedQuery(name, resultClass);
    }

    @Override
    public Query createNativeQuery(String sqlString) {
        return this.entityManager.createNativeQuery(sqlString);
    }

    @Override
    public Query createNativeQuery(String sqlString, Class resultClass) {
        return this.entityManager.createNativeQuery(sqlString, resultClass);
    }

    @Override
    public Query createNativeQuery(String sqlString, String resultSetMapping) {
        return this.entityManager.createNativeQuery(sqlString, resultSetMapping);
    }

    @Override
    public StoredProcedureQuery createNamedStoredProcedureQuery(String name) {
        return this.entityManager.createNamedStoredProcedureQuery(name);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName) {
        return this.entityManager.createStoredProcedureQuery(procedureName);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, Class... resultClasses) {
        return this.entityManager.createStoredProcedureQuery(procedureName, resultClasses);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, String... resultSetMappings) {
        return this.entityManager.createStoredProcedureQuery(procedureName, resultSetMappings);
    }

    @Override
    public void joinTransaction() {
        this.entityManager.joinTransaction();
    }

    @Override
    public boolean isJoinedToTransaction() {
        return this.entityManager.isJoinedToTransaction();
    }

    @Override
    public <T> T unwrap(Class<T> cls) {
        return this.entityManager.unwrap(cls);
    }

    @Override
    public Object getDelegate() {
        return this.entityManager.getDelegate();
    }

    @Override
    public boolean isOpen() {
        return this.entityManager.isOpen();
    }

    @Override
    public EntityTransaction getTransaction() {
        return this.entityManager.getTransaction();
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return this.entityManager.getEntityManagerFactory();
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return this.entityManager.getCriteriaBuilder();
    }

    @Override
    public Metamodel getMetamodel() {
        return this.entityManager.getMetamodel();
    }

    @Override
    public <T> EntityGraph<T> createEntityGraph(Class<T> rootType) {
        return this.entityManager.createEntityGraph(rootType);
    }

    @Override
    public EntityGraph<?> createEntityGraph(String graphName) {
        return this.entityManager.createEntityGraph(graphName);
    }

    @Override
    public EntityGraph<?> getEntityGraph(String graphName) {
        return this.entityManager.getEntityGraph(graphName);
    }

    @Override
    public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> entityClass) {
        return this.entityManager.getEntityGraphs(entityClass);
    }

    //endregion

    //region | EntityTransaction |

    @Override
    public void begin() {
        this.transaction.begin();
    }

    @Override
    public void commit() {
        this.transaction.commit();
    }

    @Override
    public void rollback() {
        this.transaction.rollback();
    }

    @Override
    public void setRollbackOnly() {
        this.transaction.setRollbackOnly();
    }

    @Override
    public boolean getRollbackOnly() {
        return this.transaction.getRollbackOnly();
    }

    @Override
    public boolean isActive() {
        return this.transaction.isActive();
    }

    //endregion
}

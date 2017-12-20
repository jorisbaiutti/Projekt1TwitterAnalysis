package ch.bfh.repositories;


import ch.bfh.entities.TwitterEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @param <T>
 *     Generic Repository for the communication with the Database
 */
public abstract class Repository<T extends TwitterEntity> extends java.util.Observable {


    EntityManager entityManager;
    @Autowired
    public Repository(ch.bfh.util.EntityManager entityManager) {
        this.entityManager = entityManager.getEntityManager();
    }

    /**
     *
     * @param entity save or update this entity to DB and notify the observers
     */
    public void save(TwitterEntity entity) {

        entityManager.getTransaction().begin();

        try{
            entityManager.persist(entity);
        } finally {
            entityManager.merge(entity);
        }

        entityManager.getTransaction().commit();
        setChanged();
        notifyObservers();

    }

    /**
     *
     * @param entity save or update this entity to DB and notify the observers
     * @return return the updated entity
     */
    public TwitterEntity update(TwitterEntity entity) {
        this.save(entity);
        return entity;
    }

    /**
     *
     * @param entity delete this entity from DB
     */
    public void delete(TwitterEntity entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    public abstract List<T> getAll();

    /**
     *
     * @param sqlQuery is the String of a SQL Query
     * @return a List of entities find by the sqlQuery
     */
    public List<T> findBySqlQuery(String sqlQuery) {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery(sqlQuery);
        List<T> entities = q.getResultList();
        entityManager.getTransaction().commit();
        return entities;
    }
}

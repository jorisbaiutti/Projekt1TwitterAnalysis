package ch.bfh.repositories;


import ch.bfh.entities.TwitterEntity;
import ch.bfh.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Patrick on 25.10.2017.
 */
public abstract class Repository<T extends TwitterEntity> {
    EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("emFactory");
    EntityManager entityManager = emFactory.createEntityManager();



     public void save(TwitterEntity entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
     public TwitterEntity update(TwitterEntity entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

     public List<T> getAll() {
        return null;
    }

     public TwitterEntity getOne(long id) {
        User user = entityManager.find(User.class, id);
        return user;

     }

     public void delete(TwitterEntity entity) {

    }

     public List<T> findBySqlQuery(String SqlQuery) {
        return null;
    }
}



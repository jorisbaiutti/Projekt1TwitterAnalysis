package ch.bfh.repositories;


import ch.bfh.entities.TwitterEntity;
import ch.bfh.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Patrick on 25.10.2017.
 */

public abstract class Repository<T extends TwitterEntity> {

    EntityManager entityManager;
    @Autowired
    public Repository(ch.bfh.util.EntityManager entityManager) {
        this.entityManager = entityManager.getEntityManager();
    }

    public void save(TwitterEntity entity) {

        entityManager.getTransaction().begin();
         try {
             entityManager.persist(entity);
         }finally {
             entityManager.merge(entity);
         }

        entityManager.getTransaction().commit();

    }
     public TwitterEntity update(TwitterEntity entity) {
        this.save(entity);
        return entity;
    }



     public TwitterEntity getOne(long id) {
         User user = entityManager.find(User.class, id);
         return user;

     }

     public void delete(TwitterEntity entity) {

    }
    public abstract List<T> getAll();

     public List<T> findBySqlQuery(String SqlQuery) {
        return null;
    }
}



package ch.bfh.util;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Can be used to commit transactions in the database
 */
@Component
public class EntityManager {
    EntityManagerFactory emFactory;
    javax.persistence.EntityManager entityManager;
    public EntityManager() {
        this.emFactory = Persistence.createEntityManagerFactory("emFactory");
        this.entityManager = emFactory.createEntityManager();
    }
    public javax.persistence.EntityManager getEntityManager(){
        return entityManager;
    }
}

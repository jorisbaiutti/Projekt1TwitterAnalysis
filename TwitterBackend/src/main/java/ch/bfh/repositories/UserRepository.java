package ch.bfh.repositories;

import ch.bfh.entities.TwitterEntity;
import ch.bfh.entities.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Patrick on 25.10.2017.
 */
@Component
public class UserRepository extends Repository<User>{
    public User findbyName(String name) {


        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("SELECT t FROM User t where t.userName LIKE :name");
        q.setParameter("name", name);
        List<User> users = q.getResultList();
        User user = users.get(0);

        return user;
    }

}

package ch.bfh.repositories;

import ch.bfh.entities.User;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

@Component
public class UserRepository extends Repository<User>{
    public UserRepository(ch.bfh.util.EntityManager entityManager) {
        super(entityManager);
    }

    /**
     *
     * @param name is the userName of the Entity User
     *             this is used for the SQL Query to find the user
     * @return a User object
     */
    public User findbyName(String name) {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("SELECT t FROM User t where t.userName LIKE :name");
        q.setParameter("name", name);
        List<User> users = q.getResultList();
        User user = users.get(0);
        entityManager.getTransaction().commit();
        return user;
    }

    /**
     *
     * @return a List of all users from DB
     */
    @Override
    public List<User> getAll() {
        return entityManager.createQuery("select u from User u").getResultList();
    }


    public User findbyId(long id){
        User user = entityManager.find(User.class, id);
        return user;
    }

    /**
     *
     * @param name is the userName of the Entity User
     *             this is used for the SQL Query to find the User
     * @return true if the user exists on the DB
     */
    public boolean userExist(String name){
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("SELECT t FROM User t where t.userName LIKE :name");
        q.setParameter("name", name);
        List<User> users = q.getResultList();
        entityManager.getTransaction().commit();
        return users.size() == 1;
    }
}
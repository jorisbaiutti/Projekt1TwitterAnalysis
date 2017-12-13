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

    public User findbyName(String name) {


        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("SELECT t FROM User t where t.userName LIKE :name");
        q.setParameter("name", name);
        List<User> users = q.getResultList();
        User user = users.get(0);
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    public void delete(User user){
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }
}

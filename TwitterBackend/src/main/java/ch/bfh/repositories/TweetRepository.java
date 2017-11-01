package ch.bfh.repositories;

import ch.bfh.entities.Tweet;
import ch.bfh.entities.TwitterEntity;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TweetRepository extends Repository<Tweet>{


    public TweetRepository(ch.bfh.util.EntityManager entityManager) {
        super(entityManager);
    }

    public List<Tweet> findbyContent(String content) {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("SELECT t FROM Tweet t where t.content LIKE :name");
        q.setParameter("name", "%"+content+"%");
        List<Tweet> tweets = q.getResultList();
        entityManager.close();

        return tweets;
    }

    public List<Tweet> findbyHashtag(String hashTag) {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("SELECT t FROM Tweet t join HashTag h where h.hashTag = :hashTag");
        q.setParameter("hashTag", hashTag);
        List<Tweet> tweets = q.getResultList();

        return tweets;
    }


    @Override
    public List<Tweet> getAll() {
        return entityManager.createQuery("select t from Tweet t").getResultList();
    }
}

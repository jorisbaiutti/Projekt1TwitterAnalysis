package ch.bfh.repositories;

import ch.bfh.entities.Tweet;
import ch.bfh.entities.TwitterEntity;
import ch.bfh.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Component
public class TweetRepository extends Repository<Tweet>{


    public TweetRepository(ch.bfh.util.EntityManager entityManager) {
        super(entityManager);
    }

    /**
     *
     * @param content is the String which is the full or a part of the Content of a Tweet
     *             this is used for the SQL Query to find this Tweets
     * @return a List of Tweets
     */
    public List<Tweet> findbyContent(String content) {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("SELECT t FROM Tweet t where t.content LIKE :name");
        q.setParameter("name", "%"+content+"%");
        List<Tweet> tweets = q.getResultList();
        entityManager.getTransaction().commit();
        return tweets;
    }

    /**
     *
     * @param hashTag is the String which is one of the HashTags of a Tweet
     *             this is used for the SQL Query to find this Tweets
     * @return a List of Tweets
     */
    public List<Tweet> findbyHashtag(String hashTag) {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("SELECT t FROM Tweet t join HashTag h where h.hashTag = :hashTag");
        q.setParameter("hashTag", hashTag);
        List<Tweet> tweets = q.getResultList();
        entityManager.getTransaction().commit();
        return tweets;
    }

    public Tweet findById(long ID){
        entityManager.getTransaction().begin();
        Tweet tweet = entityManager.find(Tweet.class, ID);
        entityManager.getTransaction().commit();
        return tweet;
    }

    public boolean tweetExistbyContent(String content){
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("SELECT t FROM Tweet t where t.content LIKE :name");
        q.setParameter("name", "%"+content+"%");
        List<Tweet> tweets = q.getResultList();
        entityManager.getTransaction().commit();
        return tweets.size() >= 1;
    }

    @Override
    public List<Tweet> getAll() {
        return entityManager.createQuery("select t from Tweet t").getResultList();
    }
}

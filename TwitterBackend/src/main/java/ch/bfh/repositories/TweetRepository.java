package ch.bfh.repositories;

import ch.bfh.entities.Tweet;
import org.springframework.stereotype.Component;

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

    /**
     *
     * @param ID is the primarykey of a Tweet in SQL
     *           this is used for the SQL Query to find this tweet
     * @return a Tweet
     */
    public Tweet findById(long ID){
        entityManager.getTransaction().begin();
        Tweet tweet = entityManager.find(Tweet.class, ID);
        entityManager.getTransaction().commit();
        return tweet;
    }

    /**
     *
     * @param content is the String which is in the content of a tweet
     *             this is used for the SQL Query to find this tweets
     * @return a boolean if tweets with this content exists
     */
    public boolean tweetExistbyContent(String content){
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("SELECT t FROM Tweet t where t.content LIKE :name");
        q.setParameter("name", "%"+content+"%");
        List<Tweet> tweets = q.getResultList();
        entityManager.getTransaction().commit();
        return tweets.size() >= 1;
    }

    /**
     *
     * @return a List of all tweets from DB
     */
    @Override
    public List<Tweet> getAll() {
        return entityManager.createQuery("select t from Tweet t").getResultList();
    }
}
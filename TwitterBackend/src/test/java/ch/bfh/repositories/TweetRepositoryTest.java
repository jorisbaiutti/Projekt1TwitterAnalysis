package ch.bfh.repositories;

import ch.bfh.entities.Tweet;
import ch.bfh.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRepository.class)
@ComponentScan(basePackages = { "ch.bfh.repositories","ch.bfh.util" })
public class TweetRepositoryTest {

    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    UserRepository userRepository;


    @Test
    public void findbyContent() throws Exception {

        if (!userRepository.userExist("TestUserNiemals")){
            User user = new User();
            user.setUserName("TestUserNiemals");
            user.setEmail("test@test.ch");
            user.setId(1234567);
            user.setLocation("TestOrt");
            userRepository.save(user);
        }

        if (!tweetRepository.tweetExistbyContent("TestContent 12345677654321")){
            Tweet tweet = new Tweet();
            tweet.setId(1234567);
            tweet.setContent("TestContent 12345677654321");
            tweet.setLikes(321);
            tweet.setCreator(userRepository.findbyName("TestUserNiemals"));
            tweetRepository.save(tweet);
        }

        assert tweetRepository.findbyContent("TestContent 12345677654321").get(0).getLikes() == 321;

        Tweet deleteTweet = tweetRepository.findById(1234567);
        tweetRepository.delete(deleteTweet);
        User deleteUser = userRepository.findbyName("TestUserNiemals");
        userRepository.delete(deleteUser);
    }
}
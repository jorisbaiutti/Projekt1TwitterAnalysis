package ch.bfh.repositories;

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
public class UserRepositoryTest{

    @Autowired
    UserRepository userRepository;

    @Test
    public void persistenceTest() throws Exception {

        if (!userRepository.userExist("TestUserNiemals")){
            User user = new User();
            user.setUserName("TestUserNiemals");
            user.setEmail("testuser@test.ch");
            user.setId(1234567);
            user.setLocation("TestOrt");
            userRepository.save(user);
        }

        assert userRepository.findbyName("TestUserNiemals").getLocation().equalsIgnoreCase("TestOrt");

        User deleteUser = userRepository.findbyName("TestUserNiemals");
        userRepository.delete(deleteUser);
    }
}
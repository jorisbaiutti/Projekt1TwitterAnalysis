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
    public void persistenceTest(){

        User deleteUser = userRepository.findbyName("TestUser");
        userRepository.delete(deleteUser);

        User user = new User();
        user.setUserName("TestUser");
        user.setLocation("TestOrt");


        userRepository.save(user);

        assert userRepository.findbyName("TestUser").getLocation().equalsIgnoreCase("TestOrt");


    }

}
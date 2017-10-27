package ch.bfh.twitter;

import ch.bfh.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test {
    UserRepository userRepository;

    @Autowired
    public Test(UserRepository userRepository) {
        this.userRepository = userRepository;



    }
}

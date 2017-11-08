package ch.bfh;

import ch.bfh.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import twitter4j.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

/**
 * Hello world!
 *
 */
@PropertySource("application.properties")
@EnableZuulProxy
@EnableScheduling
@SpringBootApplication
public class StartApplication
{
    public static void main( String[] args ) throws IOException, TwitterException {
        SpringApplication.run(StartApplication.class, args);
    }

}

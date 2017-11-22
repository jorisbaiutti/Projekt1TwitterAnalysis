package ch.bfh;

import ch.bfh.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import twitter4j.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

import static springfox.documentation.builders.PathSelectors.regex;


@PropertySource("application.properties")
@EnableZuulProxy
@EnableSwagger2
@EnableScheduling
@SpringBootApplication
public class StartApplication
{
    public static void main( String[] args ) throws IOException, TwitterException {
        SpringApplication.run(StartApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("guru.springframework.controllers"))
                .paths(regex("/api.*"))
                .build();
    }

}

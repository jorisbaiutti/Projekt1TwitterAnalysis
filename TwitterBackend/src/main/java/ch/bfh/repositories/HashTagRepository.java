package ch.bfh.repositories;

import ch.bfh.entities.HashTag;
import ch.bfh.entities.TwitterEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Patrick on 25.10.2017.
 */
@Component
public class HashTagRepository extends Repository<HashTag>{


}

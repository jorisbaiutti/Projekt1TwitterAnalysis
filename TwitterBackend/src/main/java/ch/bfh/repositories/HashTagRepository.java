package ch.bfh.repositories;

import ch.bfh.entities.HashTag;
import ch.bfh.entities.TwitterEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Component
public class HashTagRepository extends Repository<HashTag>{


    public HashTagRepository(ch.bfh.util.EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<HashTag> getAll() {
        return null;
    }
}

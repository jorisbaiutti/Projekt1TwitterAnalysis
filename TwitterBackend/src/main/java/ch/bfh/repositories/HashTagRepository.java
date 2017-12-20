package ch.bfh.repositories;

import ch.bfh.entities.HashTag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HashTagRepository extends Repository<HashTag>{


    public HashTagRepository(ch.bfh.util.EntityManager entityManager) {
        super(entityManager);
    }

    /**
     *
     * @return a List of all HashTags from DB
     */
    @Override
    public List<HashTag> getAll() {
        return entityManager.createQuery("select h from HashTag h").getResultList();
    }
}
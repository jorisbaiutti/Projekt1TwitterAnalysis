package ch.bfh.repositories;

import ch.bfh.beans.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    private static List<Person> personList = new ArrayList<Person>();


    static {
        personList.add(new Person("Joris", 26, 1));
        personList.add(new Person("Sascha", 25, 2));
        personList.add(new Person("Paedu", 27, 3));
    }

    public Person findbyId(int id){
        return personList.stream().filter(p -> p.getId() == id).findFirst().get();
    }
}
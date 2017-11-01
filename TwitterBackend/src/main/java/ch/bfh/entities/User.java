package ch.bfh.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Patrick on 11.10.2017.
 */
@javax.persistence.Entity
@Table(name = "[User]")
public class User extends TwitterEntity {

    @Id
    private long id;

    private String userName;

    private String location;

    private String email;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "creator")
    private List<Tweet> created;

    public User(){

    }

    public User(String userName) {
        this.userName = userName;
    }

    public List<Tweet> getCreated() {
        return created;
    }

    public void setCreated(List<Tweet> created) {
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
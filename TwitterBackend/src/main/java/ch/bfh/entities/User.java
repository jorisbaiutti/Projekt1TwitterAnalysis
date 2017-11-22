package ch.bfh.entities;

import twitter4j.GeoLocation;

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

    @Column(unique = true, nullable = false)
    private String userName;

    private String email;

    private String location;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
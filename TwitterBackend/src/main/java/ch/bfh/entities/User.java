package ch.bfh.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Patrick on 11.10.2017.
 */
@Entity
@Table(name = "[User]")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID", unique = true, nullable = false)
    private int id;

    @Column(name = "USER_NAME", unique = true, nullable = false)
    private String userName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "likes")
    private List<Tweet> liked;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "retweets")
    private List<Tweet> retweeted;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "mentions")
    private List<Tweet> mentioned;

    @OneToMany
    private List<Tweet> created;

    public User(){

    }

    public List<Tweet> getCreated() {
        return created;
    }

    public void setCreated(List<Tweet> created) {
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Tweet> getLiked() {
        return liked;
    }

    public void setLiked(List<Tweet> liked) {
        this.liked = liked;
    }

    public List<Tweet> getRetweeted() {
        return retweeted;
    }

    public void setRetweeted(List<Tweet> retweeted) {
        this.retweeted = retweeted;
    }

    public List<Tweet> getMentioned() {
        return mentioned;
    }

    public void setMentioned(List<Tweet> mentioned) {
        this.mentioned = mentioned;
    }
}

package ch.bfh.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Patrick on 09.10.2017.
 */
@Entity
@Table
public class Tweet {

    @Id
    @GeneratedValue
    @Column(name = "TWEET_ID", unique = true, nullable = false)
    private int id;

    @ManyToMany(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @JoinTable(name = "TWEET_HASHTAG",  joinColumns = {
            @JoinColumn(name = "TWEET_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "HASHTAG",
                    nullable = false, updatable = false) })
    private List<HashTag> hashTags;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "TWEET_LIKE_USER",  joinColumns = {
            @JoinColumn(name = "TWEET_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "USER_ID",
                    nullable = false, updatable = false) })
    private List<User> likes;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "TWEET_RETWEET_USER",  joinColumns = {
            @JoinColumn(name = "TWEET_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "USER_ID",
                    nullable = false, updatable = false) })
    private List<User> retweets;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "TWEET_MENTION_USER",  joinColumns = {
            @JoinColumn(name = "TWEET_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "USER_ID",
                    nullable = false, updatable = false) })
    private List<User> mentions;


    @ManyToOne
    @JoinColumn(name="CREATOR_ID")
    private User creator;

    public Tweet(){

    }

    public int getId() {
        return id;
    }

    public List<HashTag> getHashTags() {
        return hashTags;
    }

    public List<User> getLikes() {
        return likes;
    }

    public List<User> getRetweets() {
        return retweets;
    }

    public List<User> getMentions() {
        return mentions;
    }

    public User getCreator() {
        return creator;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHashTags(List<HashTag> hashTags) {
        this.hashTags = hashTags;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    public void setRetweets(List<User> retweets) {
        this.retweets = retweets;
    }

    public void setMentions(List<User> mentions) {
        this.mentions = mentions;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}

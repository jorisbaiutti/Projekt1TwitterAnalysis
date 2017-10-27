package ch.bfh.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Patrick on 09.10.2017.
 */
@javax.persistence.Entity
@Table
public class Tweet extends TwitterEntity {


    @Column(name = "TWEET_ID", unique = true)
    @Id
    private long id;

    @ManyToMany(fetch = FetchType.LAZY,  cascade = CascadeType.MERGE)
    @JoinTable(name = "TWEET_HASHTAG",  joinColumns = {
            @JoinColumn(name = "TWEET_ID", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "HASHTAG",
                    nullable = false, updatable = false) })
    private List<HashTag> hashTags;

    private int likes;

    private int retweets;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "TWEET_MENTION_USER",  joinColumns = {
            @JoinColumn(name = "TWEET_ID", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "USER_ID",
                    nullable = false, updatable = false) })
    private List<User> mentions;


    private String content;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="CREATOR_ID")
    private User creator;

    public Tweet(){

    }

    public Tweet(String content, User creator) {
        this.content = content;
        this.creator = creator;
    }

    public long getId() {
        return id;
    }

    public List<HashTag> getHashTags() {
        return hashTags;
    }

    public List<User> getMentions() {
        return mentions;
    }

    public User getCreator() {
        return creator;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHashTags(List<HashTag> hashTags) {
        this.hashTags = hashTags;
    }

    public void setMentions(List<User> mentions) {
        this.mentions = mentions;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getRetweets() {
        return retweets;
    }

    public void setRetweets(int retweets) {
        this.retweets = retweets;
    }
}
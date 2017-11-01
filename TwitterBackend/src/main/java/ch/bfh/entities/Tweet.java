package ch.bfh.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Patrick on 09.10.2017.
 */
@javax.persistence.Entity
@Table
public class Tweet extends TwitterEntity {


    @Id
    private long id;

    @ManyToMany(fetch = FetchType.LAZY,  cascade = CascadeType.MERGE)
    private List<HashTag> hashTags;

    private int likes;

    private int retweets;

    private String content;


    @ManyToOne(cascade = CascadeType.MERGE)
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

    public User getCreator() {
        return creator;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHashTags(List<HashTag> hashTags) {
        this.hashTags = hashTags;
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
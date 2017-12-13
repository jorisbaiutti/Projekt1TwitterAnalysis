package ch.bfh.entities;

import twitter4j.GeoLocation;
import twitter4j.Place;

import javax.persistence.*;
import java.util.List;

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

    private String language;

    private double longitude;

    private double latitude;

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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


}
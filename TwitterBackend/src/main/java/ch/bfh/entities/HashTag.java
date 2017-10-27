package ch.bfh.entities;

import javax.persistence.*;

/**
 * Created by Patrick on 11.10.2017.
 */
@javax.persistence.Entity
@Table
public class HashTag extends TwitterEntity{

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "HASHTAG", unique = true, nullable = false)
    private String hashTag;

    public HashTag(){

    }


    public HashTag(String hashTag) {
        this.hashTag = hashTag;
    }

    public String getHashTag() {
        return hashTag;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
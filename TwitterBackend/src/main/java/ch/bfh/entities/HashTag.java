package ch.bfh.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Patrick on 11.10.2017.
 */
@Entity
@Table
public class HashTag {

    @Id
    @Column(name = "HASHTAG", unique = true, nullable = false)
    private String hashTag;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "hashTags")
    private List<Tweet> tweets;

    public HashTag(){

    }

    public String getHashTag() {
        return hashTag;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}

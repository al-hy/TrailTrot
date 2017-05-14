package com.example.ahy.myapplication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by ahy on 5/6/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrailReviews {
    @JsonProperty("reviews")
    private List<Reviews> reviews;

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Reviews {
    @JsonProperty("rating")
    private int rating;
    @JsonProperty("user")
    private User user;
    @JsonProperty("text")
    private String text;
    @JsonProperty("time_created")
    private String time;
    @JsonProperty("url")
    private String url;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class User {
   @JsonProperty("image_url")
   private String userImage;
   @JsonProperty("name")
   private String nameOfUser;

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getNameOfUser() {
        return nameOfUser;
    }

    public void setNameOfUser(String nameOfUser) {
        this.nameOfUser = nameOfUser;
    }
}

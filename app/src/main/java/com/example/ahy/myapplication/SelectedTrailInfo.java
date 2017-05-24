package com.example.ahy.myapplication;

import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by ahy on 5/6/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SelectedTrailInfo {

    @JsonProperty("name")
    private String name;
    @JsonProperty("image_url")
    private String imageURL;
    @JsonProperty("display_phone")
    private String phoneNumber;
    @JsonProperty("rating")
    private double rating;
    @JsonProperty("review_count")
    private int reviewCount;
    @JsonProperty("is_closed")
    private boolean isClosed;
    @JsonProperty("location")
    private Address address;
    @JsonProperty("photos")
    private String[] trailPhotos;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String[] getTrailPhotos() {
        return trailPhotos;
    }

    public void setTrailPhotos(String[] trailPhotos) {
        this.trailPhotos = trailPhotos;
    }


    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }
}

package com.example.ahy.myapplication;

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
    @JsonProperty("coordinates")
    private Coordinates coordinates;

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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class TrailPhotos {

    //@JsonProperty("photos")
    private String photosURL;

    public String getPhotosURL() {
        return photosURL;
    }

    public void setPhotosURL(String photosURL) {
        this.photosURL = photosURL;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Address {
    @JsonProperty("display_address")
    private String[] displayAddress;

    public String[] getDisplayAddress() {
        return displayAddress;
    }

    public void setDisplayAddress(String[] displayAddress) {
        this.displayAddress = displayAddress;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Coordinates {
    @JsonProperty("latitude")
    private double latitude;
    @JsonProperty("longitude")
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
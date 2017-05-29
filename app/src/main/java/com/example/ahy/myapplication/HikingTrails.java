package com.example.ahy.myapplication;

/**
 * Created by ahy on 5/2/17.
 */

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;
import com.yelp.fusion.client.models.Business;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Objects;

/**
 * Created by ahy on 5/1/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class HikingTrails {
    @JsonProperty("total")
    private int total;
    @JsonProperty("businesses")
    private List<Businesses> businesses;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Businesses> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Businesses> businesses) {
        this.businesses = businesses;
    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
class Businesses implements Comparable<Businesses> {

    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private String id;
    @JsonProperty("image_url")
    private String imageURL;
    @JsonProperty("rating")
    private double rating;
    @JsonProperty("review_count")
    private int reviewCount;
    @JsonProperty("coordinates")
    private Coordinates coordinates;
    @JsonProperty("location")
    private Address address;
    private double distance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(@NonNull Businesses o) {
        return Double.compare(this.getDistance(), o.getDistance());
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Coordinates extends SerializableSerializer {

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

@JsonIgnoreProperties(ignoreUnknown = true)
class Address extends SerializableSerializer{

    @JsonProperty("display_address")
    @Nullable
    private String[] displayAddress;

    public String[] getDisplayAddress() {
        return displayAddress;
    }

    public void setDisplayAddress(String[] displayAddress) {
        this.displayAddress = displayAddress;
    }
}


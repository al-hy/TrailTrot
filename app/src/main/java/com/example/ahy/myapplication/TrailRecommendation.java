package com.example.ahy.myapplication;

/**
 * Created by ahy on 4/24/17.
 */

public class TrailRecommendation {

    private String name;
    private double rating;
    private int trailImage;

    public TrailRecommendation(String name, double rating, int trailImage) {
        this.name = name;
        this.rating = rating;
        this.trailImage = trailImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTrailImage() {
        return trailImage;
    }

    public void setTrailImage(int trailImage) {
        this.trailImage = trailImage;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

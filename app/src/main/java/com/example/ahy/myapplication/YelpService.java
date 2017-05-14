package com.example.ahy.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by ahy on 5/2/17.
 */

public interface YelpService {
    @POST("/oauth2/token")
    Call<YelpInfo> getRouteVehicles(@Query("grant_type") String type, @Query("client_id") String id,
                                    @Query("client_secret") String secret);

    @GET("/v3/businesses/search")
    Call<HikingTrails> getHikingTrails(@Header("Authorization") String accessToken,
                                       @Query("term") String term,
                                       @Query("latitude") double latitude,
                                       @Query("longitude") double longitude,
                                       @Query("limit") int limit);
    @GET("/v3/businesses/search")
    Call<HikingTrails> searchLocation(@Header("Authorization") String accessToken,
    @Query("term") String term,
    @Query("location") String location,
    @Query("limit") int limit);

    @GET("/v3/businesses/{id}/reviews")
    Call<TrailReviews> getTrailReviews(@Header("Authorization") String accessToken,
                                       @Path("id") String id);

    @GET("/v3/businesses/{id}")
    Call<SelectedTrailInfo> getExtraPhotos(@Header("Authorization") String accessToken,
                                       @Path("id") String id);

}

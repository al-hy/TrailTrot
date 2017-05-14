package com.example.ahy.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class SelectedTrailActivity extends AppCompatActivity {

    private YelpInfo yelpInfo;
    private YelpService service;
    private EditText editText;
    private ListView listView;
    private ListView extraImages;
    private ImageView imageView;
    private TextView textView;
    private String image, id, name;
    private SelectedTrailReviewAdapter selectedTrailReviewAdapter;
    private TrailPhotoAdapter trailPhotoAdapter;
    private ImageView ratingBar;
    private TextView addressLine1;
    private TextView reviewCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trail_selected);
        imageView = (ImageView) findViewById(R.id.selectedTrailPhoto) ;

        Intent intent = getIntent();
        image = intent.getStringExtra("image");
        yelpInfo = (YelpInfo) intent.getSerializableExtra("yelpInfo");
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");


        /**
         * PASS THE ACCESS TOKEN! RETRIEVE FROM INTENT
         */

        if(image.isEmpty()){
            imageView.setImageResource(R.drawable.no_image);
        } else {
            Picasso.with(this)
                    .load(image)
                    .resize(500, 400)
                    .centerInside()
                    .into(imageView);
        }

        textView = (TextView) findViewById(R.id.selectedTrailName);
        textView.setText(name);


        getYelp();


    }

    public void getYelp() {
        final AsyncTask<Void, Void, Void> yelpRequest = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.yelp.com")
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();

                service = retrofit.create(YelpService.class);

                editText = (EditText) findViewById(R.id.searchLocation);
                addressLine1 = (TextView) findViewById(R.id.address_line_1);

                Call<TrailReviews> hikingTrails = service.getTrailReviews(yelpInfo.getTokenType() + " " + yelpInfo.getAccessToken(), id);
                hikingTrails.enqueue(new Callback<TrailReviews>() {
                    @Override
                    public void onResponse(Call<TrailReviews> call, Response<TrailReviews> response) {
                        TrailReviews trailReviews = response.body();

                        List<Reviews> reviews = trailReviews.getReviews();

                        listView = (ListView) findViewById(R.id.userReview);
                        selectedTrailReviewAdapter = new SelectedTrailReviewAdapter(
                                SelectedTrailActivity.this, R.layout.trail_selected_reviews, reviews);

                        listView.setAdapter(selectedTrailReviewAdapter);
                    }

                    @Override
                    public void onFailure(Call<TrailReviews> call, Throwable t) {
                        Log.e("TEST", "Failed to get the hiking info");
                        StringWriter errors = new StringWriter();
                        t.printStackTrace(new PrintWriter(errors));
                        Log.e("TEST", errors.toString());
                    }
                });

                Call<SelectedTrailInfo> trailExtraPhotos = service.getExtraPhotos(yelpInfo.getTokenType() + " " + yelpInfo.getAccessToken(), id);
                trailExtraPhotos.enqueue(new Callback<SelectedTrailInfo>() {
                    @Override
                    public void onResponse(Call<SelectedTrailInfo> call, Response<SelectedTrailInfo> response) {
                        SelectedTrailInfo selectedTrailInfo = response.body();
                        ratingBar = (ImageView) findViewById(R.id.ratingBar);
                        reviewCount = (TextView) findViewById(R.id.selectedReviewCount);

                        Address address = selectedTrailInfo.getAddress();
                        String[] displayAddress = address.getDisplayAddress();

                        StringBuilder addressBuilder = new StringBuilder();

                        for(int i = 0; i < displayAddress.length; i++) {
                            if(i == displayAddress.length - 1) {
                                addressBuilder.append(displayAddress[i]);
                            } else {
                                addressBuilder.append(displayAddress[i]).append(", ");
                            }
                        }

                        addressLine1.setText(addressBuilder.toString());

                        switch (Double.toString(selectedTrailInfo.getRating())) {
                            case "0":
                                ratingBar.setImageResource(R.drawable.stars_small_0);
                                break;
                            case "0.5":
                                ratingBar.setImageResource(R.drawable.stars_small_0);
                                break;
                            case "1.0":
                                ratingBar.setImageResource(R.drawable.stars_small_1);
                                break;
                            case "1.5":
                                ratingBar.setImageResource(R.drawable.stars_small_1_half);
                                break;
                            case "2.0":
                                ratingBar.setImageResource(R.drawable.stars_small_2);
                                break;
                            case "2.5":
                                ratingBar.setImageResource(R.drawable.stars_small_2_half);
                                break;
                            case "3.0":
                                ratingBar.setImageResource(R.drawable.stars_small_3);
                                break;
                            case "3.5":
                                ratingBar.setImageResource(R.drawable.stars_small_3_half);
                                break;
                            case "4.0":
                                ratingBar.setImageResource(R.drawable.stars_small_4);
                                break;
                            case "4.5":
                                ratingBar.setImageResource(R.drawable.stars_small_4_half);
                                break;
                            case "5.0":
                                ratingBar.setImageResource(R.drawable.stars_small_5);
                                break;
                        }

                        reviewCount.setText("(" + selectedTrailInfo.getReviewCount() + ")");

                        String[] photos = selectedTrailInfo.getTrailPhotos();

                        extraImages = (ListView) findViewById(R.id.trailPhotos);

                        trailPhotoAdapter = new TrailPhotoAdapter(
                                SelectedTrailActivity.this, R.layout.trail_selected_photos, photos, yelpInfo);

                        extraImages.setAdapter(trailPhotoAdapter);
                    }

                    @Override
                    public void onFailure(Call<SelectedTrailInfo> call, Throwable t) {
                        Log.e("TEST", "Failed to get the hiking info");
                        StringWriter errors = new StringWriter();
                        t.printStackTrace(new PrintWriter(errors));
                        Log.e("TEST", errors.toString());
                    }
                });


                return null;
            }

        };
        yelpRequest.execute();
    }
}

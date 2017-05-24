package com.example.ahy.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
    private Service service1;
    private Service service2;
    private Service service3;
    private EditText editText;
    private ListView listView;
    private ListView extraImages;
    private ImageView imageView;
    private TextView textView;
    private String image, id, name;
    private SelectedTrailReviewAdapter selectedTrailReviewAdapter;
    private ImageView ratingBar;
    private TextView addressLine1;
    private TextView reviewCount;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private TextView currentTemperature;
    private ImageView currentWeather;


    private RecyclerView recyclerViewWeather;
    private RecyclerView.LayoutManager layoutManagerWeather;
    private RecyclerView.Adapter adapterWeather;

    private Address displayAddress;
    private Coordinates coordinates;
    private String rating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trail_selected);

        Intent intent = getIntent();
        image = intent.getStringExtra("image");
        yelpInfo = (YelpInfo) intent.getSerializableExtra("yelpInfo");
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");
        displayAddress = (Address) intent.getSerializableExtra("address");
        coordinates = (Coordinates) intent.getSerializableExtra("coordinates");
        rating = intent.getStringExtra("rating");
        editText = (EditText) findViewById(R.id.searchLocation);
        addressLine1 = (TextView) findViewById(R.id.address_line_1);
        ratingBar = (ImageView) findViewById(R.id.ratingBar);
        reviewCount = (TextView) findViewById(R.id.selectedReviewCount);
        currentWeather = (ImageView) findViewById(R.id.currentWeather);
        currentTemperature = (TextView) findViewById(R.id.currentTemperature);


        /**
         * Initialize RecyclerView for Extra Yelp Images
         */
        recyclerView = (RecyclerView) findViewById(R.id.trailPhotos);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(SelectedTrailActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        /**
         * Initialize RecyclerView for Weather Forecast
         */

        recyclerViewWeather = (RecyclerView) findViewById(R.id.weeklyForecast);
        recyclerViewWeather.setHasFixedSize(true);
        layoutManagerWeather = new LinearLayoutManager(SelectedTrailActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewWeather.setLayoutManager(layoutManagerWeather);



        /**
         * PASS THE ACCESS TOKEN! RETRIEVE FROM INTENT
         */


        textView = (TextView) findViewById(R.id.selectedTrailName);
        textView.setText(name);

        StringBuilder addressBuilder = new StringBuilder();

        if (displayAddress.getDisplayAddress() != null) {

            for (int i = 0; i < displayAddress.getDisplayAddress().length; i++) {
                if (i == displayAddress.getDisplayAddress().length - 1) {
                    addressBuilder.append(displayAddress.getDisplayAddress()[i]);
                } else {
                    addressBuilder.append(displayAddress.getDisplayAddress()[i]).append(", ");
                }
            }

            addressLine1.setText(addressBuilder.toString());

        }

        switch (rating) {
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

        getYelp();

    }

    public void getYelp() {


        final AsyncTask<Void, Void, Void> getPhotos = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                Retrofit retrofit1 = new Retrofit.Builder()
                        .baseUrl("https://api.yelp.com")
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();

                service1 = retrofit1.create(Service.class);

                Call<SelectedTrailInfo> trailExtraPhotos = service1.getExtraPhotos(yelpInfo.getTokenType() + " " + yelpInfo.getAccessToken(), id);
                trailExtraPhotos.enqueue(new Callback<SelectedTrailInfo>() {
                    @Override
                    public void onResponse(Call<SelectedTrailInfo> call, Response<SelectedTrailInfo> response) {
                        SelectedTrailInfo selectedTrailInfo = response.body();

                        Log.i("STRING", "STRING");

                        if(selectedTrailInfo != null) {
                            reviewCount.setText("(" + selectedTrailInfo.getReviewCount() + ")");
                            adapter = new TrailPhotoAdapter(selectedTrailInfo.getTrailPhotos(), yelpInfo);
                            recyclerView.setAdapter(adapter);
                        }

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

        getPhotos.execute();

        final AsyncTask<Void, Void, Void> yelpRequest = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                Retrofit retrofit2 = new Retrofit.Builder()
                        .baseUrl("https://api.yelp.com")
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();

                service2 = retrofit2.create(Service.class);


                Call<TrailReviews> hikingTrails = service2.getTrailReviews(yelpInfo.getTokenType() + " " + yelpInfo.getAccessToken(), id);
                hikingTrails.enqueue(new Callback<TrailReviews>() {
                    @Override
                    public void onResponse(Call<TrailReviews> call, Response<TrailReviews> response) {
                        TrailReviews trailReviews = response.body();

                        List<Reviews> reviews = trailReviews.getReviews();

                        listView = (ListView) findViewById(R.id.userReview);
                        listView.setScrollContainer(false);
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


                return null;
            }

        };

        yelpRequest.execute();


        final AsyncTask<Void, Void, Void> getWeather = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                Retrofit retrofit3 = new Retrofit.Builder()
                        .baseUrl("https://api.darksky.net")
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();

                service3 = retrofit3.create(Service.class);

                Call<Weather> forecast = service3.getForecast("b220231b558efd3165c5c65e01228ee3", coordinates.getLatitude(), coordinates.getLongitude());
                forecast.enqueue(new Callback<Weather>() {
                    @Override
                    public void onResponse(Call<Weather> call, Response<Weather> response) {
                        Weather weather = response.body();

                        if(weather != null) {

                            Currently currently = weather.getCurrently();
                            if(currently.getTime() <= weather.getDaily().getForecast().get(0).getSunsetTime()) {

                                switch (currently.getIcon()) {

                                    case "clear-day":
                                        currentWeather.setImageResource(R.drawable.clearday);
                                        break;
                                    case "clear-night":
                                        currentWeather.setImageResource(R.drawable.clearnight);
                                        break;
                                    case "rain":
                                        currentWeather.setImageResource(R.drawable.rainday);
                                        break;
                                    case "snow":
                                        currentWeather.setImageResource(R.drawable.snow);
                                        break;
                                    case "sleet":
                                        currentWeather.setImageResource(R.drawable.snow);
                                        break;
                                    case "wind":
                                        currentWeather.setImageResource(R.drawable.daywind);
                                        break;
                                    case "fog":
                                        currentWeather.setImageResource(R.drawable.fog);
                                        break;
                                    case "cloudy":
                                        currentWeather.setImageResource(R.drawable.cloudy);
                                        break;
                                    case "partly-cloudy-day":
                                        currentWeather.setImageResource(R.drawable.partlycloudyday);
                                        break;
                                    case "partly-cloudy-night":
                                        currentWeather.setImageResource(R.drawable.partlycloudynight);
                                        break;
                                }

                            } else  {

                                    switch (currently.getIcon()) {

                                        case "clear-day":
                                            currentWeather.setImageResource(R.drawable.clearday);
                                            break;
                                        case "clear-night":
                                            currentWeather.setImageResource(R.drawable.clearnight);
                                            break;
                                        case "rain":
                                            currentWeather.setImageResource(R.drawable.rainnight);
                                            break;
                                        case "snow":
                                            currentWeather.setImageResource(R.drawable.snow);
                                            break;
                                        case "sleet":
                                            currentWeather.setImageResource(R.drawable.snow);
                                            break;
                                        case "wind":
                                            currentWeather.setImageResource(R.drawable.nightwind);
                                            break;
                                        case "fog":
                                            currentWeather.setImageResource(R.drawable.fog);
                                            break;
                                        case "cloudy":
                                            currentWeather.setImageResource(R.drawable.cloudy);
                                            break;
                                        case "partly-cloudy-day":
                                            currentWeather.setImageResource(R.drawable.partlycloudyday);
                                            break;
                                        case "partly-cloudy-night":
                                            currentWeather.setImageResource(R.drawable.partlycloudynight);
                                            break;
                                    }

                                }


                            currentTemperature.setText(Integer.toString((int) (Math.round(currently.getTemperature()*1)/1.0)) + "\u00b0");

                            List<Forecast> forecast = weather.getDaily().getForecast();
                            adapterWeather = new WeatherActivityAdapter(forecast, weather.getTimezone());
                            recyclerViewWeather.setAdapter(adapterWeather);

                        }

                    }

                    @Override
                    public void onFailure(Call<Weather> call, Throwable t) {
                        Log.e("TEST", "Failed to get the forecast");
                        StringWriter errors = new StringWriter();
                        t.printStackTrace(new PrintWriter(errors));
                        Log.e("TEST", errors.toString());
                    }
                });


                return null;
            }
        };

        getWeather.execute();

    }
}

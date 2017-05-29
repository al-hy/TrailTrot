package com.example.ahy.myapplication;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by ahy on 4/24/17.
 */

public class TrailRecommendationActivity extends AppCompatActivity{

    private List<TrailRecommendation> trailRecommendationList;
    private TextView textView;
    private Button button;
    private ListView listView;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private double latitude;
    private double longitude;
    private EditText editText;
    private SwipeRefreshLayout swipeRefreshLayout;
    private YelpInfo yelpInfo;
    private Service service;
    private Location userLocation;

    //When creating a listView, an adapter is needed for dynamic retrieval
    private TrailRecommendationActivityAdapter trailRecommendationActivityAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trail_selection);
        editText = (EditText) findViewById(R.id.searchLocation);


        Intent intent = getIntent();

        //Add Location services here
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                userLocation = location;
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                Log.i("LAT", Double.toString(latitude));
                Log.i("LONG", Double.toString(longitude));
                getYelp(null);

                if(swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }

                locationManager.removeUpdates(locationListener);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TrailRecommendationActivity.this);
                builder.setTitle("Use location?");
                builder.setMessage("This app wants to make changes to your device settings:\n\n-Use GPS for location");
                builder.setPositiveButton("Agree",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent locationDisabled = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(locationDisabled);
                            }

                        });
                builder.setNegativeButton("Disagree",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }

                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        };

        button = (Button) findViewById(R.id.searchButton);
        configure_button();

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                configure_button();
            }

        });


        //End of Location Services


        button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(editText.getText().toString().isEmpty()){
                     getYelp(null);
                 } else {
                     getYelp(editText.getText().toString());
                 }
             }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    getYelp(editText.getText().toString());;
                    return true;
                }
                return false;
            }
        });

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch(requestCode) {
            case 10:
                configure_button();
                break;
            default:
                break;
        }
    }

    public void configure_button() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] {
                        Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
                }, 10);
            }

            return;
        }

            if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
               locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 0, locationListener);
                Log.i("NETWORK", "USED NETWORK FOR LOCATION");
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 0, locationListener);
                Log.i("GPS", "USED GPS FOR LOCATION");
            }

    }

    public void getYelp(@Nullable final String searchLocation) {

        final AsyncTask<Void, Void, Void> yelpRequest = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.yelp.com")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        service = retrofit.create(Service.class);


        Call<YelpInfo> routeInfoCall = service.getRouteVehicles("client_credentials", getString(R.string.id), getString(R.string.secret));
        routeInfoCall.enqueue(new Callback<YelpInfo>() {
            @Override
            public void onResponse(Call<YelpInfo> call, Response<YelpInfo> response) {
                yelpInfo = response.body();
                editText = (EditText) findViewById(R.id.searchLocation);
                String text = editText.getText().toString();



                if(searchLocation != null ) {
                    Log.i("ERROR", "IN LOCATION");

                    Call<HikingTrails> hikingTrails = service.searchLocation(yelpInfo.getTokenType() + " " + yelpInfo.getAccessToken(), "hiking", searchLocation, 50);
                    request(hikingTrails);

                } else {
                    Log.i("ERROR", "IN LAT AND LONG");
                    Call<HikingTrails> hikingTrails = service.getHikingTrails(yelpInfo.getTokenType() + " " + yelpInfo.getAccessToken(), "hiking", latitude, longitude, 50);
                    request(hikingTrails);
                }

            }

            @Override
            public void onFailure(Call<YelpInfo> call, Throwable t) {
                Log.e("TEST", "Failed to get the yelp info");
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

    public void request(Call hikingTrails) {
        hikingTrails.enqueue(new Callback<HikingTrails>() {
            @Override
            public void onResponse(Call<HikingTrails> call, Response<HikingTrails> response) {
                HikingTrails hikingTrails = response.body();


                List<Businesses> trails = hikingTrails.getBusinesses();


                for(Businesses business: trails) {
                    Location trailLocation = new Location(userLocation);
                    trailLocation.setLatitude(business.getCoordinates().getLatitude());
                    trailLocation.setLongitude(business.getCoordinates().getLongitude());
                    business.setDistance(userLocation.distanceTo(trailLocation) );
                }

                Collections.sort(trails);

                listView = (ListView) findViewById(R.id.trailListView);
                trailRecommendationActivityAdapter = new TrailRecommendationActivityAdapter(
                        TrailRecommendationActivity.this, R.layout.trail_selection_adapter, trails, yelpInfo, userLocation);

                listView.setAdapter(trailRecommendationActivityAdapter);
            }

            @Override
            public void onFailure(Call<HikingTrails> call, Throwable t) {
                Log.e("TEST", "Failed to get the hiking info");
                StringWriter errors = new StringWriter();
                t.printStackTrace(new PrintWriter(errors));
                Log.e("TEST", errors.toString());
            }
        });
    }
}

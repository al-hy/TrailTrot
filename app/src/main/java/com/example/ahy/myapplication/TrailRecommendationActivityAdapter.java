package com.example.ahy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yelp.fusion.client.models.Location;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ahy on 4/24/17.
 */

public class TrailRecommendationActivityAdapter extends ArrayAdapter<Businesses> {

    private Context context;
    private int layoutResource;
    private List<Businesses> trailRecommendation;
    private ImageView imageView;
    private YelpInfo yelpInfo;
    private ImageView ratingBar;
    private TextView reviewCount;
    private android.location.Location userLocation;
    private android.location.Location trailLocation;
    private TextView distance;


    public TrailRecommendationActivityAdapter(@NonNull Context context,
                                              @LayoutRes int resource, @NonNull List<Businesses> objects, YelpInfo yelpInfo, android.location.Location userLocation) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResource = resource;
        this.trailRecommendation = objects;
        this.yelpInfo = yelpInfo;
        this.userLocation = userLocation;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layoutResource, parent, false);
        final TextView trailName = (TextView) view.findViewById(R.id.trailName);
        distance = (TextView) view.findViewById(R.id.distance);
        reviewCount = (TextView) view.findViewById(R.id.recommendationReviewCount);

        /**
         * Handle Null Location Object here!
         */

        //trailLocation = new android.location.Location(userLocation);
        //trailLocation.setLatitude(trailRecommendation.get(position).getCoordinates().getLatitude());
        //trailLocation.setLongitude(trailRecommendation.get(position).getCoordinates().getLongitude());


//        float meters = userLocation.distanceTo(trailLocation);

        //double miles = Math.round((meters * 0.000621371192) * 100)/ 100.0;


        //distance.setText(Double.toString(miles) + " mi");
        if(userLocation != null) {
            distance.setText(Double.toString(Math.round((trailRecommendation.get(position).getDistance() * 0.000621371192) * 100) / 100.0) + " mi");
        }
            reviewCount.setText("(" + trailRecommendation.get(position).getReviewCount() + ")");

        /**
         *
         */

        imageView = (ImageView) view.findViewById(R.id.trailPreviewImage);
        ratingBar = (ImageView) view.findViewById(R.id.selectionRating);

        trailName.setText(trailRecommendation.get(position).getName());

        if(trailRecommendation.get(position).getImageURL().isEmpty()){
            imageView.setImageResource(R.drawable.no_image);
        } else {
            try {
                Picasso.with(context)
                        .load(trailRecommendation.get(position).getImageURL())
                        .resize(400, 300).centerCrop()
                        .placeholder(R.drawable.no_image)
                        .error(R.drawable.no_image)
                        .into(imageView);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }

        switch (Double.toString(trailRecommendation.get(position).getRating())) {
            case "0":
                ratingBar.setImageResource(R.drawable.stars_regular_0);
                break;
            case "0.5":
                ratingBar.setImageResource(R.drawable.stars_regular_0);
                break;
            case "1.0":
                ratingBar.setImageResource(R.drawable.stars_regular_1);
                break;
            case "1.5":
                ratingBar.setImageResource(R.drawable.stars_regular_1_half);
                break;
            case "2.0":
                ratingBar.setImageResource(R.drawable.stars_regular_2);
                break;
            case "2.5":
                ratingBar.setImageResource(R.drawable.stars_regular_2_half);
                break;
            case "3.0":
                ratingBar.setImageResource(R.drawable.stars_regular_3);
                break;
            case "3.5":
                ratingBar.setImageResource(R.drawable.stars_regular_3_half);
                break;
            case "4.0":
                ratingBar.setImageResource(R.drawable.stars_regular_4);
                break;
            case "4.5":
                ratingBar.setImageResource(R.drawable.stars_regular_4_half);
                break;
            case "5.0":
                ratingBar.setImageResource(R.drawable.stars_regular_5);
                break;
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SelectedTrailActivity.class);
                intent.putExtra("image", trailRecommendation.get(position).getImageURL());
                intent.putExtra("name", trailRecommendation.get(position).getName());
                intent.putExtra("yelpInfo", yelpInfo);
                intent.putExtra("coordinates", trailRecommendation.get(position).getCoordinates());
                intent.putExtra("id", trailRecommendation.get(position).getId());
                intent.putExtra("address", trailRecommendation.get(position).getAddress());
                intent.putExtra("rating", Double.toString(trailRecommendation.get(position).getRating()));
                intent.putExtra("url", trailRecommendation.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        return view;
    }

}

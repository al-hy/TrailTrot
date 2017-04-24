package com.example.ahy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ahy on 4/24/17.
 */

public class TrailRecommendationActivityAdapter extends ArrayAdapter<TrailRecommendation> {

    private Context context;
    private int layoutResource;
    private List<TrailRecommendation> trailRecommendation;

    public TrailRecommendationActivityAdapter(@NonNull Context context,
                                              @LayoutRes int resource, @NonNull List<TrailRecommendation> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResource = resource;
        this.trailRecommendation = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layoutResource, parent, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.trailPreviewImage);
        System.out.println(trailRecommendation.get(position).getTrailImage());
        imageView.setImageResource(trailRecommendation.get(position).getTrailImage());

        final TextView trailName = (TextView) view.findViewById(R.id.trailName);
        trailName.setText(trailRecommendation.get(position).getName());


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TrailSelectionActivity.class);
                intent.putExtra("image", trailRecommendation.get(position).getTrailImage());
                intent.putExtra("name", trailRecommendation.get(position).getName());
                context.startActivity(intent);
            }
        });

        //TextView trailRating = (TextView) view.findViewById(R.id.trailRating);
        //trailRating.setText(trailRecommendation.get(position).getRating());

        return view;
    }

}

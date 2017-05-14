package com.example.ahy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ahy on 5/8/17.
 */

public class TrailPhotoAdapter extends ArrayAdapter<String> {

    private Context context;
    private int layoutResource;
    private String[] trailPhotos;
    private ImageView imageView;
    private YelpInfo yelpInfo;

    public TrailPhotoAdapter(@NonNull Context context, @LayoutRes int resource,
                             @NonNull String[] trailPhotos, YelpInfo yelpInfo) {
        super(context, resource, trailPhotos);
        this.context = context;
        this.layoutResource = resource;
        this.trailPhotos = trailPhotos;
        this.yelpInfo = yelpInfo;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layoutResource, parent, false);

        int index = position;
        index++;

        imageView = (ImageView) view.findViewById(R.id.trailExtraPhotos);

        if (index < trailPhotos.length) {
            Log.i("PHOTOS URL", trailPhotos[index]);

            String url = trailPhotos[index];

            if (trailPhotos[position] == null) {
                try {
                    Picasso.with(context)
                            .load(R.drawable.no_image)
                            .resize(100, 100).centerCrop()
                            .placeholder(R.drawable.no_image)
                            .error(R.drawable.no_image)
                            .into(imageView);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            } else {

                try {
                    Picasso.with(context)
                            .load(url)
                            .resize(460, 328).centerCrop()
                            .placeholder(R.drawable.no_image)
                            .error(R.drawable.no_image)
                            .into(imageView);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }



        }
        return view;
    }

}

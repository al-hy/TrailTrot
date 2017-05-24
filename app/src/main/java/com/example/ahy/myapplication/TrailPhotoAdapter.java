package com.example.ahy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
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

public class TrailPhotoAdapter extends RecyclerView.Adapter<TrailPhotoAdapter.ViewHolder> {

    private Context context;
    private int layoutResource;
    private String[] trailPhotos;
    private ImageView imageView;
    private YelpInfo yelpInfo;

    public TrailPhotoAdapter(@NonNull String[] trailPhotos, YelpInfo yelpInfo) {
        this.trailPhotos = trailPhotos;
        this.yelpInfo = yelpInfo;

    }


    @Override
    public TrailPhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trail_selected_photos, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrailPhotoAdapter.ViewHolder holder, int position) {
        imageView = holder.extraImages;

        String url = trailPhotos[position];
        try {
            Picasso.with(context)
                    .load(url)
                    .resize(533, 400).centerCrop()
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image)
                    .into(imageView);
        }
        catch (Exception e) {
                    e.getStackTrace();
                }
    }

    @Override
    public int getItemCount() {
        return trailPhotos.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView extraImages;

        public ViewHolder(View itemView) {
            super(itemView);

            extraImages = (ImageView) itemView.findViewById(R.id.trailExtraPhotos);
        }
    }
}


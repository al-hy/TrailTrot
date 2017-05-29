package com.example.ahy.myapplication;

import android.content.Context;
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

import java.util.List;

/**
 * Created by ahy on 5/6/17.
 */

public class SelectedTrailReviewAdapter extends ArrayAdapter<Reviews> {

    private Context context;
    private int layoutResource;
    private List<Reviews> reviews;
    private ImageView imageView;
    private ImageView ratingBar;

    public SelectedTrailReviewAdapter(@NonNull Context context,
                                      @LayoutRes int resource, @NonNull List<Reviews> reviews) {
        super(context, resource, reviews);
        this.context = context;
        this.layoutResource = resource;
        this.reviews = reviews;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layoutResource, parent, false);
        imageView = (ImageView) view.findViewById(R.id.userImage);
        ratingBar = (ImageView) view.findViewById(R.id.reviewRating);
        final TextView trailReview = (TextView) view.findViewById(R.id.trailReview);
        final TextView nameOfUser = (TextView) view.findViewById(R.id.nameOfUser);

        nameOfUser.setText(reviews.get(position).getUser().getNameOfUser());
        trailReview.setText(reviews.get(position).getText() + "More Info on Yelp");

        if(reviews.get(position).getUser().getUserImage() == null){
            imageView.setImageResource(R.drawable.ic_person_black_24dp);
        } else {
            try {
                Picasso.with(context)
                        .load(reviews.get(position).getUser().getUserImage())
                        .resize(200, 200).centerCrop()
                        .into(imageView);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }

        switch (Double.toString(reviews.get(position).getRating())) {
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

        /**
         * Make each Yelp Review clickable and open up in browser
         */

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, SelectedTrailActivity.class);
//                intent.putExtra("image", trailRecommendation.get(position).getImageURL());
//                intent.putExtra("name", trailRecommendation.get(position).getName());
//                intent.putExtra("yelpInfo", yelpInfo);
//                intent.putExtra("id", trailRecommendation.get(position).getId());
//                context.startActivity(intent);
//            }
//        });

        return view;
    }

}

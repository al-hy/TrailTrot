package com.example.ahy.myapplication;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class TrailSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail_selection);

        Intent intent = getIntent();

        ImageView imageView = (ImageView) findViewById(R.id.selectedTrailPhoto) ;

        int value = intent.getIntExtra("image", 5);
        imageView.setImageResource(value);

        TextView textView = (TextView) findViewById(R.id.selectedTrailName);
        String name = intent.getStringExtra("name");
        textView.setText(name);

        TextView userReview = (TextView) findViewById(R.id.userReview);
        userReview.setText("Coming Soon!");
    }
}

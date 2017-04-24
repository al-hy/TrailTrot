package com.example.ahy.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ahy on 4/24/17.
 */

public class TrailRecommendationActivity extends AppCompatActivity{

    private List<TrailRecommendation> trailRecommendationList;
    private ListView listView;

    //When creating a listView, an adapter is needed for dynamic retrieval
    private TrailRecommendationActivityAdapter trailRecommendationActivityAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_trail);

        Intent intent = getIntent();

        initTrailRecommendation();

        Button button = (Button) findViewById(R.id.searchButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrailRecommendation trailRecommendation =
                        new TrailRecommendation("Next Nearest Trail",
                                new Random().nextInt(5),
                                R.drawable.trail_1);

            }
        });

        listView = (ListView) findViewById(R.id.trailListView);
        trailRecommendationActivityAdapter = new TrailRecommendationActivityAdapter(
                this, R.layout.trail_list_item, trailRecommendationList);

        listView.setAdapter(trailRecommendationActivityAdapter);


    }

    public void initTrailRecommendation() {
        trailRecommendationList = new ArrayList<TrailRecommendation>() {{
            add(new TrailRecommendation("Joshua Tree National Park", 4.5, R.drawable.trail_1));
            add(new TrailRecommendation("Zion National Park", 4.5, R.drawable.trail_2));
            add(new TrailRecommendation("Bryce Canyon National Park", 4.5, R.drawable.trail_3));
            add(new TrailRecommendation("Yosemite National Park", 4.5, R.drawable.trail_4));
            add(new TrailRecommendation("Death Valley Tree National Park", 4.5, R.drawable.trail_5));
            add(new TrailRecommendation("Great Basin National Park", 4.5, R.drawable.trail_6));
            add(new TrailRecommendation("Arches National Park", 4.5, R.drawable.trail_7));
        }};
    }


}

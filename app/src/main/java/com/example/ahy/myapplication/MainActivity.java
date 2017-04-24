package com.example.ahy.myapplication;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.ahy.myapplication.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button registerButton = (Button) findViewById(R.id.registerButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "Incorrect Login or Password!", Toast.LENGTH_SHORT).show();
                sendMessage(view);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Registration coming soon!", Toast.LENGTH_SHORT).show();
            }
        });

        EditText username =  (EditText) findViewById(R.id.username);
        System.out.println(username.getText().toString());
    }

    protected void sendMessage(View view) {
        Intent intent = new Intent(this, TrailRecommendationActivity.class);
        EditText editText = (EditText) findViewById(R.id.username);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}

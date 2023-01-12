package com.example.finalmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class welcome extends AppCompatActivity {

    Button lgOut;
    ImageView  map, settings, route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_welcome);
        variables();

        lgOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent= new Intent(welcome.this, MainActivity.class);
                Toast.makeText(welcome.this, "User has logged out", Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent= new Intent(welcome.this, location.class);
                startActivity(intent);

            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent= new Intent(welcome.this, com.example.finalmap.settings.class);
                startActivity(intent);
            }
        });

        route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent= new Intent(welcome.this, Route.class);
                startActivity(intent);
            }
        });
    }
    public void variables(){

        settings= findViewById(R.id.imgSettings);
        map= findViewById(R.id.imgMap);
        route = findViewById(R.id.imgRoute);
        lgOut= findViewById(R.id.btnOut);
    }
}
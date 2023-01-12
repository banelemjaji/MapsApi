package com.example.finalmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Switch;

public class settings extends AppCompatActivity {

    Switch kilo, miles;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_settings);
        variables();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent= new Intent(settings.this, welcome.class);
                startActivity(intent);
            }
        });

        if (kilo.isActivated()){

            miles.setActivated(false);
        }
        if (miles.isActivated()){
            kilo.setActivated(false);
        }

    }

    public void variables(){

        kilo= findViewById(R.id.kilo);
        miles= findViewById(R.id.miles);
        button= findViewById(R.id.button);
    }
}
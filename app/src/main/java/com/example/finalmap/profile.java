package com.example.finalmap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profile extends AppCompatActivity {
    private Button bt1, bt2, bt3;
    private ImageButton image;
    private ImageView views;
    FirebaseAuth firebaseAuth;
    private EditText name, email, start, target;
    private String userName, userEmail, userStart, userTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
        variables();
        firebaseAuth= FirebaseAuth.getInstance();
        userName=name.getText().toString();
        userEmail=email.getText().toString();
        userStart=start.getText().toString();
        userTarget=target.getText().toString();


        if (ContextCompat.checkSelfPermission(profile.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(profile.this, new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);
        }
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    sendUser();
                    Toast.makeText(profile.this, "Profile successfully updated", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(profile.this, "Profile update failed", Toast.LENGTH_SHORT).show();
                }


            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                email.setText("");
                start.setText("");
                target.setText("");
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent= new Intent(profile.this, welcome.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            views.setImageBitmap(captureImage);
        }
    }
    public void variables(){
        bt1=findViewById(R.id.button3);
        bt2=findViewById(R.id.button4);
        bt3=findViewById(R.id.btnHome);
        views=findViewById(R.id.imageView2);
        image=findViewById(R.id.imgBtn);
        name=findViewById(R.id.etName);
        email=findViewById(R.id.etAddress);
        start=findViewById(R.id.etStart);
        target=findViewById(R.id.etTarget);
    }
    public void sendUser(){

        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference myRef= firebaseDatabase.getReference(firebaseAuth.getUid());
        userProfile userProfile= new userProfile(userName, userEmail, userStart, userTarget);

        myRef.setValue(userProfile);
    }
    private Boolean validate(){
        Boolean result=false;
        userName=name.getText().toString();
        userEmail=email.getText().toString();
        userStart=start.getText().toString();
        userTarget=target.getText().toString();

        if(userName.isEmpty()|| userEmail.isEmpty()|| userStart.isEmpty()||userTarget.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result=true;
        }
        return result;
    }
}
package com.example.finalmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText memailTxt, mpassTxt;
    Button mlgnBtn;
    TextView mclickToReg;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        memailTxt= findViewById(R.id.emailTxt);
        mpassTxt= findViewById(R.id.passTxt);
        progressBar= findViewById(R.id.progressBar2);
        fAuth= FirebaseAuth.getInstance();
        mlgnBtn= findViewById(R.id.lgnBtn);
        mclickToReg= findViewById(R.id.clickToReg);

        mclickToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, regPage.class);
                startActivity(intent);
            }
        });

        mlgnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= memailTxt.getText().toString().trim();
                String password= mpassTxt.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    memailTxt.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mpassTxt.setError("Password is required");
                    return;
                }

                if(password.length()<6){
                    mpassTxt.setError("Password must be 6 characters or more");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), welcome.class));

                        }else{
                            Toast.makeText(MainActivity.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });


            }
        });


    }
}
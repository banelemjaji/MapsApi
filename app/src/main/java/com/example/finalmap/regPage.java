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

public class regPage extends AppCompatActivity {

    EditText mfullnameTxt, mregmailTxt, mregpassTxt;
    Button mregBtn;
    TextView mclickToLog;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_reg_page);
        mfullnameTxt= findViewById(R.id.fullnameTxt);
        mregmailTxt= findViewById(R.id.regmailTxt);
        mregpassTxt= findViewById(R.id.regpassTxt);
        mregBtn= findViewById(R.id.regBtn);
        mclickToLog= findViewById(R.id.clickToLog);

        mclickToLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                Intent intent= new Intent(regPage.this, MainActivity.class);
                startActivity(intent);

            }
        });


        fAuth= FirebaseAuth.getInstance();
        progressBar= findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        mregBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email= mregmailTxt.getText().toString().trim();
                String password= mregpassTxt.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mregmailTxt.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mregpassTxt.setError("Password is required");
                    return;
                }

                if(password.length()<6){
                    mregpassTxt.setError("Password must be 6 characters or more");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(regPage.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else{
                            Toast.makeText(regPage.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });



            }
        });

    }
}
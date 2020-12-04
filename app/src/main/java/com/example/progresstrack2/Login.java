package com.example.progresstrack2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private TextView reg;
    private EditText emailLog, passLog;
    private Button loginnn;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        reg = findViewById(R.id.tvReg);
        reg.setOnClickListener(this);

        loginnn = (Button) findViewById(R.id.btLogin);
        loginnn.setOnClickListener(this);

        emailLog = (EditText) findViewById(R.id.edEmailLog);

        passLog = (EditText) findViewById(R.id.edPasswordLog);

        progressBar = (ProgressBar) findViewById(R.id.progressBarLog);

        mAuth=FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tvReg:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.btLogin:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String emailEdit=emailLog.getText().toString().trim();
        String passEdit=passLog.getText().toString().trim();

        if(emailEdit.isEmpty()){
            emailLog.setError("Email is required");
            emailLog.requestFocus();
            return;
        }
        if(passEdit.isEmpty()){
            passLog.setError("Password is required");
            passLog.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailEdit).matches()){
            emailLog.setError("Please enter a valid email");
            emailLog.requestFocus();
            return;
        }
        if(passEdit.length()<6){
            passLog.setError("Min password should be is 6 characters long");
            passLog.requestFocus();
            return;

        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(emailEdit,passEdit)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            if (user.isEmailVerified()) {
                                startActivity(new Intent(Login.this, MainActivity.class));

                            }else{
                                user.sendEmailVerification();
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Login.this,"Check your email to verify your account.",Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(Login.this,"Failed to login!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
}
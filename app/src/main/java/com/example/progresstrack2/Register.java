package com.example.progresstrack2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class Register extends AppCompatActivity {

    EditText email, pass, conPass;
    Button regist;
    TextView loginClick;
    FirebaseAuth fAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email=findViewById(R.id.edEmail);
        pass=findViewById(R.id.edPassword);
        conPass=findViewById(R.id.edConPass);
        regist=findViewById(R.id.btRegister);
        loginClick=findViewById(R.id.tvLogin);

        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailidd = email.getText().toString().trim();
                String password = pass.getText().toString().trim();
                //String conPassword=conPass.getText().toString().trim();

                if (TextUtils.isEmpty(emailidd)) {
                    email.setError("Email is required.");
                }
                if (TextUtils.isEmpty(password)) {
                    pass.setError("Password is required.");
                    return;
                }
                /*if (TextUtils.isEmpty(conPassword)) {
                    pass.setError("Confirm Password is required.");
                    return;
                }*/
                if (password.length() < 6) {
                    pass.setError("Password must be more than 6 characters");
                }
                /*if(password!=conPassword)
                {
                    pass.setError("Password and confirm password doesn't match");
                }*/
                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(emailidd, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful())
                     {
                         Toast.makeText(Register.this, "User created.", Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(getApplicationContext(),MainActivity.class));

                     }else{
                         Toast.makeText(Register.this, "Error : "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                     }
                    }
                });
            }


        });

    }
}
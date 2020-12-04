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
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private TextView log;
    private EditText name,email,pass;
    private ProgressBar progressBar;
    private Button register;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        register=(Button) findViewById(R.id.btRegister);
        register.setOnClickListener(this);

        name=(EditText) findViewById(R.id.edName);
        email=(EditText)findViewById(R.id.edEmail);
        pass=(EditText)findViewById(R.id.edPassword);

        log=(TextView)findViewById(R.id.tvLogin);
        log.setOnClickListener(this);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvLogin:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.btRegister:
                registeruser();
                break;

        }

    }

    private void registeruser() {
        final String nameEdit=name.getText().toString().trim();
        final String emailEdit=email.getText().toString().trim();
        final String passEdit=pass.getText().toString().trim();

        if(nameEdit.isEmpty()) {
            name.setError("Name is required");
            name.requestFocus();
            return;
        }
        if(emailEdit.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailEdit).matches()) {
            email.setError("Please enter a valid email address");
            email.requestFocus();
            return;
        }
        if(passEdit.isEmpty()){
            pass.setError("Password is required");
            pass.requestFocus();
            return;
        }
        if(passEdit.length()<6){
            pass.setError("Min password length should be 6 characters");
            pass.requestFocus();
            return;

        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(emailEdit,passEdit)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful())
                     {
                         User user = new User(nameEdit, emailEdit, passEdit);

                         FirebaseDatabase.getInstance().getReference("Users")
                                 .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                 .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                             @Override
                             public void onComplete(@NonNull Task<Void> task) {
                                 if(task.isSuccessful()){
                                     Toast.makeText(Register.this,"User has been registered successfully",Toast.LENGTH_LONG).show();
                                     progressBar.setVisibility(View.GONE); //user got registered

                                     //redirect to login layout
                                 }else{
                                     Toast.makeText(Register.this, "Failed to register", Toast.LENGTH_LONG).show();
                                     progressBar.setVisibility(View.GONE);
                                 }
                             }
                         });

                     }else{
                         Toast.makeText(Register.this, "Failed to register", Toast.LENGTH_LONG).show();
                         progressBar.setVisibility(View.GONE);
                     }
                    }
                });




    }
}
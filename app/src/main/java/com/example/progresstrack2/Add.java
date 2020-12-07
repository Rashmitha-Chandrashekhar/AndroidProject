package com.example.progresstrack2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Add extends AppCompatActivity {

    private EditText addSub,phn;
    private Button adds;

    private ProgressBar progressBar;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    //TextView eg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        phn=(EditText)findViewById(R.id.edPhone);
        adds=(Button) findViewById(R.id.btAddSub);
        addSub=(EditText) findViewById(R.id.edAddSub);
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);

        /*eg=(TextView) findViewById(R.id.tvEg);
        Intent intent=getIntent();
        String phoneNo=intent.getStringExtra("phone");

        eg.setText(phoneNo);*/

        adds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = addSub.getText().toString();



               /* root.push().setValue(topic);  //push method creates a unique key for each entry
                root.child("01").setValue(topic);   //for topic to appear under 01
                //For multiple data to be added HashMap is used.*/


                HashMap<String, Object> addMap = new HashMap<>();
                addMap.put("Subjects", topic);
                //addMap.put("Phone",phoneNo);

                FirebaseDatabase.getInstance().getReference().child("Topics").push()
                        .setValue(addMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Add.this, "Done!", Toast.LENGTH_LONG).show();
                                    Intent i=new Intent(getApplicationContext(),MainActivity.class);

                                    //i.putExtra("phone",phnEdit);
                                    startActivity(i);

                                    //progressBar.setVisibility(View.GONE);
                                } else {
                                    Toast.makeText(Add.this, "Failed!", Toast.LENGTH_LONG).show();
                                    //progressBar.setVisibility(View.GONE);

                                }
                                //Log.i("done","onComplete:");


               /* Intent intent=getIntent();
                String str=intent.getStringExtra("phone");
                */

                                //eg.setText(str);


                            }
                        });
            }
        });
    }
}
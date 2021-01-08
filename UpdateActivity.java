package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText sub_title, sub_comment, sub_marks;
    Button update_button, delete_button;

    String subid, subtitle, subcomment, submarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        sub_title = findViewById(R.id.sub_title);
        sub_comment = findViewById(R.id.sub_comment);
        sub_marks = findViewById(R.id.sub_marks);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(subtitle);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                subtitle = sub_title.getText().toString().trim();
                subcomment =  sub_comment.getText().toString().trim();
                submarks =  sub_marks.getText().toString().trim();
                myDB.updateData(subid, subtitle, subcomment, submarks);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("subid") && getIntent().hasExtra("subtitle") &&
                getIntent().hasExtra("author1") && getIntent().hasExtra("pages1")){
            //Getting Data from Intent
            subid = getIntent().getStringExtra(" subid");
            subtitle = getIntent().getStringExtra("subtitle");
            subcomment = getIntent().getStringExtra(" subcomment");
            submarks= getIntent().getStringExtra("submarks");

            //Setting Intent Data
            sub_title.setText(subtitle);
            sub_comment.setText(subcomment);
            sub_marks.setText(submarks);
            Log.d("stev", subtitle+" "+subcomment+" "+submarks);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " +subtitle + " ?");
        builder.setMessage("Are you sure you want to delete " + subtitle + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(subid);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}

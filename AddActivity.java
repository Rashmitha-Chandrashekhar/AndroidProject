package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText main_title, main_comment, main_marks;
    Button main_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        main_title = findViewById(R.id. main_title);
        main_comment = findViewById(R.id. main_comment);
        main_marks = findViewById(R.id.main_marks);
        main_button = findViewById(R.id.main_button);
        main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(main_title.getText().toString().trim(),
                        main_comment.getText().toString().trim(),
                        Integer.valueOf( main_marks.getText().toString().trim()));
            }
        });
    }
}

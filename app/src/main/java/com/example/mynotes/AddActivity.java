package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText titleText;
    EditText contentText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        titleText=findViewById(R.id.titleText);
        contentText=findViewById(R.id.contentText);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper db =new MyDatabaseHelper(AddActivity.this);
                db.addNote(titleText.getText().toString().trim(),contentText.getText().toString().trim());
                Intent i=new Intent(AddActivity.this,MainActivity.class);
                startActivity(i);

            }
        });
    }
}
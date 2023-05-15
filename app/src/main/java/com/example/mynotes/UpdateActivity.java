package com.example.mynotes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText titleText2,contentText2;
    Button updateButton,deleteButton;
    String id,title,content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        titleText2=findViewById(R.id.titleText2);
        contentText2=findViewById(R.id.contentText2);
        updateButton=findViewById(R.id.updateButton);
        deleteButton=findViewById(R.id.deleteButton);
        getIntentAndSetData();

        ActionBar ab=getSupportActionBar();
        if(ab!=null){
            ab.setTitle(title);
        }
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            MyDatabaseHelper myDB=new MyDatabaseHelper(UpdateActivity.this);
            title =titleText2.getText().toString().trim();
            content=contentText2.getText().toString().trim();
                myDB.Update(id,title,content);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    void getIntentAndSetData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("content")){
            id=getIntent().getStringExtra("id");
            title=getIntent().getStringExtra("title");
            content=getIntent().getStringExtra("content");

            titleText2.setText(title);
            contentText2.setText(content);
        }
        else{
            Toast.makeText(UpdateActivity.this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete "+title+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper db=new MyDatabaseHelper(UpdateActivity.this);
                db.DeleteOneRow(id);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }


}
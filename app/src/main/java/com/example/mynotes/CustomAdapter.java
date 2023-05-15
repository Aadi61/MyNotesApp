package com.example.mynotes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    ArrayList notes_id,notes_title,notes_content;
    Context context;
    Activity activity;
    public CustomAdapter(Activity activity,ArrayList notes_id, ArrayList notes_title, ArrayList notes_content, Context context) {
        this.activity=activity;
        this.notes_id = notes_id;
        this.notes_title = notes_title;
        this.notes_content = notes_content;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.notes_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
    String notesid=String.valueOf(notes_id.get(position));
    String notestitle=String.valueOf(notes_title.get(position));
    String notescontent=String.valueOf(notes_content.get(position));
    holder.notes_id_text.setText(notesid);
    holder.notes_title_text.setText(notestitle);
    holder.notes_content_text.setText(notescontent);
    holder.mainLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(context,UpdateActivity.class);
            i.putExtra("id",notesid);
            i.putExtra("title",notestitle);
            i.putExtra("content",notescontent);
            activity.startActivityForResult(i,1);

        }
    });
    }

    @Override
    public int getItemCount() {
        return notes_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
    TextView notes_id_text,notes_title_text,notes_content_text;
    LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            notes_id_text=itemView.findViewById(R.id.id_number);
            notes_title_text=itemView.findViewById(R.id.textView);
            notes_content_text=itemView.findViewById(R.id.textView2);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}

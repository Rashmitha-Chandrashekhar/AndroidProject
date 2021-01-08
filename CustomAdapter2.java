package com.jovanovic.stefan.sqlitetutorial;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder> {

    private Context context1;
    private Activity activity1;
    private ArrayList sub_id,  sub_title, sub_comment, sub_marks;

    CustomAdapter2(Activity activity, Context context, ArrayList book_id1, ArrayList sub_title, ArrayList sub_comment,
                  ArrayList book_pages1){
        this.activity1 = activity1;
        this.context1 = context1;
        this.sub_id = sub_id;
        this.sub_title = sub_title;
        this.sub_comment =sub_comment;
        this.sub_marks = sub_marks;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context1);
        View view = inflater.inflate(R.layout.activity_custom_adapter2, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.sub_id_txt.setText(String.valueOf(sub_id.get(position)));
        holder.sub_title_txt.setText(String.valueOf(sub_title.get(position)));
        holder.sub_comment_txt.setText(String.valueOf(sub_comment.get(position)));
        holder.sub_marks_txt.setText(String.valueOf(sub_marks.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent intent = new Intent(context1, UpdateActivity.class);
                 intent.putExtra("subid", String.valueOf(sub_id.get(position)));
                intent.putExtra("subtopic", String.valueOf(sub_title.get(position)));
                intent.putExtra("subcomment", String.valueOf(sub_comment.get(position)));
                intent.putExtra("submarks", String.valueOf(sub_marks.get(position)));
                activity1.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return sub_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView sub_id_txt, sub_title_txt, sub_comment_txt, sub_marks_txt;
        LinearLayout mainLayout1;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sub_id_txt = itemView.findViewById(R.id.sub_id_txt);
            sub_title_txt = itemView.findViewById(R.id.sub_title_txt);
            sub_comment_txt = itemView.findViewById(R.id. sub_comment_txt);
            sub_marks_txt = itemView.findViewById(R.id.sub_marks_txt);
            mainLayout1 = itemView.findViewById(R.id.mainLayout1);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context1, R.anim.translate_anim);
            mainLayout1.setAnimation(translate_anim);
        }

    }

}

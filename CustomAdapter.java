package com.jovanovic.stefan.sqlitetutorial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList main_id,  main_title, main_comment, main_marks;

    CustomAdapter(Activity activity, Context context, ArrayList main_id, ArrayList  main_title, ArrayList main_comment,
                  ArrayList  main_marks){
        this.activity = activity;
        this.context = context;
        this.main_id =main_id;
        this. main_title = main_title;
        this.main_comment = main_comment;
        this.main_marks = main_marks;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.main_id_txt.setText(String.valueOf(main_id.get(position)));
        holder.main_title_txt.setText(String.valueOf(main_title.get(position)));
        holder.main_comment_txt.setText(String.valueOf(main_comment.get(position)));
        holder.main_marks_txt.setText(String.valueOf(main_marks.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CustomAdapter2.class);
                // intent.putExtra("id", String.valueOf(book_id.get(position)));
                //intent.putExtra("title", String.valueOf(book_title.get(position)));
                //intent.putExtra("author", String.valueOf(book_author.get(position)));
                //intent.putExtra("pages", String.valueOf(book_pages.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });



    }

    @Override
    public int getItemCount() {
        return main_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView main_id_txt,  main_title_txt, main_comment_txt, main_marks_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            main_id_txt = itemView.findViewById(R.id.main_id_txt);
            main_title_txt = itemView.findViewById(R.id.main_title_txt);
            main_comment_txt = itemView.findViewById(R.id. main_comment_txt);
            main_marks_txt = itemView.findViewById(R.id.main_marks_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}

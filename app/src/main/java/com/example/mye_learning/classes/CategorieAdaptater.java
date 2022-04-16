package com.example.mye_learning.classes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mye_learning.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategorieAdaptater{
/*
    Context context ;
    ArrayList<PopularCatigorie> list;

    public CategorieAdaptater(Context context, ArrayList<PopularCatigorie> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.popular_cat_recycle_item,parent,false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PopularCatigorie popularCatigorie = list.get(position);

        holder.catName.setText("Title :" + popularCatigorie.getTitle());
        holder.catDate.setText("Date : " + popularCatigorie.getDate());
        String imageUri = null;
        imageUri = popularCatigorie.getImg();
        Picasso.get().load(imageUri).into(holder.imgCat);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imgCat;
        TextView catName, catDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCat = itemView.findViewById(R.id.catImage);
            catName = itemView.findViewById(R.id.catName);
            catDate= itemView.findViewById(R.id.dateCat);


        }
    }*/
}

package com.example.mye_learning.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mye_learning.R;

import java.util.ArrayList;

public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.PopularCatViewHolder> {

    ArrayList<PopularCatigorie> popularCatigories;

    public CategorieAdapter(ArrayList<PopularCatigorie> popularCatigories) {
        this.popularCatigories = popularCatigories;
    }

    @NonNull
    @Override
    public PopularCatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_cat_recycle_item,null,false);
        PopularCatViewHolder viewHolder = new PopularCatViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PopularCatViewHolder holder, int position) {
        PopularCatigorie popCat = popularCatigories.get(position);
        holder.catImage.setImageResource(popCat.getImg());
        holder.catName.setText(popCat.getCatName());
    }

    @Override
    public int getItemCount() {
        return popularCatigories.size();
    }

    // Holder class for Popular Categorie :
    static class PopularCatViewHolder extends RecyclerView.ViewHolder{
        ImageView catImage;
        TextView catName;
        public PopularCatViewHolder(@NonNull View itemView) {
            super(itemView);
            catImage = itemView.findViewById(R.id.catImage);
            catName = itemView.findViewById(R.id.catName);

        }
    }
}

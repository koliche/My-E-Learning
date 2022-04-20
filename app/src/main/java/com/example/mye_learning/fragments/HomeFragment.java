package com.example.mye_learning.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mye_learning.CoursActivity;
import com.example.mye_learning.NotificationActivity;
import com.example.mye_learning.ProfielActivity;
import com.example.mye_learning.R;
import com.example.mye_learning.TestActivity;
import com.example.mye_learning.classes.PopularCatigorie;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    // My parameters :
    View view ;
    ImageView notification_image;
    private ImageView imageProfiel;
    private RecyclerView popularCat;
    private RecyclerView allCatRecycleView;
    private FirebaseRecyclerAdapter<PopularCatigorie,MyViewHolder> firebaseRecyclerAdapter;
    private FirebaseRecyclerAdapter<PopularCatigorie,MyAllViewHolder> firebaseAllRecyclerAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        notification_image = view.findViewById(R.id.notification_image);
        popularCat = view.findViewById(R.id.popularCatRecycleView);
        allCatRecycleView = view.findViewById(R.id.allCatRecycleView);
        popularCat.setHasFixedSize(true);
        allCatRecycleView.setHasFixedSize(true);
        popularCat.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        allCatRecycleView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        popularCatigorie();
        imageProfiel = view.findViewById(R.id.imageProfiel);
        imageProfiel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProfielActivity.class);
                startActivity(intent);
            }
        });
        notification_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NotificationActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        popularCatigorie();
        allCatigorie();
    }

    private void allCatigorie() {
        final Query query = FirebaseDatabase.getInstance().getReference().child("Catigorie");
        firebaseAllRecyclerAdapter = new FirebaseRecyclerAdapter<PopularCatigorie, MyAllViewHolder>(
                PopularCatigorie.class,
                R.layout.all_cat_recycle_items,
                MyAllViewHolder.class,
                query){
            @Override
            protected void populateViewHolder(MyAllViewHolder viewHolder, PopularCatigorie model, int position) {
                final String key = getRef(position).getKey();
                FirebaseDatabase.getInstance().getReference().child("Catigorie").child(key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String title = String.valueOf(snapshot.child("description").child("title").getValue());
                        viewHolder.catName.setText(title);
                        String date = String.valueOf(snapshot.child("description").child("date").getValue());
                        viewHolder.catDate.setText(date);
                        String image = String.valueOf(snapshot.child("description").child("img").getValue());
                        Picasso.get().load(image).into(viewHolder.imgCat);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                viewHolder.allcatigorie.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(),CoursActivity.class);
                        intent.putExtra("catKey",key);
                        startActivity(intent);
                    }
                });
            }
        };
        allCatRecycleView.setAdapter(firebaseAllRecyclerAdapter);
    }

    private void popularCatigorie() {
        final Query query = FirebaseDatabase.getInstance().getReference().child("Catigorie");
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PopularCatigorie, MyViewHolder>(
                PopularCatigorie.class,
                R.layout.popular_cat_recycle_item,
                MyViewHolder.class,
                query){
            @Override
            protected void populateViewHolder(MyViewHolder viewHolder, PopularCatigorie model, int position) {
                final String key = getRef(position).getKey();
                FirebaseDatabase.getInstance().getReference().child("Catigorie").child(key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String title = String.valueOf(snapshot.child("description").child("title").getValue());
                        viewHolder.catName.setText(title);
                        String date = String.valueOf(snapshot.child("description").child("date").getValue());
                        viewHolder.catDate.setText(date);
                        String image = String.valueOf(snapshot.child("description").child("img").getValue());
                        Picasso.get().load(image).into(viewHolder.imgCat);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                // this is the Onclick listner for all Items
                viewHolder.cardViewPopularCat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(),CoursActivity.class);
                        intent.putExtra("catKey",key);
                        startActivity(intent);
                    }
                });
            }
        };
        popularCat.setAdapter(firebaseRecyclerAdapter);
    }
    public static class MyAllViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCat;
        TextView catName, catDate;
        ConstraintLayout allcatigorie;
        public MyAllViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCat = itemView.findViewById(R.id.catImage1);
            catName = itemView.findViewById(R.id.catName1);
            catDate= itemView.findViewById(R.id.dateCat1);
            allcatigorie = itemView.findViewById(R.id.allcatigorie);
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCat;
        TextView catName, catDate;
        CardView cardViewPopularCat;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCat = itemView.findViewById(R.id.catImage);
            catName = itemView.findViewById(R.id.catName);
            catDate= itemView.findViewById(R.id.dateCat);
            cardViewPopularCat = itemView.findViewById(R.id.categoryId);
        }
    }
}
package com.example.mye_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mye_learning.classes.PopularCatigorie;
import com.example.mye_learning.fragments.HomeFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class TestActivity extends AppCompatActivity {

    private RecyclerView popularCat;
    private FirebaseRecyclerAdapter<PopularCatigorie, myViewHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        popularCat = findViewById(R.id.testRec);
        popularCat.setHasFixedSize(true);
        popularCat.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    @Override
    protected void onStart() {
        super.onStart();
        popularCatigorie();
    }

    private void popularCatigorie() {

        final Query query = FirebaseDatabase.getInstance().getReference().child("Catigorie");
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PopularCatigorie, myViewHolder>(
                PopularCatigorie.class,
                R.layout.popular_cat_recycle_item,
                myViewHolder.class,
                query){
            @Override
            protected void populateViewHolder(myViewHolder viewHolder, PopularCatigorie model, int position) {
                final String key = getRef(position).getKey();
                FirebaseDatabase.getInstance().getReference().child("Catigorie").child(key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String title = String.valueOf(snapshot.child("description").child("title").getValue());
                        Toast.makeText(TestActivity.this, title, Toast.LENGTH_SHORT).show();
                        viewHolder.catName.setText(title);

                        String image = String.valueOf(snapshot.child("description").child("img").getValue());
                        Picasso.get().load(image).into(viewHolder.imgCat);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        };
        popularCat.setAdapter(firebaseRecyclerAdapter);


    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCat;
        TextView catName, catDate;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCat = itemView.findViewById(R.id.catImage);
            catName = itemView.findViewById(R.id.catName);
            catDate= itemView.findViewById(R.id.dateCat);

        }
    }
}
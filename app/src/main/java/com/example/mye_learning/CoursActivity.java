package com.example.mye_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mye_learning.classes.Cours;
import com.example.mye_learning.classes.PopularCatigorie;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class CoursActivity extends AppCompatActivity {

    private RecyclerView coursRecycleView;
    private FirebaseRecyclerAdapter<Cours, myCoursViewHolder> firebaseCoursRecyclerAdapter;
    String catKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours);
        catKey = getIntent().getStringExtra("catKey");
        coursRecycleView = findViewById(R.id.coursRec);
        coursRecycleView.setHasFixedSize(true);
        coursRecycleView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        coursOfCatigorie();
    }

    private void coursOfCatigorie() {

        final Query query = FirebaseDatabase.getInstance().getReference().child("Catigorie").child(catKey).child("allCours");
        firebaseCoursRecyclerAdapter = new FirebaseRecyclerAdapter<Cours,myCoursViewHolder>(
                Cours.class,
                R.layout.cours_recycle_items,
                myCoursViewHolder.class,
                query){
            @Override
            protected void populateViewHolder(myCoursViewHolder viewHolder, Cours model, int position) {
                final String key = getRef(position).getKey();
                FirebaseDatabase.getInstance().getReference().child("Catigorie").child(catKey).child("allCours").child(key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String title = String.valueOf(snapshot.child("title").getValue());
                        Toast.makeText(CoursActivity.this, title, Toast.LENGTH_SHORT).show();
                        viewHolder.courName.setText(title);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                viewHolder.coursRecycle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(CoursActivity.this,RealCourActivity.class);
                        intent.putExtra("coursKey",key);
                        startActivity(intent);
                    }
                });

            }
        };
        coursRecycleView.setAdapter(firebaseCoursRecyclerAdapter);
    }
    public static class myCoursViewHolder extends RecyclerView.ViewHolder {
        TextView courName;
        ConstraintLayout coursRecycle;
        public myCoursViewHolder(@NonNull View itemView) {
            super(itemView);
            courName = itemView.findViewById(R.id.courName);
            coursRecycle = itemView.findViewById(R.id.coursRecycle);

        }
    }
}
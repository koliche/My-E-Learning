package com.example.mye_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mye_learning.classes.Cours;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfielActivity extends AppCompatActivity {

    RecyclerView profielRecycleView;
    FirebaseRecyclerAdapter<Cours, myProfielViewHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiel);

        profielRecycleView = findViewById(R.id.profielRecycleView);
        profielRecycleView.setHasFixedSize(true);
        profielRecycleView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        profielCoursOfCatigorie();
    }

    private void profielCoursOfCatigorie() {
        final Query query = FirebaseDatabase.getInstance().getReference().child("AllCours");
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Cours, myProfielViewHolder>(
                Cours.class,
                R.layout.profiel_cours_items,
                myProfielViewHolder.class,
                query
        ) {
            @Override
            protected void populateViewHolder(myProfielViewHolder myProfielViewHolder, Cours cours, int i) {
                final String key = getRef(i).getKey();
                FirebaseDatabase.getInstance().getReference().child("AllCours").child(key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String title = String.valueOf(snapshot.child("title").getValue());
                        Toast.makeText(ProfielActivity.this, title, Toast.LENGTH_SHORT).show();
                        myProfielViewHolder.courTitle.setText(title);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        };
        profielRecycleView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class myProfielViewHolder extends RecyclerView.ViewHolder{
        TextView courTitle;
        ConstraintLayout profielCoursRecycle;
        public myProfielViewHolder(@NonNull View itemView) {
            super(itemView);
            courTitle = itemView.findViewById(R.id.courTitle);
            profielCoursRecycle = itemView.findViewById(R.id.profielCoursRecycle);
        }
    }
}
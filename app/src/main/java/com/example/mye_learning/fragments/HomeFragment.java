package com.example.mye_learning.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mye_learning.R;
import com.example.mye_learning.classes.CategorieAdapter;
import com.example.mye_learning.classes.PopularCatigorie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // My parameters :
    View view ;
    RecyclerView popularCat;
    RecyclerView allCat;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        popularCat = view.findViewById(R.id.popularCatRecycleView);
        allCat = view.findViewById(R.id.allCatRecycleView);

        ArrayList<PopularCatigorie> catigories = new ArrayList<>();
        catigories.add(new PopularCatigorie(R.drawable.profiel,"Cat1",1));
        catigories.add(new PopularCatigorie(R.drawable.profiel,"Cat2",2));
        catigories.add(new PopularCatigorie(R.drawable.profiel,"Cat3",3));
        CategorieAdapter adapter = new CategorieAdapter(catigories);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        popularCat.setHasFixedSize(true);
        popularCat.setLayoutManager(layoutManager);
        popularCat.setAdapter(adapter);


        return view;
    }
}
package com.example.mye_learning.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mye_learning.HelpActivity;
import com.example.mye_learning.NotificationActivity;
import com.example.mye_learning.PrivacyActivity;
import com.example.mye_learning.ProfielActivity;
import com.example.mye_learning.R;
import com.example.mye_learning.TemsOfUseActivity;


public class SettingsFragment extends Fragment {
    View view;
    ConstraintLayout helpSetting;
    ConstraintLayout notificationSetting;
    ConstraintLayout darckModeSetting;
    ConstraintLayout logoutSetting;
    ConstraintLayout policySetting;
    ConstraintLayout temsOfUseSetting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        helpSetting = view.findViewById(R.id.helpSetting);
        notificationSetting = view.findViewById(R.id.notificationSetting);
        darckModeSetting = view.findViewById(R.id.darckModeSetting);
        logoutSetting = view.findViewById(R.id.logoutSetting);
        policySetting = view.findViewById(R.id.policySetting);
        temsOfUseSetting = view.findViewById(R.id.temsOfUseSetting);

        temsOfUseSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TemsOfUseActivity.class);
                startActivity(intent);
            }
        });
        policySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PrivacyActivity.class);
                startActivity(intent);
            }
        });
        notificationSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NotificationActivity.class);
                startActivity(intent);
            }
        });
        helpSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HelpActivity.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}
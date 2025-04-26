package com.example.daandharma;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    ImageView img;

    public HomeFragment() {
        // Required empty public constructor
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        img = v.findViewById(R.id.eyebanks);
        ImageView img2 = v.findViewById(R.id.skinbanks);
        ImageView img3 = v.findViewById(R.id.bodybanks);
        ImageView img4 = v.findViewById(R.id.organbanks);
        ImageView img5 = v.findViewById(R.id.ngobanks);
        ImageView img6 = v.findViewById(R.id.gobanks);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EyebanksActivity.class);
                startActivity(intent);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SkinbanksActivity.class);
                startActivity(intent);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BodydonationActivity.class);
                startActivity(intent);
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OTHospitalsActivity.class);
                startActivity(intent);
            }
        });

        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NGOActivity.class);
                startActivity(intent);
            }
        });

        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GOActivity.class);
                startActivity(intent);
            }
        });



        getActivity().setTitle("Home");
        // Inflate the layout for this fragment
        return v;
    }
}
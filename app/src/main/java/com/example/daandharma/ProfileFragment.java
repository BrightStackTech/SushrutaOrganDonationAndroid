package com.example.daandharma;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {

    TextView t1, t2;
    String username, email, pass;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        TextView t1, t2;
        t1 = (TextView) getView().findViewById(R.id.textViewhey);
        t2 = (TextView) getView().findViewById(R.id.textViewhey2);

//
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
//        String name = sharedPreferences.getString("name", "");
//        String password = sharedPreferences.getString("password", "");
//
//        t1.setText(name);
//        t2.setText(password);
        username = getActivity().getIntent().getStringExtra("key_name");
        email = getActivity().getIntent().getStringExtra("key_email");


        getUserDetails();
    }


    public void getUserDetails(){
        Database db;
        db = new Database(getActivity().getApplicationContext(), "DaanDharma", null, 1);
        ArrayList<UserModal>al = db.getLoggedinUserDetails(username,email);
        UserModal userModal = al.get(0);
        t1.setText(userModal.getuserName());
        t2.setText(userModal.getEmail());
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Your Profile");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}
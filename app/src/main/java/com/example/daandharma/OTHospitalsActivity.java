package com.example.daandharma;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OTHospitalsActivity extends AppCompatActivity {

    Toolbar toolbar;

    TextView BacktoHome;

    ImageView BacktoHomeImg;

    RecyclerView recyclerView;
    RecyclerHospitalsAdapter adapter;
    ArrayList<HospitalsModel> arrayList = new ArrayList<>();
    SearchView searchView;

    Spinner spinner;

    ArrayList<String> arrorgans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_othospitals);


        recyclerView = findViewById(R.id.HospitalsRecycler);
        searchView = findViewById(R.id.search_view);
        setData();
        prepareRecyclerView();

        searchView.setQueryHint(Html.fromHtml("<font color = #6b6f73>" + getResources().getString(R.string.city_name)+ "</font>"));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }

            private void filter(String newText) {
                ArrayList<HospitalsModel> filteredList = new ArrayList<>();
                for (HospitalsModel item : arrayList){
                    if(item.getHname().toLowerCase().contains(newText.toLowerCase())||item.getHlocation().toLowerCase().contains(newText.toLowerCase())||item.getHstate().toLowerCase().contains(newText.toLowerCase())){
                        filteredList.add(item);
                    }
                }
                adapter.filterList(filteredList);
            }
        });


        spinner = findViewById(R.id.spinner);
        arrorgans.add("Select an Organ");
        arrorgans.add("Cornea");
        arrorgans.add("Heart");
        arrorgans.add("Hand");
        arrorgans.add("Kidney");
        arrorgans.add("Liver");
        arrorgans.add("Lung");
        arrorgans.add("Pancreas");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrorgans);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String newItem = spinner.getSelectedItem().toString();
                filter1(newItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

            private void filter1(String newItem){
                ArrayList<HospitalsModel> filteredList2 = new ArrayList<>();
                for (HospitalsModel item : arrayList){
                    if(item.getHorgan().toLowerCase().contains(newItem.toLowerCase())){
                        filteredList2.add(item);
                    }
                }
                adapter.filterList2(filteredList2);
            }

        });




        toolbar = findViewById(R.id.toolbar1);

        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setTitle("ORGAN TRANSPLANT HOSPITALS");


        BacktoHomeImg = findViewById(R.id.sushrutaHomeImgg);
        BacktoHome = findViewById(R.id.sushrutaHomee);

        BacktoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OTHospitalsActivity.this, NavdrawerActivity.class));
            }
        });

        BacktoHomeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OTHospitalsActivity.this, NavdrawerActivity.class));
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.opt_menu_2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if(itemId==R.id.rate){
            Uri uri = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfcdQDrTM-xIPrH-wOGMMbIbRIowDfxbCsjTmqYj9RZlH0Jtw/viewform?embedded=true");
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        } else if (itemId==R.id.exit) {

            AlertDialog.Builder builder= new AlertDialog.Builder(OTHospitalsActivity.this);
            builder.setTitle("Exit");
            builder.setMessage("Are you sure you want to exit the application ? ");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finishAffinity();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        } else if (itemId==android.R.id.home) {
            super.onBackPressed();
        }else if (itemId==R.id.addhos) {
            Dialog dialog = new Dialog(OTHospitalsActivity.this);
            dialog.setContentView(R.layout.dialogbox_layout);

            EditText edtName = dialog.findViewById(R.id.edtName);
            EditText edtCity = dialog.findViewById(R.id.edtCity);
            EditText edtAddress = dialog.findViewById(R.id.edtAddress);
            EditText edtState = dialog.findViewById(R.id.edtState);
            EditText edtNumber = dialog.findViewById(R.id.edtNumber);
            EditText edtOrgan = dialog.findViewById(R.id.edtOrgan);

            Button btnAction = dialog.findViewById(R.id.btnAction);

            btnAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String hname="", hlocation="", haddress="", hstate="", hnum="", horgan="";

                    if(!edtName.getText().toString().equals("")){
                        hname = edtName.getText().toString();
                    }else {
                        Toast.makeText(OTHospitalsActivity.this, "Please Enter Hospital Name", Toast.LENGTH_SHORT).show();
                    }

                    if(!edtCity.getText().toString().equals("")){
                        hlocation = edtCity.getText().toString();
                    }else {
                        Toast.makeText(OTHospitalsActivity.this, "Please Enter Hospital City", Toast.LENGTH_SHORT).show();
                    }

                    if(!edtAddress.getText().toString().equals("")){
                        haddress = edtAddress.getText().toString();
                    }else {
                        Toast.makeText(OTHospitalsActivity.this, "Please Enter Hospital Address", Toast.LENGTH_SHORT).show();
                    }

                    if(!edtState.getText().toString().equals("")){
                        hstate = edtState.getText().toString();
                    }else {
                        Toast.makeText(OTHospitalsActivity.this, "Please Enter Hospital State", Toast.LENGTH_SHORT).show();
                    }

                    if(!edtNumber.getText().toString().equals("")){
                        hnum = edtNumber.getText().toString();
                    }else {
                        Toast.makeText(OTHospitalsActivity.this, "Please Enter Hospital Number", Toast.LENGTH_SHORT).show();
                    }

                    horgan = edtOrgan.getText().toString();

                    arrayList.add(new HospitalsModel(hname,hlocation,haddress,hstate,hnum,horgan));

                    adapter.notifyItemInserted(arrayList.size()-1);

                    recyclerView.scrollToPosition(arrayList.size()-1);

                    dialog.dismiss();

                }
            });

            dialog.show();

        }
        else{

        }
        return super.onOptionsItemSelected(item);
    }

    public void setData(){
        arrayList.add(new HospitalsModel("Asian Heart Institute", "Mumbai", "Malad East", "Maharashtra", "1111111111", "Heart"));
        arrayList.add(new HospitalsModel("BSES MG HOSPITAL", "Mumbai","Dadar", "Maharashtra", "1222222222", "Kidney"));
        arrayList.add(new HospitalsModel("Bhatia Hospital", "Mumbai","Andheri", "Maharashtra", "1333333333", "Liver"));
        arrayList.add(new HospitalsModel("Breach Candy Hospital", "Mumbai","Borivali", "Maharashtra", "1444444444", "Heart"));
        arrayList.add(new HospitalsModel("Dr Balabhai Hospital", "Mumbai","Bhayandar", "Maharashtra", "1555555555", "Kidney"));
        arrayList.add(new HospitalsModel("Fortis Hospital", "Mumbai","Charni Road", "Maharashtra", "1666666666", "Cornea"));
        arrayList.add(new HospitalsModel("Dr L H Hiranandani Hospitals", "Mumbai","Dahisar", "Maharashtra", "1777777777", "Liver"));
        arrayList.add(new HospitalsModel("Global Hospitals", "New Delhi","Rajiv Chowk", "Delhi NCR", "1888888888", "Hand"));
        arrayList.add(new HospitalsModel("Jupiter Hospital", "New Delhi","Chandni Chowk", "Delhi NCR", "1999999999", "Lung"));
        arrayList.add(new HospitalsModel("K E M HOSPITAL", "Mumbai","Charni Road", "Maharashtra", "2111111111", "Pancreas"));
        arrayList.add(new HospitalsModel("Kohinoor Hospital", "Kolkata","Subhaschandra Nagar", "West Bengal", "2222222222", "Hand"));
        arrayList.add(new HospitalsModel("Lilavati Hospital", "Thane","Old Belapur Road", "Maharashtra", "2333333333", "Lung"));
        arrayList.add(new HospitalsModel("Malika Hospital", "Navi Mumbai","Kamothe Road", "Maharashtra", "2444444444", "Pancreas"));
        arrayList.add(new HospitalsModel("P D Hinduja Hospital", "Mumbai","Grant Road", "Maharashtra", "2555555555", "Kidney"));
        arrayList.add(new HospitalsModel("Parakh Hospital", "Navi Mumbai","Nerul", "Maharashtra", "2666666666", "Cornea"));
        arrayList.add(new HospitalsModel("Saifee Hospital", "Pune","Sholapur Road", "Maharashtra", "2777777777", "Liver"));
        arrayList.add(new HospitalsModel("BYL Nair Charitable Hospital", "Pune","Dhankawadi Road", "Maharashtra", "2888888888", "Heart"));
    }

    public void prepareRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        preAdapter();
    }

    public void preAdapter(){
        adapter = new RecyclerHospitalsAdapter(arrayList, this, this::selectedUser);
        recyclerView.setAdapter(adapter);
    }

    public void selectedUser(HospitalsModel hospitalsModel) {
//        Toast.makeText(this, hospitalsModel.getHname(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, SelectedHospitalActivity.class).putExtra("data", hospitalsModel));
    }

}
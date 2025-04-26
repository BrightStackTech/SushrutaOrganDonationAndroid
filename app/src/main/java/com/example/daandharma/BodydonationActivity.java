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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class BodydonationActivity extends AppCompatActivity {


    Toolbar toolbar;

    TextView BacktoHome;

    ImageView BacktoHomeImg;

    RecyclerView recyclerView;
    RecyclerHospitalsAdapter adapter;
    ArrayList<HospitalsModel> arrayList = new ArrayList<>();
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodydonation);

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


        toolbar = findViewById(R.id.toolbar1);

        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setTitle("BODY DONATION");


        BacktoHomeImg = findViewById(R.id.sushrutaHomeImgg);
        BacktoHome = findViewById(R.id.sushrutaHomee);

        BacktoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BodydonationActivity.this, NavdrawerActivity.class));
            }
        });

        BacktoHomeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BodydonationActivity.this, NavdrawerActivity.class));
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

            AlertDialog.Builder builder= new AlertDialog.Builder(BodydonationActivity.this);
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
        } else if (itemId==R.id.addhos) {
            Dialog dialog = new Dialog(BodydonationActivity.this);
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
                        Toast.makeText(BodydonationActivity.this, "Please Enter Hospital Name", Toast.LENGTH_SHORT).show();
                    }

                    if(!edtCity.getText().toString().equals("")){
                        hlocation = edtCity.getText().toString();
                    }else {
                        Toast.makeText(BodydonationActivity.this, "Please Enter Hospital City", Toast.LENGTH_SHORT).show();
                    }

                    if(!edtAddress.getText().toString().equals("")){
                        haddress = edtAddress.getText().toString();
                    }else {
                        Toast.makeText(BodydonationActivity.this, "Please Enter Hospital Address", Toast.LENGTH_SHORT).show();
                    }

                    if(!edtState.getText().toString().equals("")){
                        hstate = edtState.getText().toString();
                    }else {
                        Toast.makeText(BodydonationActivity.this, "Please Enter Hospital State", Toast.LENGTH_SHORT).show();
                    }

                    if(!edtNumber.getText().toString().equals("")){
                        hnum = edtNumber.getText().toString();
                    }else {
                        Toast.makeText(BodydonationActivity.this, "Please Enter Hospital Number", Toast.LENGTH_SHORT).show();
                    }

                    horgan = edtOrgan.getText().toString();

                    arrayList.add(new HospitalsModel(hname,hlocation,haddress,hstate,hnum,horgan));

                    adapter.notifyItemInserted(arrayList.size()-1);

                    recyclerView.scrollToPosition(arrayList.size()-1);

                    dialog.dismiss();

                }
            });

            dialog.show();

        }else if (itemId==android.R.id.home) {
            super.onBackPressed();
        }
        else{

        }
        return super.onOptionsItemSelected(item);
    }

    public void setData(){
        arrayList.add(new HospitalsModel("DADHICHI MISSION", "Mumbai", "Malad East", "Maharashtra", "1111111111", ""));
        arrayList.add(new HospitalsModel("GRANT MEDICAL COLLEGE", "Mumbai","Dadar", "Maharashtra", "1222222222", ""));
        arrayList.add(new HospitalsModel("JAMSHEDJEE JEEJEEBHOY GROUP OF HOSPITALS", "Mumbai","Andheri", "Maharashtra", "1333333333", ""));
        arrayList.add(new HospitalsModel("K J SOMAIYA MEDICAL COLLEGE", "Mumbai","Borivali", "Maharashtra", "1444444444", ""));
        arrayList.add(new HospitalsModel("LOKMANYA TILAK MUNICIPAL HOSPITAL", "Mumbai","Bhayandar", "Maharashtra", "1555555555", ""));
        arrayList.add(new HospitalsModel("SETH G S MEDICAL COLLEGE", "Mumbai","Charni Road", "Maharashtra", "1666666666", ""));
        arrayList.add(new HospitalsModel("B Y L NAIR CHARITABLE HOSPITAL", "Mumbai","Dahisar", "Maharashtra", "1777777777", ""));
        arrayList.add(new HospitalsModel("ARMY COLLEGE OF MEDICAL SCIENCES", "New Delhi","Rajiv Chowk", "Delhi NCR", "1888888888", ""));
        arrayList.add(new HospitalsModel("VARDHMAN MAHAVIR MEDICAL COLLEGE", "New Delhi","Chandni Chowk", "Delhi NCR", "1999999999", ""));
        arrayList.add(new HospitalsModel("GANADARPAN MEDICAL HOSPITAL", "Kolkata","Netaji Nagar", "West Bengal", "2111111111", ""));
        arrayList.add(new HospitalsModel("Institute of Post Graduate Medical Education and Research", "Kolkata","Subhaschandra Nagar", "West Bengal", "2222222222", ""));
        arrayList.add(new HospitalsModel("RAJIV GANDHI MEDICAL COLLEGE", "Thane","Old Belapur Road", "Maharashtra", "2333333333", ""));
        arrayList.add(new HospitalsModel("MAHATMA GANDHI MISSION MEDICAL COLLEGE", "Navi Mumbai","Kamothe Road", "Maharashtra", "2444444444", ""));
        arrayList.add(new HospitalsModel("PADAMSHREE D.Y. PATIL MEDICAL COLLEGE", "Navi Mumbai","Nerul", "Maharashtra", "2555555555", ""));
        arrayList.add(new HospitalsModel("TERNA MEDICAL COLLEGE", "Navi Mumbai","Nerul", "Maharashtra", "2666666666", ""));
        arrayList.add(new HospitalsModel("ARMED FORCES MEDICAL COLLEGE", "Pune","Sholapur Road", "Maharashtra", "2777777777", ""));
        arrayList.add(new HospitalsModel("BHARATI VIDYAPEETH MEDICAL COLLEGE", "Pune","Dhankawadi Road", "Maharashtra", "2888888888", ""));
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
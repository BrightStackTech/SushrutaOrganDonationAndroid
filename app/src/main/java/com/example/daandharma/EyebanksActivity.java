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
import android.widget.LinearLayout;
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

public class EyebanksActivity extends AppCompatActivity implements RecyclerHospitalsAdapter.UserClickListener{

    Toolbar toolbar;

    TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, BacktoHome;

    ImageView BacktoHomeImg;

    LinearLayout l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;

    RecyclerView recyclerView;
    RecyclerHospitalsAdapter adapter;
    ArrayList<HospitalsModel> arrayList = new ArrayList<>();
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eyebanks);



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

        getSupportActionBar().setTitle("EYE BANKS");


        BacktoHomeImg = findViewById(R.id.sushrutaHomeImgg);
        BacktoHome = findViewById(R.id.sushrutaHomee);

        BacktoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EyebanksActivity.this, NavdrawerActivity.class));
            }
        });

        BacktoHomeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EyebanksActivity.this, NavdrawerActivity.class));
            }
        });


//        t1 = findViewById(R.id.textViewM1);
//        t2 = findViewById(R.id.textViewM2);
//        t3 = findViewById(R.id.textViewM3);
//        t4 = findViewById(R.id.textViewM4);
//        t5 = findViewById(R.id.textViewM5);
//        t6 = findViewById(R.id.textViewM6);
//        t7 = findViewById(R.id.textViewM7);
//        l1 = findViewById(R.id.layoutM1);
//        l2 = findViewById(R.id.layoutM2);
//        l3 = findViewById(R.id.layoutM3);
//        l4 = findViewById(R.id.layoutM4);
//        l5 = findViewById(R.id.layoutM5);
//        l6 = findViewById(R.id.layoutM6);
//        l7 = findViewById(R.id.layoutM7);
//        l1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(EyebanksActivity.this,SelectedHospitalActivity.class));
//            }
//        });
//
//        l2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(EyebanksActivity.this,SelectedHospitalActivity.class));
//            }
//        });
//
//        l3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(EyebanksActivity.this,SelectedHospitalActivity.class));
//            }
//        });
//
//        l4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(EyebanksActivity.this,SelectedHospitalActivity.class));
//            }
//        });
//
//        l5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(EyebanksActivity.this,SelectedHospitalActivity.class));
//            }
//        });
//
//        l6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(EyebanksActivity.this,SelectedHospitalActivity.class));
//            }
//        });
//
//        l7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(EyebanksActivity.this,SelectedHospitalActivity.class));
//            }
//        });

//        SearchView searchview;
//        RecyclerView recyclerview;
//        HospitalsModel model = new HospitalsModel( "A", "1234567890");
//        arrayList.add(model);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
//        recyclerView.setAdapter(adapter);
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

            AlertDialog.Builder builder= new AlertDialog.Builder(EyebanksActivity.this);
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
            Dialog dialog = new Dialog(EyebanksActivity.this);
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
                        Toast.makeText(EyebanksActivity.this, "Please Enter Hospital Name", Toast.LENGTH_SHORT).show();
                    }

                    if(!edtCity.getText().toString().equals("")){
                        hlocation = edtCity.getText().toString();
                    }else {
                        Toast.makeText(EyebanksActivity.this, "Please Enter Hospital City", Toast.LENGTH_SHORT).show();
                    }

                    if(!edtAddress.getText().toString().equals("")){
                        haddress = edtAddress.getText().toString();
                    }else {
                        Toast.makeText(EyebanksActivity.this, "Please Enter Hospital Address", Toast.LENGTH_SHORT).show();
                    }

                    if(!edtState.getText().toString().equals("")){
                        hstate = edtState.getText().toString();
                    }else {
                        Toast.makeText(EyebanksActivity.this, "Please Enter Hospital State", Toast.LENGTH_SHORT).show();
                    }

                    if(!edtNumber.getText().toString().equals("")){
                        hnum = edtNumber.getText().toString();
                    }else {
                        Toast.makeText(EyebanksActivity.this, "Please Enter Hospital Number", Toast.LENGTH_SHORT).show();
                    }

                    horgan = edtOrgan.getText().toString();

                    arrayList.add(new HospitalsModel(hname,hlocation,haddress,hstate,hnum,horgan));

                    adapter.notifyItemInserted(arrayList.size()-1);

                    recyclerView.scrollToPosition(arrayList.size()-1);

                    dialog.dismiss();

                }
            });

            dialog.show();

        } else{

        }
        return super.onOptionsItemSelected(item);
    }

    public void setData(){
        arrayList.add(new HospitalsModel("ADITYA JYOTI EYE HOSPITAL", "Mumbai", "Malad East", "Maharashtra", "1111111111", ""));
        arrayList.add(new HospitalsModel("Arpan Eye Bank", "Mumbai","Dadar", "Maharashtra", "1222222222", ""));
        arrayList.add(new HospitalsModel("B.Y.L Nair Charitable Hospital", "Mumbai","Andheri", "Maharashtra", "1333333333", ""));
        arrayList.add(new HospitalsModel("Conwest Jain Group of Hospital Eye Bank", "Mumbai","Borivali", "Maharashtra", "1444444444", ""));
        arrayList.add(new HospitalsModel("Dr L H Hiranandani Hospital", "Mumbai","Naigoan", "Maharashtra", "1555555555", ""));
        arrayList.add(new HospitalsModel("Dr R N Cooper Hospital and Eye Bank", "Mumbai","Bandra", "Maharashtra", "1666666666", ""));
        arrayList.add(new HospitalsModel("Eye Bank coordination And Research Centre", "Mumbai","Ghatkopar", "Maharashtra", "1777777777", ""));
        arrayList.add(new HospitalsModel("Fortis Hospital", "Mumbai","Mumbai Central", "Maharashtra", "1888888888", ""));
        arrayList.add(new HospitalsModel("GOKHALE EYE HOSPITAL", "Mumbai","Jogeshwari", "Maharashtra", "1999999999", ""));
        arrayList.add(new HospitalsModel("K.J.Somaiya Hospital", "Mumbai","Dadar", "Maharashtra", "2111111111", ""));
        arrayList.add(new HospitalsModel("K.E.M HOSPITAL", "Mumbai","Churchgate", "Maharashtra", "2222222222", ""));
        arrayList.add(new HospitalsModel("Kandivali Eye Donation", "Mumbai","Kandivali", "Maharashtra", "2333333333", ""));
        arrayList.add(new HospitalsModel("Lotus Eye Hospital", "Mumbai","Dahisar", "Maharashtra", "2444444444", ""));
        arrayList.add(new HospitalsModel("Mahatme Eye Bank", "Mumbai","Mira Road", "Maharashtra", "2555555555", ""));
        arrayList.add(new HospitalsModel("Navnit Shah Eyebank", "Mumbai","Bhayandar", "Maharashtra", "2666666666", ""));
        arrayList.add(new HospitalsModel("Rajawadi Hospital", "Mumbai","Virar", "Maharashtra", "2777777777", ""));
        arrayList.add(new HospitalsModel("Shroff Eye Clinic", "Mumbai","Matunga Road", "Maharashtra", "2888888888", ""));
        arrayList.add(new HospitalsModel("Tarun Mitra Mandal","Mumbai","Prabhadevi", "Maharashtra", "2999999999", ""));
        arrayList.add(new HospitalsModel("Cardinal Gracias Memorial Hospital", "Thane","Thane East", "Maharashtra", "3111111111", ""));
        arrayList.add(new HospitalsModel("Matrushree Gomtiben Ratanshi cheda Sahiyara Eye Bank", "Thane","Thane West", "Maharashtra", "3222222222", ""));
        arrayList.add(new HospitalsModel("D Y PATIL HOSPITAL", "Navi Mumbai","Shewri East", "Maharashtra", "4111111111", ""));
        arrayList.add(new HospitalsModel("Laxmi Eye Bank","Navi Mumbai","Shewri West", "Maharashtra", "4222222222", ""));
        arrayList.add(new HospitalsModel("ICARE Eye Hospital and Post Graaduate Institute", "Noida","Rajiv Chowk", "Delhi NCR", "5111111111", ""));
        arrayList.add(new HospitalsModel("A.EDWARD MAUMENEE EYE BANK", "New Delhi","Kashmere Gate", "Delhi NCR", "6111111111", ""));
        arrayList.add(new HospitalsModel("CENTRE FOR SIGHT", "New Delhi","Chandni Chowk", "Delhi NCR", "6222222222", ""));
        arrayList.add(new HospitalsModel("Dr Shroff Charity Eye Hospital", "New Delhi","Azadpur", "Delhi NCR", "6333333333", ""));
        arrayList.add(new HospitalsModel("GURU GOBIND SINGH EYE BANK", "New Delhi","Chattarpur", "Delhi NCR", "6444444444", ""));
        arrayList.add(new HospitalsModel("Guru Nanak Eye Centre", "New Delhi","Janakpuri West", "Delhi NCR", "6555555555", ""));
        arrayList.add(new HospitalsModel("INDRAPRASTA APOLLO HOSPITAL", "New Delhi","Botanical Garden", "Delhi NCR", "6666666666", ""));
        arrayList.add(new HospitalsModel("Sight Life India", "New Delhi","Mandi House", "Delhi NCR", "6777777777", ""));
        arrayList.add(new HospitalsModel("Venu Eye Institute", "New Delhi","Anand Vihar", "Delhi NCR", "6888888888", ""));
        arrayList.add(new HospitalsModel("DISHA EYE HOSPITAL", "Kolkata","Rabindra Sadan", "West Bengal", "7111111111", ""));
        arrayList.add(new HospitalsModel("International Eye Bank", "Kolkata","Netaji Bhavan", "West Bengal", "7222222222", ""));
        arrayList.add(new HospitalsModel("Sankara Nethralaya", "Kolkata","Naopara", "West Bengal", "7333333333", ""));
        arrayList.add(new HospitalsModel("Susrut Eye Foundation", "Kolkata","Kalighat", "West Bengal", "7444444444", ""));
        arrayList.add(new HospitalsModel("ASO PALOV EYE BANK", "Ahemdabad","Ahemdabad East", "Gujarat", "8111111111", ""));
        arrayList.add(new HospitalsModel("DR DHANIJISHA ANKLESARIA EYE BANK", "Ahemdabad","Ahemdabad West", "Gujarat", "8222222222", ""));
        arrayList.add(new HospitalsModel("LATA MANGESHKAR HOSPITAL", "Nagpur","Jayprakash Nagar", "Maharashtra", "9111111111", ""));
        arrayList.add(new HospitalsModel("MADHAV NETRA PEDHI", "Nagpur","Khapri", "Maharashtra", "9222222222", ""));
        arrayList.add(new HospitalsModel("MAHATMA EYE BANK", "Nagpur","Ujwal Nagar", "Maharashtra", "9333333333", ""));
        arrayList.add(new HospitalsModel("SURAJ EYE BANK", "Nagpur","Kasturchand Park", "Maharashtra", "9444444444", ""));
        arrayList.add(new HospitalsModel("GRANT MEDICAL RUBY HALL CLINIC", "Pune","Shaniwar Peth", "Maharashtra", "1011111111", ""));
    }

    public void prepareRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        preAdapter();
    }

    public void preAdapter(){
        adapter = new RecyclerHospitalsAdapter(arrayList, this, this::selectedUser);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void selectedUser(HospitalsModel hospitalsModel) {
//        Toast.makeText(this, hospitalsModel.getHname(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, SelectedHospitalActivity.class).putExtra("data", hospitalsModel));
    }
}
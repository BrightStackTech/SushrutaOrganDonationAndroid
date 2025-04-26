package com.example.daandharma;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SelectedHospitalActivity extends AppCompatActivity {

    Toolbar toolbar;

    ImageView BacktoHomeImg;

    TextView tvSelectedHospitalName, tvSelectedHospitalLocation, tvSelectedHospitalAddress, tvSelectedHospitalState, tvSelectedHospitalNum, BacktoHome;
    HospitalsModel hospitalsModel;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_hospital);
        tvSelectedHospitalName = findViewById(R.id.tvSelectedHospitalName);
        tvSelectedHospitalLocation = findViewById(R.id.tvSelectedHospitalLocation);
        tvSelectedHospitalAddress = findViewById(R.id.tvSelectedHospitalAddress);
        tvSelectedHospitalState = findViewById(R.id.tvSelectedHospitalState);
        tvSelectedHospitalNum = findViewById(R.id.tvSelectedHospitalNum);
        intent = getIntent();

        if(intent!=null){
            hospitalsModel= (HospitalsModel) intent.getSerializableExtra("data");
            tvSelectedHospitalName.setText(hospitalsModel.getHname());
            tvSelectedHospitalLocation.setText(hospitalsModel.getHlocation());
            tvSelectedHospitalAddress.setText(hospitalsModel.getHaddress());
            tvSelectedHospitalState.setText(hospitalsModel.getHstate());
            tvSelectedHospitalNum.setText(hospitalsModel.getHnum());
        }

        toolbar = findViewById(R.id.toolbar1);

        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setTitle(hospitalsModel.getHname());


        BacktoHomeImg = findViewById(R.id.sushrutaHomeImgg);
        BacktoHome = findViewById(R.id.sushrutaHomee);

        BacktoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectedHospitalActivity.this, NavdrawerActivity.class));
            }
        });

        BacktoHomeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectedHospitalActivity.this, NavdrawerActivity.class));
            }
        });


        tvSelectedHospitalNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+hospitalsModel.getHnum()));
                startActivity(intent);
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

            AlertDialog.Builder builder= new AlertDialog.Builder(SelectedHospitalActivity.this);
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
        }
        else{

        }
        return super.onOptionsItemSelected(item);
    }
}
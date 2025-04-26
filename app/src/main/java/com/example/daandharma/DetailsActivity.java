package com.example.daandharma;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetailsActivity extends AppCompatActivity {

    Toolbar toolbar;

    ImageView BacktoHomeImg;

    TextView tvSelectedHospitalName, tvSelectedHospitalLocation, tvSelectedHospitalAddress, tvSelectedHospitalState, tvSelectedHospitalNum, BacktoHome;

    TextView markertxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
//        markertxt = findViewById(R.id.marker);

        tvSelectedHospitalName = findViewById(R.id.tvSelectedHospitalName1);
//        tvSelectedHospitalLocation = findViewById(R.id.tvSelectedHospitalLocation1);
//        tvSelectedHospitalAddress = findViewById(R.id.tvSelectedHospitalAddress1);
//        tvSelectedHospitalState = findViewById(R.id.tvSelectedHospitalState1);
//        tvSelectedHospitalNum = findViewById(R.id.tvSelectedHospitalNum1);

        String title = getIntent().getStringExtra("title");
//        String hname = getIntent().getStringExtra("hname");
//        String hlocation = getIntent().getStringExtra("hlocation");
//        String haddress = getIntent().getStringExtra("haddress");
//        String hstate = getIntent().getStringExtra("hstate");
//        String hnum = getIntent().getStringExtra("hnum");

//        markertxt.setText(title);

        tvSelectedHospitalName.setText(title);
//        tvSelectedHospitalLocation.setText(hlocation);
//        tvSelectedHospitalAddress.setText(haddress);
//        tvSelectedHospitalState.setText(hstate);
//        tvSelectedHospitalNum.setText(hnum);

        toolbar = findViewById(R.id.toolbar1);

        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setTitle(title);


        BacktoHomeImg = findViewById(R.id.sushrutaHomeImgg);
        BacktoHome = findViewById(R.id.sushrutaHomee);

        BacktoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailsActivity.this, NavdrawerActivity.class));
            }
        });

        BacktoHomeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailsActivity.this, NavdrawerActivity.class));
            }
        });
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if(itemId==R.id.rate){
            Uri uri = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfcdQDrTM-xIPrH-wOGMMbIbRIowDfxbCsjTmqYj9RZlH0Jtw/viewform?embedded=true");
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        } else if (itemId==R.id.exit) {

            AlertDialog.Builder builder= new AlertDialog.Builder(DetailsActivity.this);
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

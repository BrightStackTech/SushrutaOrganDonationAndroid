package com.example.daandharma;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class NGOActivity extends AppCompatActivity {

    Toolbar toolbar;

    TextView BacktoHome;

    ImageView BacktoHomeImg;

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient fusedLocationProviderClient;

    HospitalsModel hospitalsModel;

    private GoogleMap mMap;
    ArrayList<LatLng>arraylist=new ArrayList<LatLng>();
    LatLng ngo1 = new LatLng(19.160204296699288, 72.85137288920318); //Apex Kidney Foundation
    LatLng ngo2 = new LatLng(19.177871777423952, 72.96137618929833); //The Federation of Organ & Body Donation
    LatLng ngo3 = new LatLng(19.110483651274926, 72.86252553553372); //Narmada Kidney Foundation
    LatLng ngo4 = new LatLng(19.038287514598167, 72.85956361378094); //NATIONAL DECEASED DONOR TRANSPLANT NETWORK


    ArrayList<String> title = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngoactivity);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_maps);

        fusedLocationProviderClient = (FusedLocationProviderClient) LocationServices.getFusedLocationProviderClient(this);
        Dexter.withContext(getApplicationContext()).withPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        getCurrentLocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();


        toolbar = findViewById(R.id.toolbar1);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setTitle("NON GOVERNMENTAL ORGANISATIONS");


        BacktoHomeImg = findViewById(R.id.sushrutaHomeImgg);
        BacktoHome = findViewById(R.id.sushrutaHomee);

        BacktoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NGOActivity.this, NavdrawerActivity.class));
            }
        });

        BacktoHomeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NGOActivity.this, NavdrawerActivity.class));
            }
        });

        arraylist.add(ngo1);
        arraylist.add(ngo2);
        arraylist.add(ngo3);
        arraylist.add(ngo4);

        title.add("Apex Kidney Foundation");
        title.add("The Federation of Organ & Body Donation");
        title.add("Narmada Kidney Foundation");
        title.add("NATIONAL DECEASED DONOR TRANSPLANT NETWORK");

//        title.add(new HospitalsModel("Apex Kidney Foundation", "Mumbai", "Malad East", "Maharashtra", "1111111111", ""));
//        title.add(new HospitalsModel("The Federation of Organ & Body Donation", "Mumbai","Dadar", "Maharashtra", "1222222222", ""));
//        title.add(new HospitalsModel("Narmada Kidney Foundation", "Mumbai","Andheri", "Maharashtra", "1333333333", ""));
//        title.add(new HospitalsModel("NATIONAL DECEASED DONOR TRANSPLANT NETWORK", "Mumbai","Borivali", "Maharashtra", "1444444444", ""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.opt_menu_2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.rate) {
            Uri uri = Uri.parse("hhttps://docs.google.com/forms/d/e/1FAIpQLSfcdQDrTM-xIPrH-wOGMMbIbRIowDfxbCsjTmqYj9RZlH0Jtw/viewform?embedded=true");
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        } else if (itemId == R.id.exit) {

            AlertDialog.Builder builder = new AlertDialog.Builder(NGOActivity.this);
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
        } else if (itemId == android.R.id.home) {
            super.onBackPressed();
        }else{
        }
        return super.onOptionsItemSelected(item);
    }


    public void getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(@NonNull GoogleMap googleMap) {
                        mMap = googleMap;
                        if(location != null){
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Your Location");
                            mMap.addMarker(markerOptions);
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                            for(int i=0; i<arraylist.size();i++){
                                for(int j=0; j<title.size();j++){
                                    mMap.addMarker(new MarkerOptions().position(arraylist.get(i)).title(String.valueOf(title.get(i))));
                                }
                            }
                            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
                                @Override
                                public boolean onMarkerClick(Marker marker){
                                    for(int i = 0; i<arraylist.size(); i++){
                                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(arraylist.get(i),5));
                                    }
                                    String markertitle = marker.getTitle();
//                                    String markername = hospitalsModel.getHname();
//                                    String markerlocation = hospitalsModel.getHlocation();
//                                    String markeraddress = hospitalsModel.getHaddress();
//                                    String markerstate = hospitalsModel.getHstate();
//                                    String markernum = hospitalsModel.getHnum();
                                    Intent i = new Intent(NGOActivity.this,DetailsActivity.class);
                                    i.putExtra("title",markertitle);
//                                    i.putExtra("hname",markername);
//                                    i.putExtra("hlocation",markerlocation);
//                                    i.putExtra("haddress",markeraddress);
//                                    i.putExtra("hstate", markerstate);
//                                    i.putExtra("hnum", markernum);
                                    startActivity(i);
                                    return false;
                                }
                            });

                        }else{
                            Toast.makeText(NGOActivity.this, "Please turn on your location", Toast.LENGTH_SHORT).show();
                        }

                    }

                });
            }
        });
    }

}
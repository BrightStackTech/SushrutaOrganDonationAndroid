package com.example.daandharma;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class NavdrawerActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

//    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navdrawer);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        ImageView BacktoHomeImg = findViewById(R.id.sushrutaHomeImg);
        TextView BacktoHome = findViewById(R.id.sushrutaHome);

        BacktoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new HomeFragment());
            }
        });

        BacktoHomeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new HomeFragment());
            }
        });

        setSupportActionBar(toolbar);

//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(NavdrawerActivity.this,EyebanksActivity.class));
//            }
//        });



//        if(getSupportActionBar()!=null){
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setTitle("Home");
//        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        drawerLayout.addDrawerListener(toggle);


        toggle.syncState();

        loadFragment(new HomeFragment());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id==R.id.optHelpLine) {
                    loadFragment(new HelplineFragment());

                } else if(id==R.id.optHome){
                    loadFragment(new HomeFragment());
//                    getSupportActionBar().setTitle("Home");

                } else if (id==R.id.optWebsite) {
//                    Toast.makeText(NavdrawerActivity.this, "Website", Toast.LENGTH_SHORT).show();
                    loadFragment(new WebsiteFragment());
//                    getSupportActionBar().setTitle("Website");

                } else if(id==R.id.optAboutUs){
//                    Toast.makeText(NavdrawerActivity.this, "About Us", Toast.LENGTH_SHORT).show();
                    loadFragment(new AboutUsFragment());
//                    getSupportActionBar().setTitle("About Us");
                }
//                else if(id==R.id.optaddyh){
//                    public boolean addviamain;
//                    addviamain = true;
//                }
                else if(id==R.id.optProfile){
                    loadFragment(new ProfileFragment());}
                else if(id==R.id.optLogOut){
//                    Toast.makeText(NavdrawerActivity.this, "Log Out", Toast.LENGTH_SHORT).show();
//                    getSupportActionBar().setTitle("Log Out");
                    logoutMenu();
                }else{

                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        new MenuInflater(this).inflate(R.menu.opt_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int itemId = item.getItemId();

        if(itemId==R.id.share_online){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Check out our new Organ Sharing App");
            intent.putExtra(Intent.EXTRA_TEXT, "Our Application Link here : https://drive.google.com/file/d/1soCCzYoPTEe2Y245SzY3vBFPKYYQJx7v/view?usp=drivesdk");
            startActivity(Intent.createChooser(intent, "Share Via"));
//            Toast.makeText(NavdrawerActivity.this, "Shared Online", Toast.LENGTH_SHORT).show();
        } else if (itemId==R.id.share_qr) {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.qr_code_layout);
            dialog.show();
//            Toast.makeText(NavdrawerActivity.this, "QR CODE", Toast.LENGTH_SHORT).show();
        } else if (itemId==R.id.rate) {
            Uri uri = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfcdQDrTM-xIPrH-wOGMMbIbRIowDfxbCsjTmqYj9RZlH0Jtw/viewform?embedded=true");
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
//            Toast.makeText(NavdrawerActivity.this, "Rate Us", Toast.LENGTH_SHORT).show();
        }else if (itemId==R.id.exit){
            AlertDialog.Builder builder= new AlertDialog.Builder(NavdrawerActivity.this);
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
//            Toast.makeText(NavdrawerActivity.this, "EXIT APPLICATION", Toast.LENGTH_SHORT).show();
        }else{

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    private void logoutMenu(){
        AlertDialog.Builder builder= new AlertDialog.Builder(NavdrawerActivity.this);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout ? ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = pref.edit();
                editor1.putBoolean("flag",false);
                editor1.apply();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.container, fragment).addToBackStack(null);
        ft.commit();
    }
}
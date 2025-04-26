package com.example.daandharma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edUsername, edPassword, edEmail;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.editTextLoginUsername);
        edPassword = findViewById(R.id.editTextLoginPassword);
        edEmail = findViewById(R.id.editTextLoginEmail);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textViewNewuser);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                String email = edEmail.getText().toString();
                Database db = new Database(getApplicationContext(), "DaanDharma", null, 1);
                if(username.length()==0 || password.length()==0 || email.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill All details",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(db.login(username, email, password)==1){
                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("username", username);
                        editor.apply();
//                        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
//                        SharedPreferences.Editor editor1 = pref.edit();
//                        editor.putBoolean("flag",true);
//                        editor1.apply();
                        Intent intent = new Intent(LoginActivity.this,NavdrawerActivity.class);
                        intent.putExtra("key_name", username);
                        intent.putExtra("key_email", email);
                        intent.putExtra("key_pass", password);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed(){
        finishAffinity();
    }
}
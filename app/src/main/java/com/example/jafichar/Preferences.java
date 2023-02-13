package com.example.jafichar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Preferences extends AppCompatActivity {
    private EditText etIP;
    private EditText etPort;
    private EditText etUser;
    private Button guardar;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);
        etIP = findViewById(R.id.etIP);
        etPort = findViewById(R.id.etPort);
        etUser = findViewById(R.id.etUser);
        guardar = findViewById(R.id.btnGuardar);
        bottomNav = findViewById(R.id.main_menu);
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();


        if (sharedPref.contains("etIP")) {
            etIP.setText(sharedPref.getString("etIP", ""));
        }
        if (sharedPref.contains("etPort")) {
            etPort.setText(sharedPref.getString("etPort", ""));
        }
        if (sharedPref.contains("username")) {
            etUser.setText(sharedPref.getString("username", ""));
        }
        guardar.setOnClickListener(v -> {
            editor.putString("etIP", etIP.getText().toString());
            editor.putString("etPort", etPort.getText().toString());
            editor.putString("etUser", etUser.getText().toString());
            editor.apply();
            Toast.makeText(getApplicationContext(), "Datos guardados", Toast.LENGTH_SHORT).show();
        });
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mainIntent);
                        return true;
                    default:
                        return false;
                }
            }
        });

    }
}
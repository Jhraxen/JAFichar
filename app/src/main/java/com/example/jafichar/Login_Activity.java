package com.example.jafichar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Login_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemClock.sleep(400);
        setTheme(R.style.Theme_JAFichar);
        setContentView(R.layout.activity_login);
        Button btnSignIn = findViewById(R.id.btnSignIn);
        Button btnSave = findViewById(R.id.btnSave);
        EditText txtUsername = findViewById(R.id.txtUsername);
        EditText txtPassword = findViewById(R.id.txtPassword);
        TextView txtRecordListener = findViewById(R.id.txtSignUp);
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (sharedPref.contains("username")) {
            txtUsername.setText(sharedPref.getString("username", ""));
        }
        if (sharedPref.contains("password")) {
            txtPassword.setText(sharedPref.getString("password", ""));
        }


        btnSave.setOnClickListener(v -> {
            editor.putString("username", txtUsername.getText().toString());
            editor.putString("password", txtPassword.getText().toString());
            editor.apply();
        });

        btnSignIn.setOnClickListener(v -> {
            if ((txtUsername.getText().toString().equals("admin") || txtUsername.getText().toString().equals("marc")) &&
                            (txtPassword.getText().toString().equals("admin") || txtPassword.getText().toString().equals("marc"))) {
                Intent intent = new Intent(this, MainActivity.class);
                txtRecordListener.setText("Login correcto");
                startActivity(intent);
            } else {
                txtRecordListener.setText("Login incorrecto");
            }
        });
    }
}

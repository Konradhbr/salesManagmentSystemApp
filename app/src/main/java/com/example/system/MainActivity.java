package com.example.system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        login(R.layout.activity_worker);
        super.onCreate(savedInstanceState);

    }

    public void login(int v) {
        Intent login = new Intent(com.example.system.MainActivity.this, Worker.class);
        startActivity(login);
    }
}
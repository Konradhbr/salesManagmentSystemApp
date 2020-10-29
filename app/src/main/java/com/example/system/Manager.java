package com.example.system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Manager extends AppCompatActivity {
    Button messageBTN ,dataBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        messageBTN = findViewById(R.id.message);
        dataBTN = findViewById(R.id.charts);


        messageBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mes = new Intent(com.example.system.Manager.this, MessagesList.class);
                startActivity(mes);
            }
        });

        dataBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent(com.example.system.Manager.this, Charts.class);
                startActivity(data);
            }
        });
    };
}
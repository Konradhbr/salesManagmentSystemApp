package com.example.system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Manager extends AppCompatActivity {
    Button messageBTN ,chart1BTN , chart2BTN , chart3BTN , logoutBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        messageBTN = findViewById(R.id.message);
        chart1BTN = findViewById(R.id.chart1);
        chart2BTN = findViewById(R.id.chart2);
        chart3BTN = findViewById(R.id.chart3);
        logoutBTN = findViewById(R.id.logout);

        messageBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mes = new Intent(com.example.system.Manager.this, MessagesList.class);
                startActivity(mes);
            }
        });

        chart1BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chart1 = new Intent(com.example.system.Manager.this, SellingTimeChart.class);
                startActivity(chart1);
            }
        });

        chart2BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chart2 = new Intent(com.example.system.Manager.this, SoldProductsChart.class);
                startActivity(chart2);
            }
        });

        chart3BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chart3 = new Intent(com.example.system.Manager.this, InStockChart.class);
                startActivity(chart3);
            }
        });

        logoutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(com.example.system.Manager.this, LoginPanel.class);
                startActivity(logout);
            }
        });
    };
}
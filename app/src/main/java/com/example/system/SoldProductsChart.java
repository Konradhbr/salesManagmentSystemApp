package com.example.system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class SoldProductsChart extends AppCompatActivity {

    DatabaseReference dataReff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sold_products_chart);

        dataReff = FirebaseDatabase.getInstance().getReference().child("Sold Product");

        dataReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                BarChart barChart = findViewById(R.id.chart2);
                ArrayList<BarEntry> staff = new ArrayList<>();
                int cena = 0;
                int countValue ,countMultiplier;
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String value = String.valueOf(snapshot1.child("productPrice").getValue());
                    String multiplier = String.valueOf(snapshot1.child("soldAmount").getValue());
                    countValue = Integer.parseInt(value);
                    countMultiplier = Integer.parseInt(multiplier);
                    cena += countValue * countMultiplier;
                }
                staff.add(new BarEntry(1, cena));
                staff.add(new BarEntry(2, 1000));

                BarDataSet barDataSet = new BarDataSet(staff, "Sprzedane elektronarzędzia , Cel miesiąca");
                barDataSet.setColors(ColorTemplate.PASTEL_COLORS);
                barDataSet.setValueTextColor(Color.BLACK);
                barDataSet.setValueTextSize(15f);

                BarData barData = new BarData(barDataSet);

                barChart.setFitBars(true);
                barChart.setData(barData);
                barChart.getDescription().setText("Ilość sprzedanych elektronarzędzi");
                barChart.animateY(5);
            }
                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){

                }

            });
    }
}
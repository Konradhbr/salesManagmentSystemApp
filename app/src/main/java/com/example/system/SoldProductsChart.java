package com.example.system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Radar;
import com.anychart.core.radar.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Align;
import com.anychart.enums.MarkerType;
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
                int amountOfProduct = 0;

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        String value = String.valueOf(snapshot1.child("productAmount").getValue());
                        amountOfProduct += Integer.parseInt(value);
                }
                staff.add(new BarEntry(1, amountOfProduct));
                staff.add(new BarEntry(2, 50));

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
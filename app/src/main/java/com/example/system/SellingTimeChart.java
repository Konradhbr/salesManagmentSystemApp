package com.example.system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SellingTimeChart extends AppCompatActivity {

    DatabaseReference dataReff;
    int before12 = 0;
    int after12 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling_time_chart);

        dataReff = FirebaseDatabase.getInstance().getReference().child("Sold Product");

        dataReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String value = String.valueOf(snapshot1.child("date").getValue());
                    int ilosc = Integer.parseInt(value.substring(0,2));
                    if (ilosc > 11) {after12 +=1;}
                    else {before12 +=1;}
                }

                Pie pie = AnyChart.pie();
                List<DataEntry> data = new ArrayList<>();
                data.add(new ValueDataEntry("Sprzedaż po godzinie 12", after12));
                data.add(new ValueDataEntry("Sprzedaż przed godziną 12", before12));

                pie.data(data);

                AnyChartView anyChartView = (AnyChartView) findViewById(R.id.chart1);
                anyChartView.setChart(pie);

            }

            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }

        });

    }
}
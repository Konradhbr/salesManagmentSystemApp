package com.example.system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity {

    String item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        ListView listView=(ListView)findViewById(R.id.listview);
        final ArrayList<String> arrayList=new ArrayList<>();

        arrayList.add("android");
        arrayList.add("is");
        arrayList.add("great");
        arrayList.add("and I love it");
        arrayList.add("It");
        arrayList.add("is");
        arrayList.add("better");
        arrayList.add("then");
        arrayList.add("many");
        arrayList.add("other");
        arrayList.add("operating system.");
        arrayList.add("android");
        arrayList.add("is");
        arrayList.add("great");
        arrayList.add("and I love it");
        arrayList.add("It");
        arrayList.add("is");
        arrayList.add("better");
        arrayList.add("then");
        arrayList.add("many");
        arrayList.add("other");
        arrayList.add("operating system.");

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ProductList.this,"clicked item:"+i+" "+arrayList.get(i).toString(),Toast.LENGTH_SHORT).show();
                item = arrayList.get(i).toString();
                Intent intent = new Intent(ProductList.this , Worker.class);
                intent.putExtra("valueFromList" , item);
                startActivity(intent);
                finish();
            }
        });

    }
}
package com.example.system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static java.lang.Integer.parseInt;


public class Worker extends AppCompatActivity {

    Button addProductBTN , sendMessageBTN , saveProduct;
    TextView showProduct , currentTime;
    EditText amount, messageField;
    int productAmount;
    String message;
    DatabaseReference reffSoldProduct, reffMessage;
    SoldProduct soldProduct = new SoldProduct();
    SendMessage sendMessage = new SendMessage();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);

        addProductBTN = findViewById(R.id.addProduct);
        showProduct = findViewById(R.id.showProduct);
        currentTime = findViewById(R.id.time);
        amount = findViewById(R.id.amount);
        sendMessageBTN = findViewById(R.id.messageBTN);
        saveProduct = findViewById(R.id.send);
        messageField = findViewById(R.id.message);
        reffSoldProduct = FirebaseDatabase.getInstance().getReference().child("Sold Product");
        reffMessage = FirebaseDatabase.getInstance().getReference().child("Messages");

        String date_n = new SimpleDateFormat("HH:mm - MMM dd, yyyy", Locale.getDefault()).format(new Date());
        currentTime.setText(date_n);

        addProductBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(com.example.system.Worker.this, ProductList.class);
                startActivity(i);
            }
        });

        String product = getIntent().getStringExtra("valueFromList");
        showProduct.setText(product);

        /*if(!amount.getText().toString().matches("")) {
            productAmount = Integer.parseInt(amount.getText().toString());
            Toast.makeText(Worker.this , productAmount , Toast.LENGTH_LONG).show();
        }*/


        saveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productAmount = Integer.parseInt(amount.getText().toString().trim());
                soldProduct.setProductName(showProduct.getText().toString().trim());
                soldProduct.setProductAmount(productAmount);
                soldProduct.setDate(currentTime.getText().toString().trim());
                reffSoldProduct.child(soldProduct.getProductName()).setValue(soldProduct);
                Toast.makeText(Worker.this , "Dodano produkt" , Toast.LENGTH_LONG).show();
            }
        });

        sendMessageBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = messageField.getText().toString().trim();
                sendMessage.setMessage(message);
                reffMessage.child("Message").setValue(sendMessage);
                Toast.makeText(Worker.this , "Wysłano wiadomość" , Toast.LENGTH_LONG).show();
            }
        });

    }

}
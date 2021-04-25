package com.example.railfencecipher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnAdditive,btnRailfence;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdditive = findViewById(R.id.additive);
        btnRailfence = findViewById(R.id.railfence);
        btnAdditive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityOne();
            }
        });

        btnRailfence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityTwo();
            }
        });




    }
    public void openActivityOne(){
        Intent intentCal = new Intent(this, Additive.class);
        startActivity(intentCal);

    }
    public void openActivityTwo(){
        Intent intentCal = new Intent(this, Railfence.class);
        startActivity(intentCal);

    }


}
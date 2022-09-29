package com.isi.ex1day3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonActivity1;
    Button buttonActivity2;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonActivity1 = findViewById(R.id.activityFirstButton);
        buttonActivity2 = findViewById(R.id.activitySecondButton);

        buttonActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent  = new Intent(MainActivity.this, ScrollLayoutActivity.class);
                startActivity(intent);
            }
        });

        buttonActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent  = new Intent(MainActivity.this, ImageActivity.class);
                startActivity(intent);
            }
        });

    }
}
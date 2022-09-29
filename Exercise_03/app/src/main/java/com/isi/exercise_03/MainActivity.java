package com.isi.exercise_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    TextView textView;
    String textToShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textToShow);


        Bundle getData = getIntent().getExtras();


        if (getData != null) {
            textToShow = getData.getString("userInput");
        } else {
            textToShow = "Welcome";
        }
        textView.setText(textToShow);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.activity_a_item:
                intent = new Intent(this, ActivityOne.class);
                break;
            case R.id.activity_b_item:
                intent = new Intent(this, ActivityTwo.class);
                break;
            case R.id.activity_c_item:
                intent = new Intent(this, ActivityThree.class);
                break;
            default:
                Toast.makeText(this, "Select a valid option", Toast.LENGTH_SHORT).show();

        }
        startActivity(intent);
        return true;
    }
}
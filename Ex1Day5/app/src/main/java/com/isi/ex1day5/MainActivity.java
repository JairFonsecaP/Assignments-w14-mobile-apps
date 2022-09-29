package com.isi.ex1day5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnPlus;
    private Button btnMinus;
    private Button btnSubmit;
    private EditText numberEditText;
    private int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value = 0;
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        numberEditText =  (EditText) findViewById(R.id.numberEdit);
        numberEditText.setText(value + "");

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setValidValue();
                value--;
                numberEditText.setText(value + "");
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setValidValue();
                value++;
                numberEditText.setText(value + "");
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setValidValue();
                String message = "The current value is: " + getValue();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private int getValue(){
        return value;
    }

    private void setValue(int value){
        this.value = value;
    }

    private void setValidValue(){
        String valueStr = numberEditText.getText().toString();
        try{
            setValue(Integer.parseInt(valueStr));
        } catch (Exception e){
            setValue(0);
        }finally {
            numberEditText.setText(value + "");
        }
    }
}
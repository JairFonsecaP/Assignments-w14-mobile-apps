package com.isi.exercise_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityThree extends AppCompatActivity {

    Button submit;
    EditText editText;
    String userInput;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        submit = findViewById(R.id.buttonSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText = findViewById(R.id.userInputText);
                userInput = editText.getText().toString();
                Bundle bundle = new Bundle();

                bundle.putString("userInput", userInput);

                intent = new Intent(ActivityThree.this, MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
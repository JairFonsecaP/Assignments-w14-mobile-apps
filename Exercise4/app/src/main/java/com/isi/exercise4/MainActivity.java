package com.isi.exercise4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button submit;
    private EditText userText;
    private AlertDialog.Builder alert;
    private TextView textToShow;
    private RadioGroup optionsGroup;
    private RadioButton option;
    private int textColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = (Button) findViewById(R.id.submit);
        userText = (EditText) findViewById(R.id.userText);
        textToShow = (TextView) findViewById(R.id.textToShow);
        textColor = Color.BLACK;




        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {




                textToShow.setText(userText.getText().toString());
                alert = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                alert.setView(inflater.inflate(R.layout.my_alert,null));

                alert.setCancelable(true)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //TODO fix
                                optionsGroup = ((AlertDialog)dialogInterface).findViewById(R.id.colors); //null
                                int selectedOption = optionsGroup.getCheckedRadioButtonId();
                                option =(RadioButton) ((AlertDialog)dialogInterface).findViewById(selectedOption);

                                switch (option.getId()) {
                                    case R.id.redOption:
                                        textColor = Color.RED;
                                        break;
                                    case R.id.blueOption:
                                        textColor = Color.BLUE;
                                        break;
                                    case R.id.greenOption:
                                        textColor = Color.GREEN;
                                        break;
                                    case R.id.grayOption:
                                        textColor = Color.GRAY;
                                        break;
                                    case R.id.yellowOption:
                                        textColor = Color.YELLOW;
                                        break;
                                    case R.id.blackOption:
                                    default:
                                        textColor = Color.BLACK;
                                }

                                textToShow.setTextColor(textColor);
                                dialogInterface.cancel();
                            }
                        });

                AlertDialog dialog = alert.create();
                dialog.setTitle("Choose a color");



                dialog.show();

            }
        });
    }
}
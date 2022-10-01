package com.isi.ex1day10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.isi.ex1day10.controllers.StudentController;
import com.isi.ex1day10.entities.Student;

public class
FormActivity extends AppCompatActivity {

    EditText nameInput;
    EditText lastnameInput;
    EditText ageInput;
    Button btnContinue;
    Button btnCancel;
    Context context;
    Student student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Bundle getData = getIntent().getExtras();



        nameInput = (EditText) findViewById(R.id.firstNameInput);
        lastnameInput = (EditText) findViewById(R.id.lastNameInput);
        ageInput = (EditText) findViewById(R.id.ageInput);
        btnContinue = (Button) findViewById(R.id.continueButton);
        btnCancel = (Button) findViewById(R.id.cancelButton);
        context = this;

        if (getData != null){
            student = (Student) getData.getSerializable("student");
            nameInput.setText(student.getName());
            lastnameInput.setText(student.getLastname());
            ageInput.setText(student.getAgeStr());

            //todo
        }

        btnContinue.setOnClickListener(view -> {
            int age;
            String message = null;
            try {
                String name = Student.validateString(nameInput.getText().toString(), "name");
                String lastname = Student.validateString(lastnameInput.getText().toString(), "lastname");
                age = Student.validateAge(validateAgeInput(ageInput.getText().toString()));

                if (student != null) {
                    if(StudentController.editStudent(context, student, name, lastname, age)) message = student.getName() + " " + student.getLastname() + " was updated";
                    else message = "Something went error, try again";

                } else {
                    student = StudentController.addStudent(context, name, lastname, age);
                    message = student.getName() + " " + student.getLastname() + " was stored!";
                }

                finish();
            } catch (Exception e){
                message = e.getMessage();
            } finally {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }

        });

        btnCancel.setOnClickListener(v -> finish());
    }

    private int validateAgeInput(String age) throws Exception {
        int toReturn;
        try{
            toReturn = Integer.parseInt(age);
        } catch (Exception e){
            throw new Exception("Please enter an age");
        }
        return toReturn;
    }
}
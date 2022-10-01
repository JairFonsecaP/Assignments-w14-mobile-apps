package com.isi.ex1day10;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.isi.ex1day10.adapter.StudentAdapter;
import com.isi.ex1day10.controllers.StudentController;
import com.isi.ex1day10.entities.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Button btnAdd;
    private Context context;
    private ListView listView;
    private ArrayList<Student> students;
    private AlertDialog.Builder alert;
    private int list_students;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAdd = (Button) findViewById(R.id.btnAdd);
        listView = (ListView) findViewById(R.id.listViewStudents);
        context  = this;
        students = StudentController.getStudents(context);
        list_students = R.layout.list_students;

        refreshList();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = (Student) parent.getItemAtPosition(position);
                alert = new AlertDialog.Builder(MainActivity.this);
                alert.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String message = student.getName() + " " + student.getLastname();
                                if (StudentController.deleteStudent(context, student)){
                                    message += " deleted!";
                                } else {
                                    message += " cannot be deleted!";
                                }
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                                refreshList();
                                }
                            })
                        .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, FormActivity.class);


                                Bundle bundle = new Bundle();
                                bundle.putSerializable("student", student);

                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });

                AlertDialog dialog = alert.create();
                dialog.setTitle("Choose an action");
                dialog.show();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        refreshList();
    }

    private void refreshList(){
        students = StudentController.getStudents(context);
        studentAdapter = new StudentAdapter(context, list_students, students);
        listView.setAdapter(studentAdapter);
    }
}
package com.isi.ex1day10.controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.isi.ex1day10.entities.Student;
import com.isi.ex1day10.services.ConnectionDB;

import java.util.ArrayList;

public class StudentController {
    public static ArrayList<Student> getStudents(Context context){
        ArrayList<Student> students = null;
        SQLiteDatabase db = ConnectionDB.getDB(context);

        Cursor cursor = db.rawQuery("SELECT * FROM students", null);

        if (cursor.isBeforeFirst()) students = new ArrayList<>();

        while (cursor.moveToNext()){
            Student student = new Student(cursor.getInt(0), cursor.getString(1),cursor.getString(2),cursor.getInt(3));
            students.add(student);
        }

        cursor.close();
        ConnectionDB.closeDB();

        return students;
    }

    @NonNull
    public static Student addStudent(Context context, String name, String lastname, int age) throws Exception {
            SQLiteDatabase db = ConnectionDB.getDB(context);
            Student student = new Student(name, lastname, age);
            long id = student.add(db);
            db.close();
            student.setId(id);
            return student;
    }

    public static boolean editStudent(Context context, @NonNull Student student, String name, String lastname, int age) throws Exception {
        student.setAge(age);
        student.setLastname(lastname);
        student.setName(name);
        SQLiteDatabase db = ConnectionDB.getDB(context);
        boolean toReturn = student.edit(db);
        db.close();
        return toReturn;
    }

    public static boolean deleteStudent(Context context, @NonNull Student student) {
        SQLiteDatabase db = ConnectionDB.getDB(context);
        boolean toReturn = student.delete(db);
        db.close();
        return toReturn;
    }
}

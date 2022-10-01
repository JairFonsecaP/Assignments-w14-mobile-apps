package com.isi.ex1day10.entities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;

import java.io.Serializable;

public class Student implements Serializable, Parcelable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String name;
    private String lastname;
    private int age;
    private ContentValues values;

    public Student(long id, String name, String lastname, int age) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public Student(String name, String lastname, int age) throws Exception {
        this.name = validateString(name, "name");
        this.lastname = validateString(lastname, "lastname");
        this.age = validateAge(age);
    }

    protected Student(@NonNull Parcel in) {
        id = in.readLong();
        name = in.readString();
        lastname = in.readString();
        age = in.readInt();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @NonNull
        @Contract("_ -> new")
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @NonNull
        @Contract(value = "_ -> new", pure = true)
        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) throws Exception{
         if (id == -1 ) throw new Exception("Id not valid");
         else this.id = id;
    }

    public String getIdStr(){
        return Long.toString(id);
     }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        this.name = validateString(name, "lastname");
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) throws Exception {
        this.lastname = validateString(lastname, "lastname");
    }

    public int getAge() {
        return age;
    }

    public String getAgeStr(){
        return Integer.toString(age);
    }

    public void setAge(int age) throws Exception {
        this.age = validateAge(age);
    }

    public long add(@NonNull SQLiteDatabase db) {
        values = new ContentValues();
        values.put("name", name);
        values.put("lastname", lastname);
        values.put("age", age);
        return  db.insertOrThrow("students", null, values);
    }

    public boolean delete(@NonNull SQLiteDatabase db){
        values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("lastname", lastname);
        values.put("age", age);
        return db.delete("students", "id=?" , new String[]{getIdStr()}) > 0;
    }

    public boolean edit(@NonNull SQLiteDatabase db){
        values = new ContentValues();
        values.put("name", name);
        values.put("lastname", lastname);
        values.put("age", age);
        return db.update("students", values, "id = ?", new String[]{getIdStr()}) > 0;
    }

    @NonNull
    public static String validateString(@NonNull String name, String field) throws Exception{
        if (name.isEmpty()) throw new Exception("You have to enter a valid " + field);
        return name;
    }

    public static int validateAge(int age) throws Exception{
        if (age < 1 || age > 120) throw new Exception("You have to enter a valid age");
        return age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(lastname);
        dest.writeInt(age);
    }
}

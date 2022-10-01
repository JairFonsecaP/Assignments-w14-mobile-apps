package com.isi.ex1day10.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.isi.ex1day10.R;
import com.isi.ex1day10.entities.Student;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    int idLayout;

    public StudentAdapter(Context context, int resource, List<Student> students){
        super(context,resource,students);
        idLayout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Student student = getItem(position);

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(idLayout, null);
        }

        TextView idTextView = convertView.findViewById(R.id.idShow);
        TextView nameTextView = convertView.findViewById(R.id.nameShow);
        TextView lastnameTextView = convertView.findViewById(R.id.lastnameShow);
        TextView ageTextView = convertView.findViewById(R.id.ageShow);

        idTextView.setText(student.getIdStr());
        nameTextView.setText(student.getName());
        lastnameTextView.setText(student.getLastname());
        ageTextView.setText(student.getAgeStr());

        return convertView;
    }
}

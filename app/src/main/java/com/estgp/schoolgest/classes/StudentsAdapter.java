package com.estgp.schoolgest.classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estgp.schoolgest.R;
import com.estgp.schoolgest.activities.StudentDetailsActivity;

import java.util.ArrayList;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentViewHolder> {

    public static final String EXTRA_SELECTED_STUDENT = "com.estgp.schoolgest.EXTRA_SELECTED_STUDENT";

    private ArrayList<Student> studentList;
    private Activity activity;
    private final LayoutInflater inflater;

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        public TextView studentNumber, name;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentNumber = itemView.findViewById(R.id.tv_item_student_number);
            name = itemView.findViewById(R.id.tv_item_name);
        }

    }

    public StudentsAdapter(Context context, ArrayList<Student> studentList, Activity activity) {

        this.studentList = studentList;
        this.activity = activity;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public StudentsAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.student_list_row, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsAdapter.StudentViewHolder holder, int position) {

        final Student student = studentList.get(position);
        holder.studentNumber.setText(student.getStudentNumber().toString());
        holder.name.setText(student.getName());


        //OnClick
        holder.itemView.setOnClickListener(v ->{
            Intent intent = new Intent(activity, StudentDetailsActivity.class);
            intent.putExtra(EXTRA_SELECTED_STUDENT, student);
            activity.startActivity(intent);

        });

        //OnCreateContextMenu
        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                contextMenu.setHeaderTitle(activity.getString(R.string.select_option));
                contextMenu.add(holder.getAdapterPosition(),1,1,activity.getString(R.string.edit));
                contextMenu.add(holder.getAdapterPosition(),2,2,activity.getString(R.string.remove));
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}

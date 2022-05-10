package com.estgp.schoolgest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.estgp.schoolgest.R;
import com.estgp.schoolgest.classes.Student;
import com.estgp.schoolgest.classes.StudentsAdapter;

import java.time.format.DateTimeFormatter;

public class StudentDetailsActivity extends AppCompatActivity {

    private TextView tvStudentDetails;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        Intent intent = getIntent();
        Student selectedStudent = (Student) intent.getSerializableExtra(StudentsAdapter.EXTRA_SELECTED_STUDENT);

        tvStudentDetails = findViewById(R.id.tv_details_student);

        //tvStudentDetails.setText(selectedStudent.toString());

        String studentText = getString(R.string.student_number) + " " +  (selectedStudent.getStudentNumber() == null ? getString(R.string.not_defined)  : selectedStudent.getStudentNumber()) + "\n";
        studentText += getString(R.string.name) + " " + (selectedStudent.getName() == null ? getString(R.string.not_defined)  : selectedStudent.getName()) + "\n";
        studentText += getString(R.string.birth_date) + " " +  (selectedStudent.getBirthDate() == null ? getString(R.string.not_defined)  : selectedStudent.getBirthDate().format(formatter)) + "\n";
        studentText += getString(R.string.address) + " " +  (selectedStudent.getAddress() == null ? getString(R.string.not_defined)  : selectedStudent.getAddress()) + "\n";
        studentText += getString(R.string.phone) + " " +  (selectedStudent.getPhone() == null ? getString(R.string.not_defined)  : selectedStudent.getPhone()) + "\n";
        studentText += getString(R.string.mobile_phone) + " " +  (selectedStudent.getMobilePhone() == null ? getString(R.string.not_defined)  : selectedStudent.getMobilePhone()) + "\n";
        studentText += getString(R.string.email) + " " +  (selectedStudent.getEmail() == null ? getString(R.string.not_defined)  : selectedStudent.getEmail()) + "\n";
        studentText += getString(R.string.school) + " " +  (selectedStudent.getSchool() == null ? getString(R.string.not_defined)  : selectedStudent.getSchool()) + "\n";

        tvStudentDetails.setText(studentText);
    }
}
package com.estgp.schoolgest.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.estgp.schoolgest.R;
import com.estgp.schoolgest.classes.School;
import com.estgp.schoolgest.classes.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class FormActivity extends AppCompatActivity {

    public static final String EXTRA_NEW_STUDENT = "com.estgp.schoolgest.activities.extra.EXTRA_NEW_STUDENT";
    private EditText etStudentNumber;
    private EditText etName;
    private EditText etBirthDate;
    private EditText etAddress;
    private EditText etPhone;
    private EditText etMobilePhone;
    private EditText etEmail;
    private Spinner spSchool;

    private Button btnInsert;

    private DatePickerDialog datePicker;
    private School selectedSchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etStudentNumber = findViewById(R.id.et_student_number);
        etName = findViewById(R.id.et_name);
        etBirthDate = findViewById(R.id.et_birth_date);
        etBirthDate.setInputType(InputType.TYPE_NULL);
        etAddress = findViewById(R.id.et_address);
        etPhone = findViewById(R.id.et_phone);
        etMobilePhone = findViewById(R.id.et_mobile_phone);
        etEmail = findViewById(R.id.et_email);
        spSchool = findViewById(R.id.sp_school);

        btnInsert = findViewById(R.id.btn_insert);

        initDatePicker();
        initSpinner();


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student newStudent = new Student();

                Log.d("mytag", "Date" + etBirthDate.getText().toString());

                if(etStudentNumber.getText().toString().isEmpty()){
                    Toast.makeText(v.getContext(), R.string.please_insert_number, Toast.LENGTH_LONG).show();
                    return;
                }

                if(etName.getText().toString().isEmpty()){
                    Toast.makeText(v.getContext(), R.string.please_insert_name, Toast.LENGTH_LONG).show();
                    return;
                }
                newStudent.setStudentNumber(etStudentNumber.getText().toString().isEmpty() ? null : Integer.parseInt(etStudentNumber.getText().toString()));
                newStudent.setName(etName.getText().toString().isEmpty() ? null : etName.getText().toString());
                newStudent.setBirthDate(etBirthDate.getText().toString().isEmpty() ? null : stringToLocalDate(etBirthDate.getText().toString()));
                newStudent.setAddress(etAddress.getText().toString().isEmpty() ? null : etAddress.getText().toString());
                newStudent.setPhone(etPhone.getText().toString().isEmpty() ? null : Integer.parseInt(etPhone.getText().toString()));
                newStudent.setMobilePhone(etMobilePhone.getText().toString().isEmpty() ? null : Integer.parseInt(etMobilePhone.getText().toString()));
                newStudent.setEmail(etEmail.getText().toString().isEmpty() ? null : etEmail.getText().toString());
                newStudent.setSchool(selectedSchool);

                Log.d("mytag", newStudent.toString());

                Intent replyIntent = new Intent();
                replyIntent.putExtra(EXTRA_NEW_STUDENT, newStudent);

                setResult(RESULT_OK, replyIntent);
                finish();

            }
        });

        //Validar se vem com Student no Intent
        isAddorEdit();

    }

    private void isAddorEdit() {
        Intent intent = getIntent();
        Student selectedSudent = (Student) intent.getSerializableExtra(MainActivity.EXTRA_SELECT_STUDENT);


        //FALTA VALIDAR COM IF PARA NAO REBENTAR O PROGRAMA
        if (selectedSudent != null) {
            etStudentNumber.setText(selectedSudent.getStudentNumber().toString());
            etName.setText(selectedSudent.getName());
            etAddress.setText(selectedSudent.getAddress());
            etBirthDate.setText(localdateToString(selectedSudent.getBirthDate()));
            etEmail.setText(selectedSudent.getEmail());
            etPhone.setText(selectedSudent.getPhone().toString());
            etMobilePhone.setText(selectedSudent.getMobilePhone().toString());


        }


    }

    private LocalDate stringToLocalDate(String date){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(date, formatter);
        }
        return null;
    }

    private String localdateToString(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (date != null) {
            return date.format(formatter);
        }
        return "";
    }


    private void initDatePicker(){
        etBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();

                if(!etBirthDate.getText().toString().isEmpty()){
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        calendar.setTime(sdf.parse(etBirthDate.getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePicker = new DatePickerDialog(FormActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String checkValidDay = dayOfMonth >= 10 ? String.valueOf(dayOfMonth) : "0" + dayOfMonth;
                        String checkValidMonth = month >= 10 ? String.valueOf(month+1) : "0" + (month+1);

                        etBirthDate.setText(checkValidDay + "/" + checkValidMonth + "/" + year);
                    }
                }, year, month, day);

                datePicker.show();
            }
        });

    }

    private void initSpinner(){
        ArrayAdapter<School> adapterSpinner = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, MainActivity.schoolList);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSchool.setAdapter(adapterSpinner);

        spSchool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSchool = (School) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
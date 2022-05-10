package com.estgp.schoolgest.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estgp.schoolgest.R;
import com.estgp.schoolgest.classes.School;
import com.estgp.schoolgest.classes.Student;
import com.estgp.schoolgest.classes.StudentsAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_SELECT_STUDENT = "com.estgp.schoolgest.extra.EXTRA_SELECT_STUDENT";
    public static Activity mainActivity;
    private ArrayList<Student> studentList;
    public static ArrayList<School> schoolList;
    private RecyclerView recyclerView;
    private StudentsAdapter adapter;
    private ActivityResultLauncher<Intent> formActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;

        studentList = new ArrayList<>();
        schoolList = new ArrayList<>();
        createSchools();

        recyclerView = findViewById(R.id.rv_students);
        adapter = new StudentsAdapter(  this, studentList,  mainActivity);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        formActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Log.d("myTag", "Activity Result OK");

                            int studentListSize = studentList.size();

                            Student newStudent = (Student) result.getData().getSerializableExtra(FormActivity.EXTRA_NEW_STUDENT);
                            studentList.add(newStudent);

                            recyclerView.getAdapter().notifyItemInserted(studentListSize);
                            recyclerView.smoothScrollToPosition(studentListSize);

                        } else if (result.getResultCode() == RESULT_CANCELED) {
                            Log.d("myTag", "Activity Result CANCELLED");
                        }
                    }
            });
    }

    //######################################## Menu ################################################
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(), FormActivity.class);
            formActivityResultLauncher.launch(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //######################################## ContextMenu ################################################


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                //EDIT STUDENT
                Student editStudent = studentList.get(item.getGroupId());
                Intent intent = new Intent(getApplicationContext(),FormActivity.class);
                intent.putExtra(EXTRA_SELECT_STUDENT,editStudent);
                formActivityResultLauncher.launch(intent);
                return true;
            case 2:
                //REMOVE STUDENT
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.delete_student);
                builder.setMessage(R.string.want_to_remove);

                //Botão de OK
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Remover o Student
                        studentList.remove(item.getGroupId());

                        //Considera somente as alterações para a posição a remover
                        recyclerView.getAdapter().notifyItemRemoved(item.getItemId());


                        //Recria todo o Recycler, considera alteração a toda a dataSet
                        //recyclerView.getAdapter().notifyDataSetChanged();
                    }
                });


                //Botão de Cancel
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    private void createSchools(){

        School school = new School("Escola Superior de Tecnologia e Gestão", "ESTG", "Portalegre");
        schoolList.add(school);

        school = new School("Escola Superior de Educação e Ciencias Sociais", "ESECS", "Portalegre");
        schoolList.add(school);

        school = new School("Escola Superior de Saúde", "ESS", "Portalegre");
        schoolList.add(school);

        school = new School("Escola Superior Agrária", "ESAE", "Elvas");
        schoolList.add(school);
    }

}
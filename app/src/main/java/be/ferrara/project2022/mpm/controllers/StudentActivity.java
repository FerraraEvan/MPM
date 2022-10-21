package be.ferrara.project2022.mpm.controllers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import be.ferrara.project2022.mpm.R;
import be.ferrara.project2022.mpm.models.Project;
import be.ferrara.project2022.mpm.models.Student;
import be.ferrara.project2022.mpm.models.StudentList;

public class StudentActivity extends AppCompatActivity implements StudentFragment.Listener {
    private static final int REQUEST_CODE = 10;
    private static final String PROJECT_NAME = "PROJECT_NAME";
    private FloatingActionButton mAddStudentButton;
    private Button mSendStudentButton;
    private TextView mStudentListTextView;
    private LinearLayout mProjectVerticalLayout;
    private String mUserList = "";
    private EditText mAddUserEditText;
    private StudentFragment studentFragment;
    private Intent intent;
    //private Student mStudent;
   // private List<Student> studentList = new ArrayList<>();
    private StudentList studentList = new StudentList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        initializeComponent();
        openDialog();
    }

    private void addFragment(Student student) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        studentFragment = (StudentFragment) fragmentManager.findFragmentById(R.id.student_vertical_layout);
        studentFragment = new StudentFragment();
        studentFragment.setStudent(student);
        studentFragment.setListener(this);
        fragmentManager.beginTransaction().add(R.id.student_vertical_layout, studentFragment).commit();
    }

    private void initializeComponent() {
        mAddStudentButton = findViewById(R.id.add_student_button);
        mProjectVerticalLayout = findViewById(R.id.student_vertical_layout);
    }

    private void openDialog() {
        mAddStudentButton.setOnClickListener(view -> {
            final AlertDialog.Builder alert = new AlertDialog.Builder(StudentActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.dialog_student, null);
            mAddUserEditText = (EditText) mView.findViewById(R.id.user_edit_text);
            alert.setView(mView);
            AlertDialog alertDialog = alert.create();
            alertDialog.show();
            setStudentName();
            sendStudent(mView, alertDialog);

        });
    }

    private void sendStudent(View mView, AlertDialog alertDialog) {
        mSendStudentButton = mView.findViewById(R.id.send_student_button);
        mSendStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                String array[] = mUserList.trim().split("\n");
                for (int i = 0; i < array.length; i++) {
                    if(!array[i].trim().equals("")){
                        Student student = new Student(array[i]);
                        studentList.addStudent(student);
                        addFragment(student);
                    }
                }
            }
        });
    }


    private void setStudentName() {
        mAddUserEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mUserList = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void updateUI(){
        mProjectVerticalLayout.removeAllViews();
        for (Student student : studentList.getStudentList()) {
            addFragment(student);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         = (Student) data.getSerializableExtra(PROJECT_NAME);
        //Log.d("student","activity result"+mStudent.getProjectList().size());
        updateUI();
    }

    @Override
    public void sendStudentToProjectActivity(Student student) {
        studentList.updateStudent(student);
        intent = new Intent(StudentActivity.this, ProjectActivity.class);
        intent.putExtra("PROJECT_NAME",studentList);
        startActivityForResult(intent,REQUEST_CODE);
    }
}
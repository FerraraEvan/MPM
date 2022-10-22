package be.ferrara.project2022.mpm.controllers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import be.ferrara.project2022.mpm.R;
import be.ferrara.project2022.mpm.models.Project;
import be.ferrara.project2022.mpm.models.Student;
import be.ferrara.project2022.mpm.models.StudentList;

public class ProjectActivity extends AppCompatActivity implements ProjectFragment.Listener {
    private static final int REQUEST_CODE = 10;
    private static final String PROJECT_NAME = "PROJECT_NAME";
    private Student mStudent;
    private Project project;
    private TextView mNameTextView;
    private FloatingActionButton mAddButton;
    private LinearLayout mContainer;
    private Intent intent;
    private ProjectFragment projectFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        initializeComponent();
        mStudent = (Student) getIntent().getSerializableExtra(PROJECT_NAME);
        updateUI();
        setText();
        onClickButton();
    }

    private void setText() {
        mStudent.getName();
        mNameTextView.setText("Project de "+ mStudent.getName());
    }

    private void onClickButton() {
        mAddButton.setOnClickListener(view -> {
            Project project = new Project();
            mStudent.addProject(project);
            addFragment(project);
        });
    }

    private void addFragment(Project project){
        FragmentManager fragmentManager = getSupportFragmentManager();
        projectFragment = (ProjectFragment) fragmentManager.findFragmentById(R.id.container_project);
        projectFragment = new ProjectFragment();
        projectFragment.setProject(project);
        projectFragment.setStudent(mStudent);
        projectFragment.setListener(this);
        fragmentManager.beginTransaction().add(R.id.container_project,projectFragment).commit();
    }
    private void initializeComponent() {
        mNameTextView=findViewById(R.id.name_project_text_view);
        mAddButton=findViewById(R.id.add_project_button);
        mContainer = findViewById(R.id.container_project);
    }

    void updateUI(){
        mContainer.removeAllViews();
        for (Project project : mStudent.getProjectList()) {
            addFragment(project);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        project = (Project) data.getSerializableExtra(PROJECT_NAME);
        mStudent.updateProject(project);
        updateUI();
    }


    @Override
    public void sendProjectToStepActivity(Project project) {
        intent = new Intent(ProjectActivity.this, StepActivity.class);
        intent.putExtra("PROJECT_NAME",project);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(PROJECT_NAME, mStudent);
        this.setResult(REQUEST_CODE, intent);
        super.onBackPressed();
    }
}
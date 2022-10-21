package be.ferrara.project2022.mpm.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import be.ferrara.project2022.mpm.R;
import be.ferrara.project2022.mpm.models.Project;
import be.ferrara.project2022.mpm.models.Student;

public class ProjectFragment extends Fragment {
    TextView  projectName,projectPoint;
    Project project;
    ConstraintLayout mContainer;
    Listener listener;
    Student mStudent;

    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    public void setStudent(Student student){this.mStudent = student;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project,container,false);
        initializeComponent(view);
        returnProject();
        projectName.setText(project.getName());
        projectPoint.setText(project.getTotalPoint()+"");
        return view;
    }

    private void returnProject() {
        mContainer.setOnClickListener(view -> listener.sendProjectToStepActivity(project));
    }

    private void initializeComponent(View view) {
        projectName = view.findViewById(R.id.fragment_project_name);
        projectPoint = view.findViewById(R.id.fragment_project_point);
        mContainer = view.findViewById(R.id.fragment_project_container);
    }
    interface Listener{
       void sendProjectToStepActivity(Project project);
    }
}

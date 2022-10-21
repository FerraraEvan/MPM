package be.ferrara.project2022.mpm.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import be.ferrara.project2022.mpm.R;
import be.ferrara.project2022.mpm.models.Student;

public class StudentFragment extends Fragment {
private TextView mNameStudentTextView;
private Student mStudent;
private Listener listener;
    public void setStudent(Student student) {this.mStudent = student;}

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_student,container,false);
        mNameStudentTextView = v.findViewById(R.id.student_name_text_view);
        mNameStudentTextView.setPadding(0,40,0,0);
        mNameStudentTextView.setText(mStudent.getName());
        mNameStudentTextView.setOnClickListener(view -> listener.sendStudentToProjectActivity(mStudent));
        return v;
    }

    interface Listener{
        void sendStudentToProjectActivity(Student student);
    }
}

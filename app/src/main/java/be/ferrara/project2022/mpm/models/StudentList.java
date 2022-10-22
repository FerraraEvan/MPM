package be.ferrara.project2022.mpm.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentList implements Serializable {
    private List<Student> studentList=new ArrayList<>();

    public void addStudent(Student student){
        studentList.add(student);
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void updateStudent(Student student) {
        Student studentToDelete = null;
        for (Student student1:studentList) {
            if(student.getId().equals(student1.getId())){
                studentToDelete = student1;
            }
        }
        if(studentToDelete!= null){
        studentList.remove(studentToDelete);
        studentList.add(student);
        }
    }
}

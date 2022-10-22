package be.ferrara.project2022.mpm.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Student implements Serializable {
    private String mName;
    private List<Project> mProjectList = new ArrayList<>();
    private java.util.UUID mId;
    private java.util.UUID mStudentId;
    public Student(String name) {
        this.mName = name;
        this.mId= UUID.randomUUID();
    }
    public Student(UUID id){
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void addProject(Project project){
        mProjectList.add(project);
    }

    public void updateProject(Project project){
        Project projectToDelete = new Project();
        for (Project project1:mProjectList) {
            if(project.getId().equals(project1.getId())){
                projectToDelete = project1;
            }
        }
        mProjectList.remove(projectToDelete);
        mProjectList.add(project);
    }

    public List<Project> getProjectList() {
        return mProjectList;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public UUID getId() {
        return mId;
    }
}

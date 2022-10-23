package be.ferrara.project2022.mpm.models;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import be.ferrara.project2022.mpm.db.MPMDbSchema;

public class Student implements Serializable {
    private String mName;
    private List<Project> mProjectList = new ArrayList<>();
    private java.util.UUID mId;
    private java.util.UUID mStudentId;
    private SQLiteDatabase mDatabase;

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
        /**mDatabase.insert(MPMDbSchema.ProjectTable.NAME,null,
                getContentValues(project));*/
        mProjectList.add(project);
    }

    private ContentValues getContentValues(Project project){
        ContentValues values = new ContentValues();
        values.put(MPMDbSchema.ProjectTable.cols.UUID,
                project.getId().toString());
        values.put(MPMDbSchema.ProjectTable.cols.NAME,
                project.getName());
        values.put(MPMDbSchema.ProjectTable.cols.DESCRIPTION,
                project.getDescription());
        values.put(MPMDbSchema.ProjectTable.cols.STUDENTUUID,
                project.getStudentId().toString());


        return values;
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

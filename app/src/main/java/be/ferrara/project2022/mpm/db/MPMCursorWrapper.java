package be.ferrara.project2022.mpm.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import be.ferrara.project2022.mpm.models.Project;
import be.ferrara.project2022.mpm.models.Step;
import be.ferrara.project2022.mpm.models.Student;

public class MPMCursorWrapper extends CursorWrapper {

    public MPMCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Student getStudent(){
        String name =
                getString(getColumnIndex(MPMDbSchema.StudentTable.cols.NAME));
        String uuidString =
                getString(getColumnIndex(MPMDbSchema.StudentTable.cols.UUID));

        Student student = new Student(UUID.fromString(uuidString));
        student.setName(name);

        return student;

    }
    public Project getProject(){
        String name =
                getString(getColumnIndex(MPMDbSchema.ProjectTable.cols.NAME));
        String description =
                getString(getColumnIndex(MPMDbSchema.ProjectTable.cols.DESCRIPTION));
        String uuidString =
                getString(getColumnIndex(MPMDbSchema.ProjectTable.cols.UUID));
        String studentUUID =
                getString(getColumnIndex(MPMDbSchema.ProjectTable.cols.STUDENTUUID));

        Project project = new Project(UUID.fromString(uuidString));
        project.setDescription(description);
        project.setStudentId(UUID.fromString(studentUUID));
        project.setName(name);

        return project;
    }
    public Step getStep(){
        String uuidString =
                getString(getColumnIndex(MPMDbSchema.StepTable.cols.PROJECTUUID));
        String name =
                getString(getColumnIndex(MPMDbSchema.StepTable.cols.NAME));
        String points =
                getString(getColumnIndex(MPMDbSchema.StepTable.cols.POINTS));

        Step step = new Step(UUID.fromString(uuidString));
        step.setName(name);
        step.setPoints(Integer.parseInt(points));

        return step;
    }
}

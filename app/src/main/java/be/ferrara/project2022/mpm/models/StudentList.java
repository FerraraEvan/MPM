package be.ferrara.project2022.mpm.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import be.ferrara.project2022.mpm.db.MPMBaseHelper;
import be.ferrara.project2022.mpm.db.MPMCursorWrapper;
import be.ferrara.project2022.mpm.db.MPMDbSchema;

public class StudentList implements Serializable {
    private List<Student> studentList=new ArrayList<>();
    private SQLiteDatabase mDatabase;
    private Context mContext;
    public StudentList(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new MPMBaseHelper(mContext).getWritableDatabase();
    }
    public StudentList(){}
    public void addStudent(Student student){
        //mDatabase.insert(MPMDbSchema.StudentTable.NAME, null,
         //       getContentValues(student));
        studentList.add(student);
    }

    private ContentValues getContentValues(Student student){
        ContentValues values = new ContentValues();
        values.put(MPMDbSchema.StudentTable.cols.UUID,
                student.getId().toString());
        values.put(MPMDbSchema.StudentTable.cols.NAME,
                student.getName());

        return values;
    }
    public List<Student> getStudentList() {
        return studentList;
        /*ArrayList<Student> students = new ArrayList<>();

        MPMCursorWrapper cursor = queryStudents(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                students.add(cursor.getStudent());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return students;*/
    }

    private MPMCursorWrapper queryStudents(String whereClause, String[] whereArgs){
        return new MPMCursorWrapper(mDatabase.query(
                MPMDbSchema.StudentTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,null,null));
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

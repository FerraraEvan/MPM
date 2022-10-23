package be.ferrara.project2022.mpm.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import be.ferrara.project2022.mpm.db.MPMDbSchema.ProjectTable;
import be.ferrara.project2022.mpm.db.MPMDbSchema.StudentTable;
import be.ferrara.project2022.mpm.db.MPMDbSchema.StepTable;

public class MPMBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "mpmBase.db";

    public MPMBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+StudentTable.NAME+"("
                + StudentTable.cols.NAME + ","+StudentTable.cols.UUID+")"
        );
        db.execSQL("CREATE TABLE "+ProjectTable.NAME+"("
                + ProjectTable.cols.NAME + ","
                + ProjectTable.cols.UUID + ","
                + ProjectTable.cols.STUDENTUUID + ","
                + "FOREIGN KEY ("+ProjectTable.cols.STUDENTUUID+") REFERENCES "+ StudentTable.cols.UUID
        );
        db.execSQL("CREATE TABLE "+StepTable.NAME+"("
                + StepTable.cols.NAME + ","
                + StepTable.cols.POINTS + ","
                + StepTable.cols.PROJECTUUID + ","
                + "FOREIGN KEY ("+StepTable.cols.PROJECTUUID+") REFERENCES "+ ProjectTable.cols.UUID);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
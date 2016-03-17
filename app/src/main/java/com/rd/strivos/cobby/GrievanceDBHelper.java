package com.rd.strivos.cobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rd.strivos.cobby.GrievanceDBFields.GrievanceAdd;

import java.sql.SQLException;

/**
 * Created by COBURG DESIGN on 23-01-2016.
 */
public class GrievanceDBHelper extends SQLiteOpenHelper {

    public GrievanceDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "grievancelive.db";

    private static final String SQL_CREATE_GRIEVANCE = "CREATE TABLE "
            + GrievanceAdd.TABLE_NAME + " (" + GrievanceAdd._ID //0
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + GrievanceAdd.COLUMN_NAME_GRIEVANCE + " TEXT," //1
            + GrievanceAdd.COLUMN_NAME_SOLUTION + " TEXT," //2
            + GrievanceAdd.COLUMN_NAME_PROJECT_TYPE + " TEXT," //3
            + GrievanceAdd.COLUMN_NAME_PROJECT_NAME + " TEXT," //4
            + GrievanceAdd.COLUMN_NAME_STATUS + " TEXT" + " )"; //5

    private static final String SQL_DELETE_GRIEVANCE = "DROP TABLE IF EXISTS "
            + GrievanceAdd.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_GRIEVANCE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_GRIEVANCE);
        onCreate(db);
    }

    public long insertGrievance(String val1, String val2, String val3, String val4, String val5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(GrievanceAdd.COLUMN_NAME_GRIEVANCE, val1);
        initialValues.put(GrievanceAdd.COLUMN_NAME_SOLUTION, val2);
        initialValues.put(GrievanceAdd.COLUMN_NAME_PROJECT_TYPE, val3);
        initialValues.put(GrievanceAdd.COLUMN_NAME_PROJECT_NAME, val4);
        initialValues.put(GrievanceAdd.COLUMN_NAME_STATUS, val5);

        long l = db.insert(GrievanceAdd.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public void updateGrievance(long val1, String val2, String val3, String val4, String val5) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GrievanceAdd.COLUMN_NAME_GRIEVANCE, val2);
        values.put(GrievanceAdd.COLUMN_NAME_SOLUTION, val3);
        values.put(GrievanceAdd.COLUMN_NAME_PROJECT_TYPE, val4);
        values.put(GrievanceAdd.COLUMN_NAME_PROJECT_NAME, val5);

        sqdb.update(GrievanceAdd.TABLE_NAME, values, "_id=" + val1, null);
    }

    public int deleteGrievance(long id) {
        return getWritableDatabase().delete(GrievanceAdd.TABLE_NAME, GrievanceAdd._ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Cursor getGrievanceList(String ProjectName, String ProjectType) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + GrievanceAdd.TABLE_NAME + " WHERE " + GrievanceAdd.COLUMN_NAME_PROJECT_NAME + "='" + ProjectName + "' and " + GrievanceAdd.COLUMN_NAME_PROJECT_TYPE + " = '" + ProjectType + "' and " + GrievanceAdd.COLUMN_NAME_STATUS + " = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getGrievanceListFull(int id) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + GrievanceAdd.TABLE_NAME + " WHERE " + GrievanceAdd._ID + "=" + id + "";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor GrievanceForSync(String companyName, String Type) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + GrievanceAdd._ID + " , " + GrievanceAdd.COLUMN_NAME_GRIEVANCE + "," + GrievanceAdd.COLUMN_NAME_SOLUTION + " from " + GrievanceAdd.TABLE_NAME + " where " + GrievanceAdd.COLUMN_NAME_PROJECT_NAME + " = '" + companyName + "' and " + GrievanceAdd.COLUMN_NAME_PROJECT_TYPE + " = '" + Type + "' and " + GrievanceAdd.COLUMN_NAME_STATUS + " = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void GrievanceStatusUpdate(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GrievanceAdd.COLUMN_NAME_STATUS, "Y");
        System.out.print(val1);

        sqdb.update(GrievanceAdd.TABLE_NAME, values, "_id=" + val1, null);
    }
}

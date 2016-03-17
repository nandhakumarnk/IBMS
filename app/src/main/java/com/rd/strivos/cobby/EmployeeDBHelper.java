package com.rd.strivos.cobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rd.strivos.cobby.EmployeeDBFields.EmployeeDetails;

/**
 * Created by COBURG DESIGN on 03-02-2016.
 */
public class EmployeeDBHelper extends SQLiteOpenHelper {

    public EmployeeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "employeedblive.db";

    private static final String SQL_CREATE_EMPLOYEE = "CREATE TABLE "
            + EmployeeDetails.TABLE_NAME + " (" + EmployeeDetails._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EmployeeDetails.COLUMN_NAME_EMPLOYEE_NAME + " TEXT,"
            + EmployeeDetails.COLUMN_NAME_EMPLOYEE_MOBILE + " TEXT,"
            + EmployeeDetails.COLUMN_NAME_EMPLOYEE_EMPLOYEEID + " TEXT,"
            + EmployeeDetails.COLUMN_NAME_EMPLOYEE_DESIGNATION + " TEXT,"
            + EmployeeDetails.COLUMN_NAME_EMPLOYEE_ID + " TEXT,"
            + EmployeeDetails.COLUMN_NAME_EMPLOYEE_DEPARTMENT + " TEXT,"
            + EmployeeDetails.COLUMN_NAME_EMPLOYEE_DEPARTMENT_ID + " TEXT,"
            + EmployeeDetails.COLUMN_NAME_EMPLOYEE_DESIGNATION_ID + " TEXT,"
            + EmployeeDetails.COLUMN_NAME_EMPLOYEE_DESIGNATION_CATEGORY_ID + " TEXT" + " )";


    private static final String SQL_DELETE_EMPLOYEE = "DROP TABLE IF EXISTS "
            + EmployeeDetails.TABLE_NAME;


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_EMPLOYEE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_EMPLOYEE);
        onCreate(db);
    }

    /**
     * INSERT EMPLOYEE DETAILS
     */

    public long insertEmployeeDetails(String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(EmployeeDetails.COLUMN_NAME_EMPLOYEE_NAME, val1);
        initialValues.put(EmployeeDetails.COLUMN_NAME_EMPLOYEE_MOBILE, val2);
        initialValues.put(EmployeeDetails.COLUMN_NAME_EMPLOYEE_EMPLOYEEID, val3);
        initialValues.put(EmployeeDetails.COLUMN_NAME_EMPLOYEE_DESIGNATION, val4);
        initialValues.put(EmployeeDetails.COLUMN_NAME_EMPLOYEE_ID, val5);
        initialValues.put(EmployeeDetails.COLUMN_NAME_EMPLOYEE_DEPARTMENT, val6);
        initialValues.put(EmployeeDetails.COLUMN_NAME_EMPLOYEE_DEPARTMENT_ID, val7);
        initialValues.put(EmployeeDetails.COLUMN_NAME_EMPLOYEE_DESIGNATION_ID, val8);
        initialValues.put(EmployeeDetails.COLUMN_NAME_EMPLOYEE_DESIGNATION_CATEGORY_ID, val9);

        long l = db.insert(EmployeeDetails.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectEmployee() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + EmployeeDetails.COLUMN_NAME_EMPLOYEE_ID + "," + EmployeeDetails.COLUMN_NAME_EMPLOYEE_NAME + "," + EmployeeDetails.COLUMN_NAME_EMPLOYEE_DESIGNATION + " from " + EmployeeDetails.TABLE_NAME + " order by " + EmployeeDetails.COLUMN_NAME_EMPLOYEE_NAME + ";";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectEmployeeService() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + EmployeeDetails.COLUMN_NAME_EMPLOYEE_ID + "," + EmployeeDetails.COLUMN_NAME_EMPLOYEE_NAME + "," + EmployeeDetails.COLUMN_NAME_EMPLOYEE_DESIGNATION + " from " + EmployeeDetails.TABLE_NAME + " where " + EmployeeDetails.COLUMN_NAME_EMPLOYEE_DEPARTMENT_ID + " = '13b65ba2-9c63-40f9-91b9-5c52d7209977' order by " + EmployeeDetails.COLUMN_NAME_EMPLOYEE_NAME + ";";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getEmployeeID(String Name) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + EmployeeDetails.COLUMN_NAME_EMPLOYEE_ID + " from " + EmployeeDetails.TABLE_NAME + " where " + EmployeeDetails.COLUMN_NAME_EMPLOYEE_NAME + "='" + Name + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void deleteEmployee() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EmployeeDetails.TABLE_NAME, null, null);
        db.close();
    }
}

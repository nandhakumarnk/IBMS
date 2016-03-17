package com.rd.strivos.cobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rd.strivos.cobby.PreCommissioningDBFields.PreCommissioningAdd;

import java.sql.SQLException;

/**
 * Created by COBURG DESIGN on 11-02-2016.
 */
public class PreCommissioningDBHelper extends SQLiteOpenHelper {

    public PreCommissioningDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "precommissioninglive.db";

    private static final String SQL_CREATE_PRECOMMISSIONING = "CREATE TABLE "
            + PreCommissioningAdd.TABLE_NAME + " (" + PreCommissioningAdd._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PreCommissioningAdd.COLUMN_NAME_SERVICE_ENGINEER_ID + " TEXT," //1
            + PreCommissioningAdd.COLUMN_NAME_SERVICE_ENGINEER_NAME + " TEXT," //2
            + PreCommissioningAdd.COLUMN_NAME_ADDRESS + " TEXT," //3
            + PreCommissioningAdd.COLUMN_NAME_PINCODE + " TEXT," //4
            + PreCommissioningAdd.COLUMN_NAME_DISTRICT + " TEXT," //5
            + PreCommissioningAdd.COLUMN_NAME_CUSTOMER_NAME + " TEXT," //6
            + PreCommissioningAdd.COLUMN_NAME_CUSTOMER_ID + " TEXT," //7
            + PreCommissioningAdd.COLUMN_NAME_ACCOUNT_M_CONTACT_ID + " TEXT," //8
            + PreCommissioningAdd.COLUMN_NAME_ACCOUNT_M_CONTACT_NAME + " TEXT," //9
            + PreCommissioningAdd.COLUMN_NAME_PHONE_NO + " TEXT," //10
            + PreCommissioningAdd.COLUMN_NAME_EMAIL + " TEXT," //11
            + PreCommissioningAdd.COLUMN_NAME_PRODUCT_ID + " TEXT," //12
            + PreCommissioningAdd.COLUMN_NAME_PRODUCT_NAME + " TEXT," //13
            + PreCommissioningAdd.COLUMN_NAME_MACHINE_SL_NO + " TEXT," //14
            + PreCommissioningAdd.COLUMN_NAME_PART_NO + " TEXT," //15
            + PreCommissioningAdd.COLUMN_NAME_EDC + " TEXT," //16
            + PreCommissioningAdd.COLUMN_NAME_YOM + " TEXT," //17
            + PreCommissioningAdd.COLUMN_NAME_REMARKS + " TEXT," //18
            + PreCommissioningAdd.COLUMN_NAME_LAT + " TEXT," //19
            + PreCommissioningAdd.COLUMN_NAME_LON + " TEXT," //20
            + PreCommissioningAdd.COLUMN_NAME_STATUS + " TEXT" + " )"; //21

    private static final String SQL_DELETE_PRECOMMISSIONING = "DROP TABLE IF EXISTS "
            + PreCommissioningAdd.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PRECOMMISSIONING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PRECOMMISSIONING);
        onCreate(db);
    }

    public long insertPreCommissioning(String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9, String val10, String val11, String val12, String val13, String val14, String val15, String val16, String val17, String val18, String val19, String val20, String val21) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_SERVICE_ENGINEER_ID, val1);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_SERVICE_ENGINEER_NAME, val2);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_ADDRESS, val3);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_PINCODE, val4);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_DISTRICT, val5);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_CUSTOMER_NAME, val6);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_CUSTOMER_ID, val7);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_ACCOUNT_M_CONTACT_ID, val8);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_ACCOUNT_M_CONTACT_NAME, val9);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_PHONE_NO, val10);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_EMAIL, val11);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_PRODUCT_ID, val12);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_PRODUCT_NAME, val13);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_MACHINE_SL_NO, val14);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_PART_NO, val15);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_EDC, val16);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_YOM, val17);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_REMARKS, val18);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_LAT, val19);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_LON, val20);
        initialValues.put(PreCommissioningAdd.COLUMN_NAME_STATUS, val21);

        long l = db.insert(PreCommissioningAdd.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public void PreCommissioningStatusUpdate(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PreCommissioningAdd.COLUMN_NAME_STATUS, "Y");
        System.out.print(val1);

        sqdb.update(PreCommissioningAdd.TABLE_NAME, values, "_id=" + val1, null);
    }

    public Cursor selectPreCommissioning() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + PreCommissioningAdd.TABLE_NAME + " WHERE " + PreCommissioningAdd.COLUMN_NAME_STATUS + " ='N'";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
}

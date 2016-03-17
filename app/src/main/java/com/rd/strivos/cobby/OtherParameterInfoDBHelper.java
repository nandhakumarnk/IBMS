package com.rd.strivos.cobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rd.strivos.cobby.OtherParameterDBFields.OtherParameterInfoAdd;

import java.sql.SQLException;

/**
 * Created by COBURG DESIGN on 09-02-2016.
 */
public class OtherParameterInfoDBHelper extends SQLiteOpenHelper {

    public OtherParameterInfoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "otherparameterlive.db";

    private static final String SQL_CREATE_OTHERPARAMETER = "CREATE TABLE "
            + OtherParameterInfoAdd.TABLE_NAME + " (" + OtherParameterInfoAdd._ID //0
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + OtherParameterInfoAdd.DRYER_CURRENT_IN_VOLTAGE_R + " TEXT,"//1
            + OtherParameterInfoAdd.DRYER_CURRENT_IN_VOLTAGE_Y + " TEXT,"//2
            + OtherParameterInfoAdd.DRYER_CURRENT_IN_VOLTAGE_B + " TEXT,"//3
            + OtherParameterInfoAdd.DRYER_CURRENT_IN_LOAD_CONDITION_AMPS_R + " TEXT,"//4
            + OtherParameterInfoAdd.DRYER_CURRENT_IN_LOAD_CONDITION_AMPS_Y + " TEXT,"//5
            + OtherParameterInfoAdd.DRYER_CURRENT_IN_LOAD_CONDITION_AMPS_B + " TEXT,"//6
            + OtherParameterInfoAdd.DRYER_DEW_POINT_INDICATION + " TEXT,"//7
            + OtherParameterInfoAdd.FILTER_CATRIDGE + " TEXT,"//8
            + OtherParameterInfoAdd.FILTER_CATRIDGE_INDICATION_LEVEL + " TEXT,"//9
            + OtherParameterInfoAdd.FILTER_CATRIDGE_CHANGE_DATE + " TEXT,"//10
            + OtherParameterInfoAdd.COLUMN_NAME_PROJECT_TYPE + " TEXT," //11
            + OtherParameterInfoAdd.COLUMN_NAME_PROJECT_NAME + " TEXT," //12
            + OtherParameterInfoAdd.COLUMN_NAME_STATUS + " TEXT" + " )"; //13

    private static final String SQL_DELETE_OTHERPARAMETER = "DROP TABLE IF EXISTS "
            + OtherParameterInfoAdd.TABLE_NAME;


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_OTHERPARAMETER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_OTHERPARAMETER);
        onCreate(db);
    }

    public long insertOtherParameter(String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9, String val10, String val11, String val12, String val13) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(OtherParameterInfoAdd.DRYER_CURRENT_IN_VOLTAGE_R, val1);
        initialValues.put(OtherParameterInfoAdd.DRYER_CURRENT_IN_VOLTAGE_Y, val2);
        initialValues.put(OtherParameterInfoAdd.DRYER_CURRENT_IN_VOLTAGE_B, val3);
        initialValues.put(OtherParameterInfoAdd.DRYER_CURRENT_IN_LOAD_CONDITION_AMPS_R, val4);
        initialValues.put(OtherParameterInfoAdd.DRYER_CURRENT_IN_LOAD_CONDITION_AMPS_Y, val5);
        initialValues.put(OtherParameterInfoAdd.DRYER_CURRENT_IN_LOAD_CONDITION_AMPS_B, val6);
        initialValues.put(OtherParameterInfoAdd.DRYER_DEW_POINT_INDICATION, val7);
        initialValues.put(OtherParameterInfoAdd.FILTER_CATRIDGE, val8);
        initialValues.put(OtherParameterInfoAdd.FILTER_CATRIDGE_INDICATION_LEVEL, val9);
        initialValues.put(OtherParameterInfoAdd.FILTER_CATRIDGE_CHANGE_DATE, val10);
        initialValues.put(OtherParameterInfoAdd.COLUMN_NAME_PROJECT_TYPE, val11);
        initialValues.put(OtherParameterInfoAdd.COLUMN_NAME_PROJECT_NAME, val12);
        initialValues.put(OtherParameterInfoAdd.COLUMN_NAME_STATUS, val13);

        long l = db.insert(OtherParameterInfoAdd.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public Cursor OtherPaymentsForSync(String ProjectName, String ProjectType) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + OtherParameterInfoAdd.TABLE_NAME + " WHERE " + OtherParameterInfoAdd.COLUMN_NAME_PROJECT_NAME + "='" + ProjectName + "' and " + OtherParameterInfoAdd.COLUMN_NAME_PROJECT_TYPE + " = '" + ProjectType + "' and " + OtherParameterInfoAdd.COLUMN_NAME_STATUS + " = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void OtherParameterStatusUpdate(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OtherParameterInfoAdd.COLUMN_NAME_STATUS, "Y");
        System.out.print(val1);

        sqdb.update(OtherParameterInfoAdd.TABLE_NAME, values, "_id=" + val1, null);
    }
}

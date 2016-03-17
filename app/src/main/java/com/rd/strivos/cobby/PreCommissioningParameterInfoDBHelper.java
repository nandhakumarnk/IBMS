package com.rd.strivos.cobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rd.strivos.cobby.PreCommissioningParameterInfoDBFields.PreCommissioningParameterInfoAdd;

import java.sql.SQLException;

/**
 * Created by COBURG DESIGN on 11-02-2016.
 */
public class PreCommissioningParameterInfoDBHelper extends SQLiteOpenHelper {

    public PreCommissioningParameterInfoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "precommissioningparameterinfolive.db";

    private static final String SQL_CREATE_PRECOMMISSIONING_PARAMETER = "CREATE TABLE "
            + PreCommissioningParameterInfoAdd.TABLE_NAME + " (" + PreCommissioningParameterInfoAdd._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PreCommissioningParameterInfoAdd.ENVIRONMENT + " TEXT," //1
            + PreCommissioningParameterInfoAdd.ROOM_VENTILATION + " TEXT," //2
            + PreCommissioningParameterInfoAdd.DUCTING_RECOMMENDED + " TEXT," //3
            + PreCommissioningParameterInfoAdd.FLEXIBLE_CONNECTION + " TEXT," //4
            + PreCommissioningParameterInfoAdd.INSOLATION_VALVE + " TEXT," //5
            + PreCommissioningParameterInfoAdd.INCOMING_VOLTAGE + " TEXT," //6
            + PreCommissioningParameterInfoAdd.SUPPLY_COPPER_CABLE_RECOMMENDED_IN_MM + " TEXT," //7
            + PreCommissioningParameterInfoAdd.FUSE_RECOMMENDED_IN_AMPS + " TEXT," //8
            + PreCommissioningParameterInfoAdd.DRYER_PIPELINE_BYPASS_RECOMMENDED + " TEXT," //9
            + PreCommissioningParameterInfoAdd.FILTER_PIPELINE_BYPASS_RECOMMENDED + " TEXT," //10
            + PreCommissioningParameterInfoAdd.VERTICAL_AIR_RECEIVER_GROUTING_RECOMMENDED + " TEXT," //11
            + PreCommissioningParameterInfoAdd.COLUMN_NAME_PROJECT_TYPE + " TEXT," //12
            + PreCommissioningParameterInfoAdd.COLUMN_NAME_PROJECT_NAME + " TEXT," //13
            + PreCommissioningParameterInfoAdd.COLUMN_NAME_STATUS + " TEXT" + " )"; //14

    private static final String SQL_DELETE_PRECOMMISSIONING_PARAMETER = "DROP TABLE IF EXISTS "
            + PreCommissioningParameterInfoAdd.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PRECOMMISSIONING_PARAMETER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PRECOMMISSIONING_PARAMETER);
        onCreate(db);
    }

    public long insertPreCommissioningParameterInfo(String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9, String val10, String val11, String val12, String val13, String val14) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(PreCommissioningParameterInfoAdd.ENVIRONMENT, val1);
        initialValues.put(PreCommissioningParameterInfoAdd.ROOM_VENTILATION, val2);
        initialValues.put(PreCommissioningParameterInfoAdd.DUCTING_RECOMMENDED, val3);
        initialValues.put(PreCommissioningParameterInfoAdd.FLEXIBLE_CONNECTION, val4);
        initialValues.put(PreCommissioningParameterInfoAdd.INSOLATION_VALVE, val5);
        initialValues.put(PreCommissioningParameterInfoAdd.INCOMING_VOLTAGE, val6);
        initialValues.put(PreCommissioningParameterInfoAdd.SUPPLY_COPPER_CABLE_RECOMMENDED_IN_MM, val7);
        initialValues.put(PreCommissioningParameterInfoAdd.FUSE_RECOMMENDED_IN_AMPS, val8);
        initialValues.put(PreCommissioningParameterInfoAdd.DRYER_PIPELINE_BYPASS_RECOMMENDED, val9);
        initialValues.put(PreCommissioningParameterInfoAdd.FILTER_PIPELINE_BYPASS_RECOMMENDED, val10);
        initialValues.put(PreCommissioningParameterInfoAdd.VERTICAL_AIR_RECEIVER_GROUTING_RECOMMENDED, val11);
        initialValues.put(PreCommissioningParameterInfoAdd.COLUMN_NAME_PROJECT_TYPE, val12);
        initialValues.put(PreCommissioningParameterInfoAdd.COLUMN_NAME_PROJECT_NAME, val13);
        initialValues.put(PreCommissioningParameterInfoAdd.COLUMN_NAME_STATUS, val14);

        long l = db.insert(PreCommissioningParameterInfoAdd.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public Cursor PreCommissioningParameterInfoForSync(String ProjectName, String ProjectType) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + PreCommissioningParameterInfoAdd.TABLE_NAME + " WHERE " + PreCommissioningParameterInfoAdd.COLUMN_NAME_PROJECT_NAME + "='" + ProjectName + "' and " + PreCommissioningParameterInfoAdd.COLUMN_NAME_PROJECT_TYPE + " = '" + ProjectType + "' and " + PreCommissioningParameterInfoAdd.COLUMN_NAME_STATUS + " = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void PreCommissioningParameterInfoStatusUpdate(String val1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PreCommissioningParameterInfoAdd.COLUMN_NAME_STATUS, "Y");

        db.update(PreCommissioningParameterInfoAdd.TABLE_NAME, values, "_id=" + val1, null);
    }

}

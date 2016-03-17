package com.rd.strivos.cobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rd.strivos.cobby.ParameterDBFields.ParameterAdd;

import java.sql.SQLException;

/**
 * Created by COBURG DESIGN on 25-01-2016.
 */
public class ParameterDBHelper extends SQLiteOpenHelper {

    public ParameterDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "parameterlive.db";

    private static final String SQL_CREATE_PARAMETER = "CREATE TABLE "
            + ParameterAdd.TABLE_NAME + " (" + ParameterAdd._ID //0
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ParameterAdd.COLUMN_NAME_CONTROL_TYPE + " TEXT," //1
            + ParameterAdd.COLUMN_NAME_PRESSURE_SETTING + " TEXT," //2
            + ParameterAdd.COLUMN_NAME_AMBIENT_TEMPERATURE + " TEXT," //3
            + ParameterAdd.COLUMN_NAME_INCOMING_VOLTAGE + " TEXT," //4
            + ParameterAdd.COLUMN_NAME_DUCTING_FACILITY + " TEXT," //5
            + ParameterAdd.COLUMN_NAME_RUNNING_HOURS_PER_DAY + " TEXT," //6
            + ParameterAdd.COLUMN_NAME_RUNNING_DAYS_PER_WEEK + " TEXT," //7
            + ParameterAdd.COLUMN_NAME_IS_COMPRESSOR_CLEANED_EVERYDAY + " TEXT," //8
            + ParameterAdd.COLUMN_NAME_TOTAL_RUN_HOURS + " TEXT," //9
            + ParameterAdd.COLUMN_NAME_TOTAL_LOAD_HOURS + " TEXT," //10
            + ParameterAdd.COLUMN_NAME_AIREND_DISCHARGE_TEMPERATURE + " TEXT," //11
            + ParameterAdd.COLUMN_NAME_CURRENT_IN_LOAD_CONDITION_AMPS + " TEXT," //12
            + ParameterAdd.COLUMN_NAME_CURRENT_IN_IDLE_CONDITION_AMPS + " TEXT," //13
            + ParameterAdd.COLUMN_NAME_NO_OF_MOTOR_STARTS + " TEXT," //14
            + ParameterAdd.COLUMN_NAME_NO_OF_LOAD_VALVE_ON + " TEXT," //15
            + ParameterAdd.COLUMN_NAME_OIL_LEVEL + " TEXT," //16
            + ParameterAdd.COLUMN_NAME_COOLER_CONDITION + " TEXT," //17
            + ParameterAdd.COLUMN_NAME_MOTOR_BEARING_NOISE + " TEXT," //18
            + ParameterAdd.COLUMN_NAME_AIREND_BEARING_NOISE + " TEXT," //19
            + ParameterAdd.COLUMN_NAME_INLET_VALVE_WORKING_CONDITION + " TEXT," //20
            + ParameterAdd.COLUMN_NAME_COMBINATION_VALVE_WORKING_CONDITION + " TEXT," //21
            + ParameterAdd.COLUMN_NAME_CAV_VALVE_WORKING_CONDITION + " TEXT," //22
            + ParameterAdd.COLUMN_NAME_MPC_VALVE_WORKING_CONDITION + " TEXT," //23
            + ParameterAdd.COLUMN_NAME_COUPLING_CONDITION + " TEXT," //24
            + ParameterAdd.COLUMN_NAME_BELT_CONDITION + " TEXT," //25
            + ParameterAdd.COLUMN_NAME_AIR_FILTER + " TEXT," //26
            + ParameterAdd.COLUMN_NAME_OIL_FILTER + " TEXT," //27
            + ParameterAdd.COLUMN_NAME_OIL_SEPARATOR + " TEXT," //28
            + ParameterAdd.COLUMN_NAME_OIL + " TEXT," //29
            + ParameterAdd.COLUMN_NAME_FILTER_MAT + " TEXT," //30
            + ParameterAdd.COLUMN_NAME_BEARING_REPLACEMENT + " TEXT," //31
            + ParameterAdd.COLUMN_NAME_MOTOR_GREASING + " TEXT," //32
            + ParameterAdd.COLUMN_NAME_MOTOR_GREASING_IN_HOURS + " TEXT," //33
            + ParameterAdd.COLUMN_NAME_SUMP_PRESSURE_AT_UNLOAD_CONDITION + " TEXT," //34
            + ParameterAdd.COLUMN_NAME_PROJECT_TYPE + " TEXT," //35
            + ParameterAdd.COLUMN_NAME_PROJECT_NAME + " TEXT," //36
            + ParameterAdd.COLUMN_NAME_STATUS + " TEXT" + " )"; //37

    private static final String SQL_DELETE_PARAMETER = "DROP TABLE IF EXISTS "
            + ParameterAdd.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PARAMETER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PARAMETER);
        onCreate(db);
    }

    public long insertParameter(String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9, String val10, String val11, String val12, String val13, String val14, String val15, String val16, String val17, String val18, String val19, String val20, String val21, String val22, String val23, String val24, String val25, String val26, String val27, String val28, String val29, String val30, String val31, String val32, String val33, String val34, String val35, String val36, String val37) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(ParameterAdd.COLUMN_NAME_CONTROL_TYPE, val1);
        initialValues.put(ParameterAdd.COLUMN_NAME_PRESSURE_SETTING, val2);
        initialValues.put(ParameterAdd.COLUMN_NAME_AMBIENT_TEMPERATURE, val3);
        initialValues.put(ParameterAdd.COLUMN_NAME_INCOMING_VOLTAGE, val4);
        initialValues.put(ParameterAdd.COLUMN_NAME_DUCTING_FACILITY, val5);
        initialValues.put(ParameterAdd.COLUMN_NAME_RUNNING_HOURS_PER_DAY, val6);
        initialValues.put(ParameterAdd.COLUMN_NAME_RUNNING_DAYS_PER_WEEK, val7);
        initialValues.put(ParameterAdd.COLUMN_NAME_IS_COMPRESSOR_CLEANED_EVERYDAY, val8);
        initialValues.put(ParameterAdd.COLUMN_NAME_TOTAL_RUN_HOURS, val9);
        initialValues.put(ParameterAdd.COLUMN_NAME_TOTAL_LOAD_HOURS, val10);
        initialValues.put(ParameterAdd.COLUMN_NAME_AIREND_DISCHARGE_TEMPERATURE, val11);
        initialValues.put(ParameterAdd.COLUMN_NAME_CURRENT_IN_LOAD_CONDITION_AMPS, val12);
        initialValues.put(ParameterAdd.COLUMN_NAME_CURRENT_IN_IDLE_CONDITION_AMPS, val13);
        initialValues.put(ParameterAdd.COLUMN_NAME_NO_OF_MOTOR_STARTS, val14);
        initialValues.put(ParameterAdd.COLUMN_NAME_NO_OF_LOAD_VALVE_ON, val15);
        initialValues.put(ParameterAdd.COLUMN_NAME_OIL_LEVEL, val16);
        initialValues.put(ParameterAdd.COLUMN_NAME_COOLER_CONDITION, val17);
        initialValues.put(ParameterAdd.COLUMN_NAME_MOTOR_BEARING_NOISE, val18);
        initialValues.put(ParameterAdd.COLUMN_NAME_AIREND_BEARING_NOISE, val19);
        initialValues.put(ParameterAdd.COLUMN_NAME_INLET_VALVE_WORKING_CONDITION, val20);
        initialValues.put(ParameterAdd.COLUMN_NAME_COMBINATION_VALVE_WORKING_CONDITION, val21);
        initialValues.put(ParameterAdd.COLUMN_NAME_CAV_VALVE_WORKING_CONDITION, val22);
        initialValues.put(ParameterAdd.COLUMN_NAME_MPC_VALVE_WORKING_CONDITION, val23);
        initialValues.put(ParameterAdd.COLUMN_NAME_COUPLING_CONDITION, val24);
        initialValues.put(ParameterAdd.COLUMN_NAME_BELT_CONDITION, val25);
        initialValues.put(ParameterAdd.COLUMN_NAME_AIR_FILTER, val26);
        initialValues.put(ParameterAdd.COLUMN_NAME_OIL_FILTER, val27);
        initialValues.put(ParameterAdd.COLUMN_NAME_OIL_SEPARATOR, val28);
        initialValues.put(ParameterAdd.COLUMN_NAME_OIL, val29);
        initialValues.put(ParameterAdd.COLUMN_NAME_FILTER_MAT, val30);
        initialValues.put(ParameterAdd.COLUMN_NAME_BEARING_REPLACEMENT, val31);
        initialValues.put(ParameterAdd.COLUMN_NAME_MOTOR_GREASING, val32);
        initialValues.put(ParameterAdd.COLUMN_NAME_MOTOR_GREASING_IN_HOURS, val33);
        initialValues.put(ParameterAdd.COLUMN_NAME_SUMP_PRESSURE_AT_UNLOAD_CONDITION, val34);
        initialValues.put(ParameterAdd.COLUMN_NAME_PROJECT_TYPE, val35);
        initialValues.put(ParameterAdd.COLUMN_NAME_PROJECT_NAME, val36);
        initialValues.put(ParameterAdd.COLUMN_NAME_STATUS, val37);

        long l = db.insert(ParameterAdd.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public void updateParameter(long val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9, String val10, String val11, String val12, String val13, String val14, String val15, String val16, String val17, String val18, String val19, String val20, String val21, String val22, String val23, String val24, String val25, String val26, String val27, String val28, String val29, String val30, String val31, String val32, String val33, String val34, String val35, String val36, String val37) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ParameterAdd.COLUMN_NAME_CONTROL_TYPE, val2);
        values.put(ParameterAdd.COLUMN_NAME_PRESSURE_SETTING, val3);
        values.put(ParameterAdd.COLUMN_NAME_AMBIENT_TEMPERATURE, val4);
        values.put(ParameterAdd.COLUMN_NAME_INCOMING_VOLTAGE, val5);
        values.put(ParameterAdd.COLUMN_NAME_DUCTING_FACILITY, val6);
        values.put(ParameterAdd.COLUMN_NAME_RUNNING_HOURS_PER_DAY, val7);
        values.put(ParameterAdd.COLUMN_NAME_RUNNING_DAYS_PER_WEEK, val8);
        values.put(ParameterAdd.COLUMN_NAME_IS_COMPRESSOR_CLEANED_EVERYDAY, val9);
        values.put(ParameterAdd.COLUMN_NAME_TOTAL_RUN_HOURS, val10);
        values.put(ParameterAdd.COLUMN_NAME_TOTAL_LOAD_HOURS, val11);
        values.put(ParameterAdd.COLUMN_NAME_AIREND_DISCHARGE_TEMPERATURE, val12);
        values.put(ParameterAdd.COLUMN_NAME_CURRENT_IN_LOAD_CONDITION_AMPS, val13);
        values.put(ParameterAdd.COLUMN_NAME_CURRENT_IN_IDLE_CONDITION_AMPS, val14);
        values.put(ParameterAdd.COLUMN_NAME_NO_OF_MOTOR_STARTS, val15);
        values.put(ParameterAdd.COLUMN_NAME_NO_OF_LOAD_VALVE_ON, val16);
        values.put(ParameterAdd.COLUMN_NAME_OIL_LEVEL, val17);
        values.put(ParameterAdd.COLUMN_NAME_COOLER_CONDITION, val18);
        values.put(ParameterAdd.COLUMN_NAME_MOTOR_BEARING_NOISE, val19);
        values.put(ParameterAdd.COLUMN_NAME_AIREND_BEARING_NOISE, val20);
        values.put(ParameterAdd.COLUMN_NAME_INLET_VALVE_WORKING_CONDITION, val21);
        values.put(ParameterAdd.COLUMN_NAME_COMBINATION_VALVE_WORKING_CONDITION, val22);
        values.put(ParameterAdd.COLUMN_NAME_CAV_VALVE_WORKING_CONDITION, val23);
        values.put(ParameterAdd.COLUMN_NAME_MPC_VALVE_WORKING_CONDITION, val24);
        values.put(ParameterAdd.COLUMN_NAME_COUPLING_CONDITION, val25);
        values.put(ParameterAdd.COLUMN_NAME_BELT_CONDITION, val26);
        values.put(ParameterAdd.COLUMN_NAME_AIR_FILTER, val27);
        values.put(ParameterAdd.COLUMN_NAME_OIL_FILTER, val28);
        values.put(ParameterAdd.COLUMN_NAME_OIL_SEPARATOR, val29);
        values.put(ParameterAdd.COLUMN_NAME_OIL, val30);
        values.put(ParameterAdd.COLUMN_NAME_FILTER_MAT, val31);
        values.put(ParameterAdd.COLUMN_NAME_BEARING_REPLACEMENT, val32);
        values.put(ParameterAdd.COLUMN_NAME_MOTOR_GREASING, val33);
        values.put(ParameterAdd.COLUMN_NAME_MOTOR_GREASING_IN_HOURS, val34);
        values.put(ParameterAdd.COLUMN_NAME_SUMP_PRESSURE_AT_UNLOAD_CONDITION, val35);
        values.put(ParameterAdd.COLUMN_NAME_PROJECT_TYPE, val36);
        values.put(ParameterAdd.COLUMN_NAME_PROJECT_NAME, val37);

        sqdb.update(ParameterAdd.TABLE_NAME, values, "_id=" + val1, null);
    }

    public int deleteParameter(long id) {
        return getWritableDatabase().delete(ParameterAdd.TABLE_NAME, ParameterAdd._ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Cursor getParameterList(String ProjectName, String ProjectType) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + ParameterAdd.TABLE_NAME + " WHERE " + ParameterAdd.COLUMN_NAME_PROJECT_NAME + "='" + ProjectName + "' and " + ParameterAdd.COLUMN_NAME_PROJECT_TYPE + " = '" + ProjectType + "' and " + ParameterAdd.COLUMN_NAME_STATUS + " = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor PaymentsForSync(String ProjectName, String ProjectType) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + ParameterAdd.TABLE_NAME + " WHERE " + ParameterAdd.COLUMN_NAME_PROJECT_NAME + "='" + ProjectName + "' and " + ParameterAdd.COLUMN_NAME_PROJECT_TYPE + " = '" + ProjectType + "' and " + ParameterAdd.COLUMN_NAME_STATUS + " = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void ParameterStatusUpdate(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ParameterAdd.COLUMN_NAME_STATUS, "Y");
        System.out.print(val1);

        sqdb.update(ParameterAdd.TABLE_NAME, values, "_id=" + val1, null);
    }
}

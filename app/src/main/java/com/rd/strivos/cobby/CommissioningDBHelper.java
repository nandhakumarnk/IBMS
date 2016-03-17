package com.rd.strivos.cobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rd.strivos.cobby.CommissioningDBFields.CommissioningAdd;
import com.rd.strivos.cobby.CommissioningDBFields.CommissioningParameterInfoAdd;
import com.rd.strivos.cobby.CommissioningDBFields.ContractTypeAdd;
import com.rd.strivos.cobby.CommissioningDBFields.PreCommissioningListAdd;

import java.sql.SQLException;

/**
 * Created by COBURG DESIGN on 18-02-2016.
 */
public class CommissioningDBHelper extends SQLiteOpenHelper {

    public CommissioningDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "commissioninglive.db";

    private static final String SQL_CREATE_COMMISSIONING = "CREATE TABLE "
            + CommissioningAdd.TABLE_NAME + " (" + CommissioningAdd._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CommissioningAdd.COLUMN_NAME_SERVICE_ENGINEER_ID + " TEXT," //1
            + CommissioningAdd.COLUMN_NAME_SERVICE_ENGINEER_NAME + " TEXT," //2
            + CommissioningAdd.COLUMN_NAME_ADDRESS + " TEXT," //3
            + CommissioningAdd.COLUMN_NAME_PINCODE + " TEXT," //4
            + CommissioningAdd.COLUMN_NAME_DISTRICT + " TEXT," //5
            + CommissioningAdd.COLUMN_NAME_CUSTOMER_NAME + " TEXT," //6
            + CommissioningAdd.COLUMN_NAME_CUSTOMER_ID + " TEXT," //7
            + CommissioningAdd.COLUMN_NAME_ACCOUNT_M_CONTACT_ID + " TEXT," //8
            + CommissioningAdd.COLUMN_NAME_ACCOUNT_M_CONTACT_NAME + " TEXT," //9
            + CommissioningAdd.COLUMN_NAME_PHONE_NO + " TEXT," //10
            + CommissioningAdd.COLUMN_NAME_EMAIL + " TEXT," //11
            + CommissioningAdd.COLUMN_NAME_PRODUCT_ID + " TEXT," //12
            + CommissioningAdd.COLUMN_NAME_PRODUCT_NAME + " TEXT," //13
            + CommissioningAdd.COLUMN_NAME_MACHINE_SL_NO + " TEXT," //14
            + CommissioningAdd.COLUMN_NAME_PART_NO + " TEXT," //15
            + CommissioningAdd.COLUMN_NAME_CONTRACT_TYPE_ID + " TEXT," //16
            + CommissioningAdd.COLUMN_NAME_FROM_DATE + " TEXT," //17
            + CommissioningAdd.COLUMN_NAME_TO_DATE + " TEXT," //18
            + CommissioningAdd.COLUMN_NAME_CONTRACT_AMOUNT + " TEXT," //19
            + CommissioningAdd.COLUMN_NAME_VISIT_CHARGES + " TEXT," //20
            + CommissioningAdd.COLUMN_NAME_FREE_VISITS + " TEXT," //21
            + CommissioningAdd.COLUMN_NAME_REMARKS + " TEXT," //22
            + CommissioningAdd.COLUMN_NAME_LAT + " TEXT," //23
            + CommissioningAdd.COLUMN_NAME_LON + " TEXT," //24
            + CommissioningAdd.COLUMN_NAME_STATUS + " TEXT" + " )"; //25

    private static final String SQL_DELETE_COMMISSIONING = "DROP TABLE IF EXISTS "
            + CommissioningAdd.TABLE_NAME;


    private static final String SQL_CREATE_CONTRACT_TYPE = "CREATE TABLE "
            + ContractTypeAdd.TABLE_NAME + " (" + ContractTypeAdd._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ContractTypeAdd.COLUMN_NAME_CONTRACT_TYPE_ID + " TEXT," //1
            + ContractTypeAdd.COLUMN_NAME_CONTRACT_TYPE_DESCRIPTION + " TEXT," //2
            + ContractTypeAdd.COLUMN_NAME_CONTRACT_TYPE_MASTER_TYPE_ID + " TEXT" + " )"; //3

    private static final String SQL_DELETE_CONTRACT_TYPE = "DROP TABLE IF EXISTS "
            + ContractTypeAdd.TABLE_NAME;

    private static final String SQL_CREATE_COMMISSIONING_PARAMETER_INFO = "CREATE TABLE "
            + CommissioningParameterInfoAdd.TABLE_NAME + " (" + CommissioningParameterInfoAdd._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CommissioningParameterInfoAdd.COLUMN_NAME_ENVIRONMENT + " TEXT," //1
            + CommissioningParameterInfoAdd.COLUMN_NAME_AMBIENT_TEMPERATURE_IN_DEGREE_CELSIUS + " TEXT," //2
            + CommissioningParameterInfoAdd.COLUMN_NAME_DUCTING_INSTALLED + " TEXT," //3
            + CommissioningParameterInfoAdd.COLUMN_NAME_ROOM_VENTILATION + " TEXT," //4
            + CommissioningParameterInfoAdd.COLUMN_NAME_FLEXIBLE_CONNECTION + " TEXT," //5
            + CommissioningParameterInfoAdd.COLUMN_NAME_INSOLATION_VALUE + " TEXT," //6
            + CommissioningParameterInfoAdd.COLUMN_NAME_INCOMING_VOLTAGE + " TEXT," //7
            + CommissioningParameterInfoAdd.COLUMN_NAME_SUPPLY_COPPER_CABLE_RECOMMENDED_IN_MM + " TEXT," //8
            + CommissioningParameterInfoAdd.COLUMN_NAME_FUSE_INSTALLED_IN_AMPS + " TEXT," //9
            + CommissioningParameterInfoAdd.COLUMN_NAME_FULL_LOAD_CURRENT + " TEXT," //10
            + CommissioningParameterInfoAdd.COLUMN_NAME_UNLOAD_CURRENT + " TEXT," //11
            + CommissioningParameterInfoAdd.COLUMN_NAME_SETTING_OVERLOAD_PROTECTION_OF_MAIN_MOTOR_IN_AMPS + " TEXT," //12
            + CommissioningParameterInfoAdd.COLUMN_NAME_SETTING_OVERLOAD_PROTECTION_OF_FAN_MOTOR_IN_AMPS + " TEXT," //13
            + CommissioningParameterInfoAdd.COLUMN_NAME_AIREND_TEMPERATURE_IN_DEGREE_CELCIUS + " TEXT," //14
            + CommissioningParameterInfoAdd.COLUMN_NAME_PRESSUR_SETTING + " TEXT," //15
            + CommissioningParameterInfoAdd.COLUMN_NAME_SUMP_PRESSURE_AT_UNLOAD_CONDITION + " TEXT," //16
            + CommissioningParameterInfoAdd.COLUMN_NAME_DRYER_PIPELINE_BYPASS + " TEXT," //17
            + CommissioningParameterInfoAdd.COLUMN_NAME_FILTER_PIPELINE_BYPASS + " TEXT," //18
            + CommissioningParameterInfoAdd.COLUMN_NAME_VERTICAL_AIR_RECEIVER_GROUTING + " TEXT," //19
            + CommissioningParameterInfoAdd.COLUMN_NAME_PROJECT_TYPE + " TEXT," //20
            + CommissioningParameterInfoAdd.COLUMN_NAME_PROJECT_NAME + " TEXT," //21
            + CommissioningParameterInfoAdd.COLUMN_NAME_STATUS + " TEXT" + " )"; //22

    private static final String SQL_DELETE_COMMISSIONING_PARAMETER_INFO = "DROP TABLE IF EXISTS "
            + CommissioningParameterInfoAdd.TABLE_NAME;

    private static final String SQL_CREATE_PRECOMMISSIONING_LIST = "CREATE TABLE "
            + PreCommissioningListAdd.TABLE_NAME + " (" + PreCommissioningListAdd._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PreCommissioningListAdd.COLUMN_NAME_ID + " TEXT," //1
            + PreCommissioningListAdd.COLUMN_NAME_LONGDOCUMENTNO + " TEXT," //2
            + PreCommissioningListAdd.COLUMN_NAME_CONVDOCDATE + " TEXT," //3
            + PreCommissioningListAdd.COLUMN_NAME_SERVICEENGINEER + " TEXT," //4
            + PreCommissioningListAdd.COLUMN_NAME_SLNO + " TEXT," //5
            + PreCommissioningListAdd.COLUMN_NAME_PARTNO + " TEXT," //6
            + PreCommissioningListAdd.COLUMN_NAME_PRODUCTPARTNO + " TEXT," //7
            + PreCommissioningListAdd.COLUMN_NAME_ACCOUNTM_ID + " TEXT" + " )"; //8

    private static final String SQL_DELETE_PRECOMMISSIONING_LIST = "DROP TABLE IF EXISTS "
            + PreCommissioningListAdd.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_COMMISSIONING);
        db.execSQL(SQL_CREATE_CONTRACT_TYPE);
        db.execSQL(SQL_CREATE_COMMISSIONING_PARAMETER_INFO);
        db.execSQL(SQL_CREATE_PRECOMMISSIONING_LIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_COMMISSIONING);
        db.execSQL(SQL_DELETE_CONTRACT_TYPE);
        db.execSQL(SQL_DELETE_COMMISSIONING_PARAMETER_INFO);
        db.execSQL(SQL_DELETE_PRECOMMISSIONING_LIST);
        onCreate(db);
    }

    public long insertCommissioning(String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9, String val10, String val11, String val12, String val13, String val14, String val15, String val16, String val17, String val18, String val19, String val20, String val21, String val22, String val23, String val24, String val25) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(CommissioningAdd.COLUMN_NAME_SERVICE_ENGINEER_ID, val1);
        initialValues.put(CommissioningAdd.COLUMN_NAME_SERVICE_ENGINEER_NAME, val2);
        initialValues.put(CommissioningAdd.COLUMN_NAME_ADDRESS, val3);
        initialValues.put(CommissioningAdd.COLUMN_NAME_PINCODE, val4);
        initialValues.put(CommissioningAdd.COLUMN_NAME_DISTRICT, val5);
        initialValues.put(CommissioningAdd.COLUMN_NAME_CUSTOMER_NAME, val6);
        initialValues.put(CommissioningAdd.COLUMN_NAME_CUSTOMER_ID, val7);
        initialValues.put(CommissioningAdd.COLUMN_NAME_ACCOUNT_M_CONTACT_ID, val8);
        initialValues.put(CommissioningAdd.COLUMN_NAME_ACCOUNT_M_CONTACT_NAME, val9);
        initialValues.put(CommissioningAdd.COLUMN_NAME_PHONE_NO, val10);
        initialValues.put(CommissioningAdd.COLUMN_NAME_EMAIL, val11);
        initialValues.put(CommissioningAdd.COLUMN_NAME_PRODUCT_ID, val12);
        initialValues.put(CommissioningAdd.COLUMN_NAME_PRODUCT_NAME, val13);
        initialValues.put(CommissioningAdd.COLUMN_NAME_MACHINE_SL_NO, val14);
        initialValues.put(CommissioningAdd.COLUMN_NAME_PART_NO, val15);
        initialValues.put(CommissioningAdd.COLUMN_NAME_CONTRACT_TYPE_ID, val16);
        initialValues.put(CommissioningAdd.COLUMN_NAME_FROM_DATE, val17);
        initialValues.put(CommissioningAdd.COLUMN_NAME_TO_DATE, val18);
        initialValues.put(CommissioningAdd.COLUMN_NAME_CONTRACT_AMOUNT, val19);
        initialValues.put(CommissioningAdd.COLUMN_NAME_VISIT_CHARGES, val20);
        initialValues.put(CommissioningAdd.COLUMN_NAME_FREE_VISITS, val21);
        initialValues.put(CommissioningAdd.COLUMN_NAME_REMARKS, val22);
        initialValues.put(CommissioningAdd.COLUMN_NAME_LAT, val23);
        initialValues.put(CommissioningAdd.COLUMN_NAME_LON, val24);
        initialValues.put(CommissioningAdd.COLUMN_NAME_STATUS, val25);

        long l = db.insert(CommissioningAdd.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public void CommissioningStatusUpdate(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CommissioningAdd.COLUMN_NAME_STATUS, "Y");
        System.out.print(val1);

        sqdb.update(CommissioningAdd.TABLE_NAME, values, "_id=" + val1, null);
    }

    public Cursor selectCommissioning() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + CommissioningAdd.TABLE_NAME + " WHERE " + CommissioningAdd.COLUMN_NAME_STATUS + " ='N'";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }


    // CONTRACT TYPE

    public long insertContractType(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(ContractTypeAdd.COLUMN_NAME_CONTRACT_TYPE_ID, val1);
        initialValues.put(ContractTypeAdd.COLUMN_NAME_CONTRACT_TYPE_DESCRIPTION, val2);
        initialValues.put(ContractTypeAdd.COLUMN_NAME_CONTRACT_TYPE_MASTER_TYPE_ID, val3);
        long l = db.insert(ContractTypeAdd.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectContractType() throws android.database.SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + ContractTypeAdd._ID + "," + ContractTypeAdd.COLUMN_NAME_CONTRACT_TYPE_ID + "," + ContractTypeAdd.COLUMN_NAME_CONTRACT_TYPE_DESCRIPTION + "," + ContractTypeAdd.COLUMN_NAME_CONTRACT_TYPE_MASTER_TYPE_ID + " from " + ContractTypeAdd.TABLE_NAME + ";";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getContractTypeID(String ContractType) throws android.database.SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + ContractTypeAdd.COLUMN_NAME_CONTRACT_TYPE_ID + " from " + ContractTypeAdd.TABLE_NAME + " where " + ContractTypeAdd.COLUMN_NAME_CONTRACT_TYPE_DESCRIPTION + "='" + ContractType + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }


    // COMMISSIONING PARAMETER INFO

    public long insertCommissioningParameterInfo(String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9, String val10, String val11, String val12, String val13, String val14, String val15, String val16, String val17, String val18, String val19, String val20, String val21, String val22) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_ENVIRONMENT, val1);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_AMBIENT_TEMPERATURE_IN_DEGREE_CELSIUS, val2);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_DUCTING_INSTALLED, val3);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_ROOM_VENTILATION, val4);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_FLEXIBLE_CONNECTION, val5);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_INSOLATION_VALUE, val6);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_INCOMING_VOLTAGE, val7);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_SUPPLY_COPPER_CABLE_RECOMMENDED_IN_MM, val8);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_FUSE_INSTALLED_IN_AMPS, val9);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_FULL_LOAD_CURRENT, val10);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_UNLOAD_CURRENT, val11);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_SETTING_OVERLOAD_PROTECTION_OF_MAIN_MOTOR_IN_AMPS, val12);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_SETTING_OVERLOAD_PROTECTION_OF_FAN_MOTOR_IN_AMPS, val13);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_AIREND_TEMPERATURE_IN_DEGREE_CELCIUS, val14);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_PRESSUR_SETTING, val15);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_SUMP_PRESSURE_AT_UNLOAD_CONDITION, val16);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_DRYER_PIPELINE_BYPASS, val17);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_FILTER_PIPELINE_BYPASS, val18);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_VERTICAL_AIR_RECEIVER_GROUTING, val19);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_PROJECT_TYPE, val20);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_PROJECT_NAME, val21);
        initialValues.put(CommissioningParameterInfoAdd.COLUMN_NAME_STATUS, val22);

        long l = db.insert(CommissioningParameterInfoAdd.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public Cursor CommissioningParameterInfoForSync(String ProjectName, String ProjectType) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + CommissioningParameterInfoAdd.TABLE_NAME + " WHERE " + CommissioningParameterInfoAdd.COLUMN_NAME_PROJECT_NAME + "='" + ProjectName + "' and " + CommissioningParameterInfoAdd.COLUMN_NAME_PROJECT_TYPE + " = '" + ProjectType + "' and " + CommissioningParameterInfoAdd.COLUMN_NAME_STATUS + " = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void CommissioningParameterInfoStatusUpdate(String val1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CommissioningParameterInfoAdd.COLUMN_NAME_STATUS, "Y");

        db.update(CommissioningParameterInfoAdd.TABLE_NAME, values, "_id=" + val1, null);
    }

    // PRECOMMISSIONING LIST

    public long insertPreCommissioningList(String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(PreCommissioningListAdd.COLUMN_NAME_ID, val1);
        initialValues.put(PreCommissioningListAdd.COLUMN_NAME_LONGDOCUMENTNO, val2);
        initialValues.put(PreCommissioningListAdd.COLUMN_NAME_CONVDOCDATE, val3);
        initialValues.put(PreCommissioningListAdd.COLUMN_NAME_SERVICEENGINEER, val4);
        initialValues.put(PreCommissioningListAdd.COLUMN_NAME_SLNO, val5);
        initialValues.put(PreCommissioningListAdd.COLUMN_NAME_PARTNO, val6);
        initialValues.put(PreCommissioningListAdd.COLUMN_NAME_PRODUCTPARTNO, val7);
        initialValues.put(PreCommissioningListAdd.COLUMN_NAME_ACCOUNTM_ID, val8);

        long l = db.insert(PreCommissioningListAdd.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectPreCommissioningList(String AccountID) throws android.database.SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from " + PreCommissioningListAdd.TABLE_NAME + " where " + PreCommissioningListAdd.COLUMN_NAME_ACCOUNTM_ID + " = '" + AccountID + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectProductPreCommBasedID(String ID) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from " + PreCommissioningListAdd.TABLE_NAME + " WHERE " + PreCommissioningListAdd._ID + "=" + ID + " ;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    //DELETE

    public void deleteCommissioning() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ContractTypeAdd.TABLE_NAME, null, null);
        db.delete(PreCommissioningListAdd.TABLE_NAME, null, null);
        db.close();
    }


}

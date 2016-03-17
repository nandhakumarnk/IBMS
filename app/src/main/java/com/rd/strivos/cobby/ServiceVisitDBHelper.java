package com.rd.strivos.cobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rd.strivos.cobby.ServiceVisitDBFields.ServiceVisitType;
import com.rd.strivos.cobby.ServiceVisitDBFields.ServiceVisitWorkStatus;
import com.rd.strivos.cobby.ServiceVisitDBFields.ProductInstallationDtls;
import com.rd.strivos.cobby.ServiceVisitDBFields.ServiceVisit;
import com.rd.strivos.cobby.ServiceVisitDBFields.ServiceVisitList;

/**
 * Created by COBURG DESIGN on 18-01-2016.
 */
public class ServiceVisitDBHelper extends SQLiteOpenHelper {

    public ServiceVisitDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "servicevisitlive.db";

    private static final String SQL_CREATE_SERVICE_VISIT_TYPE = "CREATE TABLE "
            + ServiceVisitType.TABLE_NAME + " (" + ServiceVisitType._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ServiceVisitType.COLUMN_NAME_SERVICE_VISIT_TYPE_ID + " TEXT,"
            + ServiceVisitType.COLUMN_NAME_SERVICE_VISIT_TYPE_DESCRIPTION + " TEXT,"
            + ServiceVisitType.COLUMN_NAME_SERVICE_VISIT_DB_MASTER_TYPE_ID + " TEXT" + " )";

    private static final String SQL_DELETE_SERVICE_VISIT_TYPE = "DROP TABLE IF EXISTS "
            + ServiceVisitType.TABLE_NAME;

    private static final String SQL_CREATE_SERVICE_VISIT_WORK_STATUS = "CREATE TABLE "
            + ServiceVisitWorkStatus.TABLE_NAME + " (" + ServiceVisitWorkStatus._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ServiceVisitWorkStatus.COLUMN_NAME_SERVICE_VISIT_WORK_STATUS_ID + " TEXT,"
            + ServiceVisitWorkStatus.COLUMN_NAME_SERVICE_VISIT_WORK_STATUS_DESCRIPTION + " TEXT,"
            + ServiceVisitWorkStatus.COLUMN_NAME_SERVICE_VISIT_WORK_STATUS_DB_MASTER_ID + " TEXT" + " )";

    private static final String SQL_DELETE_SERVICE_VISIT_WORK_STATUS = "DROP TABLE IF EXISTS "
            + ServiceVisitWorkStatus.TABLE_NAME;

    private static final String SQL_CREATE_PRODUCT_INSTALLATION_DETAILS = "CREATE TABLE "
            + ProductInstallationDtls.TABLE_NAME + " (" + ProductInstallationDtls._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ProductInstallationDtls.COLUMN_NAME_INSTALLATION_ACCOUNTM_ID + " TEXT,"
            + ProductInstallationDtls.COLUMN_NAME_INSTALLATION_COMMISSIONING_TRANS_ID + " TEXT,"
            + ProductInstallationDtls.COLUMN_NAME_INSTALLATION_CONTRACT_SUMMARY_ID + " TEXT,"
            + ProductInstallationDtls.COLUMN_NAME_INSTALLATION_PRINCIPAL + " TEXT,"
            + ProductInstallationDtls.COLUMN_NAME_INSTALLATION_PRODUCT_PART_NO + " TEXT,"
            + ProductInstallationDtls.COLUMN_NAME_INSTALLATION_PART_NO + " TEXT,"
            + ProductInstallationDtls.COLUMN_NAME_INSTALLATION_SL_NO + " TEXT,"
            + ProductInstallationDtls.COLUMN_NAME_INSTALLATION_CONTRACT_TYPE + " TEXT,"
            + ProductInstallationDtls.COLUMN_NAME_INSTALLATION_FROM_DATE + " TEXT,"
            + ProductInstallationDtls.COLUMN_NAME_INSTALLATION_TO_DATE + " TEXT,"
            + ProductInstallationDtls.COLUMN_NAME_INSTALLATION_PRODUCT_TYPE_ID + " TEXT" + " )";

    private static final String SQL_DELETE_PRODUCT_INSTALLATION_DETAILS = "DROP TABLE IF EXISTS "
            + ProductInstallationDtls.TABLE_NAME;

    private static final String SQL_CREATE_SERVICE_VISIT = "CREATE TABLE "
            + ServiceVisit.TABLE_NAME + " (" + ServiceVisit._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ServiceVisit.COLUMN_NAME_EMPLOYEE_ID + " TEXT," //1
            + ServiceVisit.COLUMN_NAME_CO_EMPLOYEE_ID + " TEXT," //2
            + ServiceVisit.COLUMN_NAME_IN_DATE + " TEXT," //3
            + ServiceVisit.COLUMN_NAME_OUT_DATE + " TEXT," //4
            + ServiceVisit.COLUMN_NAME_ACCOUNTM_ID + " TEXT," //5
            + ServiceVisit.COLUMN_NAME_COMPANY_NAME + " TEXT," //6
            + ServiceVisit.COLUMN_NAME_ADDRESS + " TEXT," //7
            + ServiceVisit.COLUMN_NAME_PINCODE + " TEXT," //8
            + ServiceVisit.COLUMN_NAME_DISTRICT_ID + " TEXT," //9
            + ServiceVisit.COLUMN_NAME_VISIT_TYPE + " TEXT," //10
            + ServiceVisit.COLUMN_NAME_PRODUCT + " TEXT," //11
            + ServiceVisit.COLUMN_NAME_PRODUCT_TYPE + " TEXT," //12
            + ServiceVisit.COLUMN_NAME_MACHINE_SERIAL_NO + " TEXT," //13
            + ServiceVisit.COLUMN_NAME_PART_NO + " TEXT," //14
            + ServiceVisit.COLUMN_NAME_CONTRACT_TYPE + " TEXT," //15
            + ServiceVisit.COLUMN_NAME_SERVICE_PERFORMED + " TEXT," //16
            + ServiceVisit.COLUMN_NAME_ACTION_REQUIRED + " TEXT," //17
            + ServiceVisit.COLUMN_NAME_VISIT_SUMMARY + " TEXT," //18
            + ServiceVisit.COLUMN_NAME_WORK_STATUS + " TEXT," //19
            + ServiceVisit.COLUMN_NAME_LATITUDE + " TEXT," //20
            + ServiceVisit.COLUMN_NAME_LONGITUDE + " TEXT," //21
            + ServiceVisit.COLUMN_NAME_STATUS + " TEXT" + " )"; //22

    private static final String SQL_DELETE_SERVICE_VISIT = "DROP TABLE IF EXISTS "
            + ServiceVisit.TABLE_NAME;

    private static final String SQL_CREATE_SERVICE_VISIT_LIST = "CREATE TABLE "
            + ServiceVisitList.TABLE_NAME + " (" + ServiceVisitList._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ServiceVisitList.COLUMN_NAME_SERVICE_VISIT_LIST_CONTRACT_SUMMARY_ID + " TEXT,"
            + ServiceVisitList.COLUMN_NAME_SERVICE_VISIT_LIST_REF_NO + " TEXT,"
            + ServiceVisitList.COLUMN_NAME_SERVICE_VISIT_LIST_REF_DATE + " TEXT,"
            + ServiceVisitList.COLUMN_NAME_PROJECT_TYPE + " TEXT,"
            + ServiceVisitList.COLUMN_NAME_PROJECT_NAME + " TEXT,"
            + ServiceVisitList.COLUMN_NAME_STATUS + " TEXT" + " )";

    private static final String SQL_DELETE_SERVICE_VISIT_LIST = "DROP TABLE IF EXISTS "
            + ServiceVisitList.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SERVICE_VISIT_TYPE);
        db.execSQL(SQL_CREATE_SERVICE_VISIT_WORK_STATUS);
        db.execSQL(SQL_CREATE_PRODUCT_INSTALLATION_DETAILS);
        db.execSQL(SQL_CREATE_SERVICE_VISIT);
        db.execSQL(SQL_CREATE_SERVICE_VISIT_LIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_SERVICE_VISIT_TYPE);
        db.execSQL(SQL_DELETE_SERVICE_VISIT_WORK_STATUS);
        db.execSQL(SQL_DELETE_PRODUCT_INSTALLATION_DETAILS);
        db.execSQL(SQL_DELETE_SERVICE_VISIT);
        db.execSQL(SQL_DELETE_SERVICE_VISIT_LIST);
        onCreate(db);
    }

    //SERVICE VISIT TYPE

    public long insertServiceVisitType(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(ServiceVisitType.COLUMN_NAME_SERVICE_VISIT_TYPE_ID, val1);
        initialValues.put(ServiceVisitType.COLUMN_NAME_SERVICE_VISIT_TYPE_DESCRIPTION, val2);
        initialValues.put(ServiceVisitType.COLUMN_NAME_SERVICE_VISIT_DB_MASTER_TYPE_ID, val3);

        long l = db.insert(ServiceVisitType.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectServiceVisitType() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from " + ServiceVisitType.TABLE_NAME + ";";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor ServiceVisitTypeID(String Type) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + ServiceVisitType.COLUMN_NAME_SERVICE_VISIT_DB_MASTER_TYPE_ID + " from " + ServiceVisitType.TABLE_NAME + " where " + ServiceVisitType.COLUMN_NAME_SERVICE_VISIT_TYPE_DESCRIPTION + "='" + Type
                + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // SERVICE VISIT WORK STATUS

    public long insertServiceVisitWorkStatus(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(ServiceVisitWorkStatus.COLUMN_NAME_SERVICE_VISIT_WORK_STATUS_ID, val1);
        initialValues.put(ServiceVisitWorkStatus.COLUMN_NAME_SERVICE_VISIT_WORK_STATUS_DESCRIPTION, val2);
        initialValues.put(ServiceVisitWorkStatus.COLUMN_NAME_SERVICE_VISIT_WORK_STATUS_DB_MASTER_ID, val3);

        long l = db.insert(ServiceVisitWorkStatus.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectServiceVisitWorkStatus() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from " + ServiceVisitWorkStatus.TABLE_NAME + ";";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor ServiceVisitVisitWorkStatusID(String Type) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + ServiceVisitWorkStatus.COLUMN_NAME_SERVICE_VISIT_WORK_STATUS_ID + " from " + ServiceVisitWorkStatus.TABLE_NAME + " where " + ServiceVisitWorkStatus.COLUMN_NAME_SERVICE_VISIT_WORK_STATUS_DESCRIPTION + "='" + Type
                + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // PRODUCT INSTALLATION DETAILS

    public long insertProductInstallationDtls(String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9, String val10, String val11) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(ProductInstallationDtls.COLUMN_NAME_INSTALLATION_ACCOUNTM_ID, val1);
        initialValues.put(ProductInstallationDtls.COLUMN_NAME_INSTALLATION_COMMISSIONING_TRANS_ID, val2);
        initialValues.put(ProductInstallationDtls.COLUMN_NAME_INSTALLATION_CONTRACT_SUMMARY_ID, val3);
        initialValues.put(ProductInstallationDtls.COLUMN_NAME_INSTALLATION_PRINCIPAL, val4);
        initialValues.put(ProductInstallationDtls.COLUMN_NAME_INSTALLATION_PRODUCT_PART_NO, val5);
        initialValues.put(ProductInstallationDtls.COLUMN_NAME_INSTALLATION_PART_NO, val6);
        initialValues.put(ProductInstallationDtls.COLUMN_NAME_INSTALLATION_SL_NO, val7);
        initialValues.put(ProductInstallationDtls.COLUMN_NAME_INSTALLATION_CONTRACT_TYPE, val8);
        initialValues.put(ProductInstallationDtls.COLUMN_NAME_INSTALLATION_FROM_DATE, val9);
        initialValues.put(ProductInstallationDtls.COLUMN_NAME_INSTALLATION_TO_DATE, val10);
        initialValues.put(ProductInstallationDtls.COLUMN_NAME_INSTALLATION_PRODUCT_TYPE_ID, val11);

        long l = db.insert(ProductInstallationDtls.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectProductInstallationDtls(String AccountM) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from " + ProductInstallationDtls.TABLE_NAME + " WHERE " + ProductInstallationDtls.COLUMN_NAME_INSTALLATION_ACCOUNTM_ID + "='" + AccountM + "' ;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectProduct() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from " + ProductInstallationDtls.TABLE_NAME + " ;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectProductInstallationBasedID(String ID) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from " + ProductInstallationDtls.TABLE_NAME + " WHERE " + ProductInstallationDtls._ID + "=" + ID + " ;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    //    SERVICE VISIT ADD

    public long insertServiceVisit(String val1, String val2, String val3, String val4, String val5, String val6, String val7,
                                   String val8, String val9, String val10, String val11, String val12, String val13, String val14,
                                   String val15, String val16, String val17, String val18, String val19, String val20, String val21,
                                   String val22) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(ServiceVisit.COLUMN_NAME_EMPLOYEE_ID, val1);
        initialValues.put(ServiceVisit.COLUMN_NAME_CO_EMPLOYEE_ID, val2);
        initialValues.put(ServiceVisit.COLUMN_NAME_IN_DATE, val3);
        initialValues.put(ServiceVisit.COLUMN_NAME_OUT_DATE, val4);
        initialValues.put(ServiceVisit.COLUMN_NAME_ACCOUNTM_ID, val5);
        initialValues.put(ServiceVisit.COLUMN_NAME_COMPANY_NAME, val6);
        initialValues.put(ServiceVisit.COLUMN_NAME_ADDRESS, val7);
        initialValues.put(ServiceVisit.COLUMN_NAME_PINCODE, val8);
        initialValues.put(ServiceVisit.COLUMN_NAME_DISTRICT_ID, val9);
        initialValues.put(ServiceVisit.COLUMN_NAME_VISIT_TYPE, val10);
        initialValues.put(ServiceVisit.COLUMN_NAME_PRODUCT, val11);
        initialValues.put(ServiceVisit.COLUMN_NAME_PRODUCT_TYPE, val12);
        initialValues.put(ServiceVisit.COLUMN_NAME_MACHINE_SERIAL_NO, val13);
        initialValues.put(ServiceVisit.COLUMN_NAME_PART_NO, val14);
        initialValues.put(ServiceVisit.COLUMN_NAME_CONTRACT_TYPE, val15);
        initialValues.put(ServiceVisit.COLUMN_NAME_SERVICE_PERFORMED, val16);
        initialValues.put(ServiceVisit.COLUMN_NAME_ACTION_REQUIRED, val17);
        initialValues.put(ServiceVisit.COLUMN_NAME_VISIT_SUMMARY, val18);
        initialValues.put(ServiceVisit.COLUMN_NAME_WORK_STATUS, val19);
        initialValues.put(ServiceVisit.COLUMN_NAME_LATITUDE, val20);
        initialValues.put(ServiceVisit.COLUMN_NAME_LONGITUDE, val21);
        initialValues.put(ServiceVisit.COLUMN_NAME_STATUS, val22);

        long l = db.insert(ServiceVisit.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectServiceVisitToSync() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from " + ServiceVisit.TABLE_NAME + ";";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void ServiceVisitStatusUpdate(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ServiceVisit.COLUMN_NAME_STATUS, "Y");
        System.out.print(val1);

        sqdb.update(ServiceVisit.TABLE_NAME, values, "_id=" + val1, null);
    }

    public Cursor selectServiceVisit() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + ServiceVisit.TABLE_NAME + " WHERE " + ServiceVisit.COLUMN_NAME_STATUS + " ='N'";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectUpStatusServiceVisit() {

        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM " + ServiceVisit.TABLE_NAME + " WHERE " + ServiceVisit.COLUMN_NAME_STATUS + " ='N'", null);
        return cursor;
    }

    //    SERVICE VISIT ADD

    public long insertServiceVisitList(String val1, String val2, String val3, String val4, String val5, String val6) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(ServiceVisitList.COLUMN_NAME_SERVICE_VISIT_LIST_CONTRACT_SUMMARY_ID, val1);
        initialValues.put(ServiceVisitList.COLUMN_NAME_SERVICE_VISIT_LIST_REF_NO, val2);
        initialValues.put(ServiceVisitList.COLUMN_NAME_SERVICE_VISIT_LIST_REF_DATE, val3);
        initialValues.put(ServiceVisitList.COLUMN_NAME_PROJECT_TYPE, val4);
        initialValues.put(ServiceVisitList.COLUMN_NAME_PROJECT_NAME, val5);
        initialValues.put(ServiceVisitList.COLUMN_NAME_STATUS, val6);

        long l = db.insert(ServiceVisitList.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public int deleteServiceVisitList(long id) {
        return getWritableDatabase().delete(ServiceVisitList.TABLE_NAME, ServiceVisitList._ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Cursor ServiceVisitListForSync(String companyName, String Type) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + ServiceVisitList._ID + " , " + ServiceVisitList.COLUMN_NAME_SERVICE_VISIT_LIST_CONTRACT_SUMMARY_ID + "," + ServiceVisitList.COLUMN_NAME_SERVICE_VISIT_LIST_REF_NO + " , " + ServiceVisitList.COLUMN_NAME_SERVICE_VISIT_LIST_REF_DATE + " , " + ServiceVisitList.COLUMN_NAME_PROJECT_TYPE + " , " + ServiceVisitList.COLUMN_NAME_PROJECT_NAME + " , " + ServiceVisitList.COLUMN_NAME_STATUS + " from " + ServiceVisitList.TABLE_NAME + " where " + ServiceVisitList.COLUMN_NAME_PROJECT_NAME + " = '" + companyName + "' and " + ServiceVisitList.COLUMN_NAME_PROJECT_TYPE + " = '" + Type + "' and " + ServiceVisitList.COLUMN_NAME_STATUS + " = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void ServiceVisitListStatusUpdate(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ServiceVisitList.COLUMN_NAME_STATUS, "Y");
        System.out.print(val1);

        sqdb.update(ServiceVisitList.TABLE_NAME, values, "_id=" + val1, null);
    }


    public void deleteServiceVisitTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ServiceVisitType.TABLE_NAME, null, null);
        db.delete(ServiceVisitWorkStatus.TABLE_NAME, null, null);
        db.delete(ProductInstallationDtls.TABLE_NAME, null, null);
        db.close();
    }


}

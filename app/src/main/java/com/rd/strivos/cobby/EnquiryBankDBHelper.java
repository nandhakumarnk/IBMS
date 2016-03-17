package com.rd.strivos.cobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rd.strivos.cobby.EnquiryBankDBFields.EnquirySource;
import com.rd.strivos.cobby.EnquiryBankDBFields.MediaMediator;
import com.rd.strivos.cobby.EnquiryBankDBFields.EnquiryBank;

import java.sql.SQLException;

/**
 * Created by COBURG DESIGN on 01-02-2016.
 */
public class EnquiryBankDBHelper extends SQLiteOpenHelper {

    public EnquiryBankDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "enquirybanklive.db";

    private static final String SQL_CREATE_ENQUIRY_SOURCE = "CREATE TABLE "
            + EnquirySource.TABLE_NAME + " (" + EnquirySource._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EnquirySource.COLUMN_NAME_ENQUIRY_SOURCE_ID + " TEXT,"
            + EnquirySource.COLUMN_NAME_ENQUIRY_SOURCE_DESCRIPTION + " TEXT,"
            + EnquirySource.COLUMN_NAME_ENQUIRY_SOURCE_MASTER_TYPE_ID + " TEXT" + " )";

    private static final String SQL_DELETE_ENQUIRY_SOURCE = "DROP TABLE IF EXISTS "
            + EnquirySource.TABLE_NAME;

    private static final String SQL_CREATE_MEDIA_MEDIATOR = "CREATE TABLE "
            + MediaMediator.TABLE_NAME + " (" + MediaMediator._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MediaMediator.COLUMN_NAME_MEDIA_MEDIATOR_ID + " TEXT,"
            + MediaMediator.COLUMN_NAME_MEDIA_MEDIATOR_DESCRIPTION + " TEXT,"
            + MediaMediator.COLUMN_NAME_MEDIA_MEDIATOR_MASTER_TYPE_ID + " TEXT,"
            + MediaMediator.COLUMN_NAME_MEDIA_MEDIATOR_PARENT_MASTERM_ID + " TEXT" + " )";

    private static final String SQL_DELETE_MEDIA_MEDIATOR = "DROP TABLE IF EXISTS "
            + MediaMediator.TABLE_NAME;

    private static final String SQL_CREATE_ENQUIRY_BANK = "CREATE TABLE "
            + EnquiryBank.TABLE_NAME + " (" + EnquiryBank._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_ENQUIRY_NO + " TEXT," //1
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_ENQUIRY_DATE + " TEXT," //2
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_REFERENCE_NO + " TEXT," //3
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_REFERENCE_DATE + " TEXT," //4
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_ENQUIRY_RECEIVED_BY + " TEXT," //5
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_ENQUIRY_SOURCE + " TEXT," //6
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_MEDIAOR_MEDIATOR + " TEXT," //7
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_ENQUIRY_PROVIDER_NAME + " TEXT," //8
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_CUSTOMER + " TEXT," //9
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_ADDRESS + " TEXT," //10
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_PINCODE + " TEXT," //11
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_DISTRICT + " TEXT," //12
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_CONTACT_PERSON + " TEXT," //13
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_CONTACT_PERSON_NO + " TEXT," //14
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_STD_NO + " TEXT," //15
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_TELE_PHONE_NO + " TEXT," //16
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_WEBSITE + " TEXT," //17
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_EMAIL + " TEXT," //18
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_PRODUCT_REQUIREMENT + " TEXT," //19
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_REQ_ON_OR_BEFORE + " TEXT," //20
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_ENQUIRY_EXECUTOR + " TEXT," //21
            + EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_REMARKS + " TEXT," //22
            + EnquiryBank.COLUMN_NAME_PROJECT_TYPE + " TEXT," //23
            + EnquiryBank.COLUMN_NAME_PROJECT_NAME + " TEXT," //24
            + EnquiryBank.COLUMN_NAME_STATUS + " TEXT," //25
            + EnquiryBank.COLUMN_NAME_LATITUDE + " TEXT," //26
            + EnquiryBank.COLUMN_NAME_LONGITUDE + " TEXT" + " )"; //27

    private static final String SQL_DELETE_ENQUIRY_BANK = "DROP TABLE IF EXISTS "
            + EnquiryBank.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENQUIRY_SOURCE);
        db.execSQL(SQL_CREATE_MEDIA_MEDIATOR);
        db.execSQL(SQL_CREATE_ENQUIRY_BANK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENQUIRY_SOURCE);
        db.execSQL(SQL_DELETE_MEDIA_MEDIATOR);
        db.execSQL(SQL_DELETE_ENQUIRY_BANK);
        onCreate(db);
    }

    /**
     * INSERT ENQUIRY SOURCE LOOKUP
     */

    public long insertEnquirySource(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(EnquirySource.COLUMN_NAME_ENQUIRY_SOURCE_ID, val1);
        initialValues.put(EnquirySource.COLUMN_NAME_ENQUIRY_SOURCE_DESCRIPTION, val2);
        initialValues.put(EnquirySource.COLUMN_NAME_ENQUIRY_SOURCE_MASTER_TYPE_ID, val3);

        long l = db.insert(EnquirySource.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public Cursor getEnquirySource() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from " + EnquirySource.TABLE_NAME + ";";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getEnquirySourceIDValue(String Description) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + EnquirySource.COLUMN_NAME_ENQUIRY_SOURCE_ID + " from " + EnquirySource.TABLE_NAME + " where " + EnquirySource.COLUMN_NAME_ENQUIRY_SOURCE_DESCRIPTION + " = '" + Description + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    /**
     * INSERT MEDIA MEDIATOR LOOKUP
     */

    public long insertMediaMediator(String val1, String val2, String val3, String val4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(MediaMediator.COLUMN_NAME_MEDIA_MEDIATOR_ID, val1);
        initialValues.put(MediaMediator.COLUMN_NAME_MEDIA_MEDIATOR_DESCRIPTION, val2);
        initialValues.put(MediaMediator.COLUMN_NAME_MEDIA_MEDIATOR_MASTER_TYPE_ID, val3);
        initialValues.put(MediaMediator.COLUMN_NAME_MEDIA_MEDIATOR_PARENT_MASTERM_ID, val4);

        long l = db.insert(MediaMediator.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public Cursor getMediaMediator(String ID) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from " + MediaMediator.TABLE_NAME + " where " + MediaMediator.COLUMN_NAME_MEDIA_MEDIATOR_PARENT_MASTERM_ID + " = '" + ID + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getMediaMediatorIDValue(String Description) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + MediaMediator.COLUMN_NAME_MEDIA_MEDIATOR_ID + " from " + MediaMediator.TABLE_NAME + " where " + MediaMediator.COLUMN_NAME_MEDIA_MEDIATOR_DESCRIPTION + " = '" + Description + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    /**
     * INSERT ENQUIRY BANK
     */

    public long insertEnquiryBank(String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9, String val10, String val11, String val12, String val13, String val14, String val15, String val16, String val17, String val18, String val19, String val20, String val21, String val22, String val23, String val24, String val25, String val26, String val27) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_ENQUIRY_NO, val1);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_ENQUIRY_DATE, val2);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_REFERENCE_NO, val3);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_REFERENCE_DATE, val4);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_ENQUIRY_RECEIVED_BY, val5);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_ENQUIRY_SOURCE, val6);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_MEDIAOR_MEDIATOR, val7);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_ENQUIRY_PROVIDER_NAME, val8);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_CUSTOMER, val9);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_ADDRESS, val10);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_PINCODE, val11);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_DISTRICT, val12);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_CONTACT_PERSON, val13);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_CONTACT_PERSON_NO, val14);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_STD_NO, val15);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_TELE_PHONE_NO, val16);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_WEBSITE, val17);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_EMAIL, val18);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_PRODUCT_REQUIREMENT, val19);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_REQ_ON_OR_BEFORE, val20);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_ENQUIRY_EXECUTOR, val21);
        initialValues.put(EnquiryBank.COLUMN_NAME_ENQUIRY_BANK_REMARKS, val22);
        initialValues.put(EnquiryBank.COLUMN_NAME_PROJECT_TYPE, val23);
        initialValues.put(EnquiryBank.COLUMN_NAME_PROJECT_NAME, val24);
        initialValues.put(EnquiryBank.COLUMN_NAME_STATUS, val25);
        initialValues.put(EnquiryBank.COLUMN_NAME_LATITUDE, val26);
        initialValues.put(EnquiryBank.COLUMN_NAME_LONGITUDE, val27);


        long l = db.insert(EnquiryBank.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public int deleteEnquiryBank(long id) {
        return getWritableDatabase().delete(EnquiryBank.TABLE_NAME, EnquiryBank._ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Cursor getEnquiryBankList(String ProjectName, String ProjectType) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + EnquiryBank.TABLE_NAME + " WHERE " + EnquiryBank.COLUMN_NAME_PROJECT_NAME + "='" + ProjectName + "' and " + EnquiryBank.COLUMN_NAME_PROJECT_TYPE + " = '" + ProjectType + "' and " + EnquiryBank.COLUMN_NAME_STATUS + " = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor EnquiryBankForSync(String ProjectName, String ProjectType) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + EnquiryBank.TABLE_NAME + " WHERE " + EnquiryBank.COLUMN_NAME_PROJECT_NAME + "='" + ProjectName + "' and " + EnquiryBank.COLUMN_NAME_PROJECT_TYPE + " = '" + ProjectType + "' and " + EnquiryBank.COLUMN_NAME_STATUS + " = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectEnquiryBank() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + EnquiryBank.TABLE_NAME + " WHERE " + EnquiryBank.COLUMN_NAME_STATUS + " ='N'";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void EnquiryBankStatusUpdate(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EnquiryBank.COLUMN_NAME_STATUS, "Y");
        System.out.print(val1);

        sqdb.update(EnquiryBank.TABLE_NAME, values, "_id=" + val1, null);
    }

    public Cursor selectUpEnquiryBank() {

        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM " + EnquiryBank.TABLE_NAME + " WHERE " + EnquiryBank.COLUMN_NAME_STATUS + " ='N'", null);
        return cursor;
    }


    public void deleteEnquiryBank() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EnquirySource.TABLE_NAME, null, null);
        db.delete(EnquirySource.TABLE_NAME, null, null);
        db.close();
    }
}

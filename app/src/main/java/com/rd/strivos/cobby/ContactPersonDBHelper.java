package com.rd.strivos.cobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rd.strivos.cobby.ContactPersonDBFields.ContactPerson;

/**
 * Created by COBURG DESIGN on 10-02-2016.
 */
public class ContactPersonDBHelper extends SQLiteOpenHelper {

    public ContactPersonDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contactpersonlive";

    private static final String SQL_CREATE_CONATCT_PERSON = "CREATE TABLE "
            + ContactPerson.TABLE_NAME + " (" + ContactPerson._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ContactPerson.COLUMN_NAME_CONTACT_PERSON_ID + " TEXT,"
            + ContactPerson.COLUMN_NAME_CONTACT_PERSON_ACCOUNTM_ID + " TEXT,"
            + ContactPerson.COLUMN_NAME_CONTACT_PERSON_CONTACT_NAME + " TEXT,"
            + ContactPerson.COLUMN_NAME_CONTACT_PERSON_MOBILENO + " TEXT,"
            + ContactPerson.COLUMN_NAME_CONTACT_PERSON_EMAIL + " TEXT,"
            + ContactPerson.COLUMN_NAME_CONTACT_PERSON_DEPARTMENT + " TEXT,"
            + ContactPerson.COLUMN_NAME_CONTACT_PERSON_DESIGNATION + " TEXT,"
            + ContactPerson.COLUMN_NAME_CONTACT_PERSON_STATUSM_ID + " TEXT" + " )";

    private static final String SQL_DELETE_CONTACT_PERSON = "DROP TABLE IF EXISTS "
            + ContactPerson.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CONATCT_PERSON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_CONTACT_PERSON);
        onCreate(db);
    }

    // INSERT CONATCT PERSON DETAILS

    public long insertContactPerson(String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(ContactPerson.COLUMN_NAME_CONTACT_PERSON_ID, val1);
        initialValues.put(ContactPerson.COLUMN_NAME_CONTACT_PERSON_ACCOUNTM_ID, val2);
        initialValues.put(ContactPerson.COLUMN_NAME_CONTACT_PERSON_CONTACT_NAME, val3);
        initialValues.put(ContactPerson.COLUMN_NAME_CONTACT_PERSON_MOBILENO, val4);
        initialValues.put(ContactPerson.COLUMN_NAME_CONTACT_PERSON_EMAIL, val5);
        initialValues.put(ContactPerson.COLUMN_NAME_CONTACT_PERSON_DEPARTMENT, val6);
        initialValues.put(ContactPerson.COLUMN_NAME_CONTACT_PERSON_DESIGNATION, val7);
        initialValues.put(ContactPerson.COLUMN_NAME_CONTACT_PERSON_STATUSM_ID, val8);

        long l = db.insert(ContactPerson.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectContactPerson() {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from " + ContactPerson.TABLE_NAME + ";";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectCPersonBasedAccountM(String AccountM) {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from " + ContactPerson.TABLE_NAME + " Where " + ContactPerson.COLUMN_NAME_CONTACT_PERSON_ACCOUNTM_ID + " = '" + AccountM + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectCPersonBasedPerson(String ContactP) {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from " + ContactPerson.TABLE_NAME + " where " + ContactPerson.COLUMN_NAME_CONTACT_PERSON_CONTACT_NAME + " = '" + ContactP + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void deleteContactPerson() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ContactPerson.TABLE_NAME, null, null);
        db.close();
    }
}

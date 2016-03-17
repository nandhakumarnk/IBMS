package com.rd.strivos.cobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rd.strivos.cobby.PersonDBFields.PersonAdd;

/**
 * Created by COBURG DESIGN on 07-01-2016.
 */
public class PersonDBHelper extends SQLiteOpenHelper {

    public PersonDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "personlive.db";

    private static final String SQL_CREATE_ADD_PERSON = "CREATE TABLE "
            + PersonAdd.TABLE_NAME + " (" + PersonAdd._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PersonAdd.COLUMN_NAME_PROJECT_ID + " TEXT,"
            + PersonAdd.COLUMN_NAME_PERSON_NAME + " TEXT,"
            + PersonAdd.COLUMN_NAME_PERSON_DESIGNATION + " TEXT,"
            + PersonAdd.COLUMN_NAME_PERSON_MOBILE_NUMBER + " TEXT,"
            + PersonAdd.COLUMN_NAME_PERSON_EMAIL + " TEXT,"
            + PersonAdd.COLUMN_NAME_PROJECT_TYPE + " TEXT,"
            + PersonAdd.COLUMN_NAME_STATUS + " TEXT" + " )";

    private static final String SQL_DELETE_ADD_PERSON = "DROP TABLE IF EXISTS "
            + PersonAdd.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ADD_PERSON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ADD_PERSON);
        onCreate(db);
    }

    public long insertAddMorePerson(String val1, String val2, String val3, String val4, String val5, String val6, String val7) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(PersonAdd.COLUMN_NAME_PROJECT_ID, val1);
        initialValues.put(PersonAdd.COLUMN_NAME_PERSON_NAME, val2);
        initialValues.put(PersonAdd.COLUMN_NAME_PERSON_DESIGNATION, val3);
        initialValues.put(PersonAdd.COLUMN_NAME_PERSON_MOBILE_NUMBER, val4);
        initialValues.put(PersonAdd.COLUMN_NAME_PERSON_EMAIL, val5);
        initialValues.put(PersonAdd.COLUMN_NAME_PROJECT_TYPE, val6);
        initialValues.put(PersonAdd.COLUMN_NAME_STATUS, val7);

        long l = db.insert(PersonAdd.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public void updateAddMorePerson(long val1, String val2, String val3, String val4, String val5, String val6, String val7) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PersonAdd.COLUMN_NAME_PROJECT_ID, val2);
        values.put(PersonAdd.COLUMN_NAME_PERSON_NAME, val3);
        values.put(PersonAdd.COLUMN_NAME_PERSON_DESIGNATION, val4);
        values.put(PersonAdd.COLUMN_NAME_PERSON_MOBILE_NUMBER, val5);
        values.put(PersonAdd.COLUMN_NAME_PERSON_EMAIL, val6);
        values.put(PersonAdd.COLUMN_NAME_PROJECT_TYPE, val7);

        sqdb.update(PersonAdd.TABLE_NAME, values, "_id=" + val1, null);
    }

    public Cursor getPersonList(String ProjectName, String ProjectType) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + PersonAdd.TABLE_NAME + " WHERE " + PersonAdd.COLUMN_NAME_PROJECT_ID + "='" + ProjectName + "' and " + PersonAdd.COLUMN_NAME_PROJECT_TYPE + " = '" + ProjectType + "' and " +PersonAdd.COLUMN_NAME_STATUS+" = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getPersonListFull(int id) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + PersonAdd.TABLE_NAME + " WHERE " + PersonAdd._ID + "=" + id + "";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectMorePersonForSync(String companyName, String Type) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        //String fetch = "Select _id,Projectid,NAME,Designation,PHONE,Email,ProjectType,Status from tblAddMorePerson where Projectid ='" + companyName + "' and ProjectType = '" + Type + "' and Status='N';";
        String fetch = "Select "+PersonAdd._ID +" , " +PersonAdd.COLUMN_NAME_PROJECT_ID+","+PersonAdd.COLUMN_NAME_PERSON_NAME+" , "+PersonAdd.COLUMN_NAME_PERSON_DESIGNATION+" , "+PersonAdd.COLUMN_NAME_PERSON_MOBILE_NUMBER+" , "+PersonAdd.COLUMN_NAME_PERSON_EMAIL+" , "+PersonAdd.COLUMN_NAME_PROJECT_TYPE+" , "+PersonAdd.COLUMN_NAME_STATUS+" from "+PersonAdd.TABLE_NAME+" where "+PersonAdd.COLUMN_NAME_PROJECT_ID+ " = '" + companyName + "' and "+ PersonAdd.COLUMN_NAME_PROJECT_TYPE+" = '" + Type + "' and "+PersonAdd.COLUMN_NAME_STATUS+" = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public int deletePerson(long id) {
        return getWritableDatabase().delete(PersonAdd.TABLE_NAME, PersonAdd._ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void AddMorePersonStatusUpdate(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PersonAdd.COLUMN_NAME_STATUS, "Y");
        System.out.print(val1);
        sqdb.update(PersonAdd.TABLE_NAME, values, "_id=" + val1, null);
    }
}

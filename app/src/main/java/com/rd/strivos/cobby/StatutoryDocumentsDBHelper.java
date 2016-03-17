package com.rd.strivos.cobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rd.strivos.cobby.StatutoryDocumentsDBFields.StatutoryDocumentsAdd;

import java.sql.SQLException;

/**
 * Created by COBURG DESIGN on 23-01-2016.
 */
public class StatutoryDocumentsDBHelper extends SQLiteOpenHelper {

    public StatutoryDocumentsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "statutorydocumentslive.db";

    private static final String SQL_CREATE_STATUTORY_DOCUMENTS = "CREATE TABLE "
            + StatutoryDocumentsAdd.TABLE_NAME + " (" + StatutoryDocumentsAdd._ID //0
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + StatutoryDocumentsAdd.COLUMN_NAME_VENDOR_NAME + " TEXT," //1
            + StatutoryDocumentsAdd.COLUMN_NAME_REF_NO + " TEXT," //2
            + StatutoryDocumentsAdd.COLUMN_NAME_REF_DATE + " TEXT," //3
            + StatutoryDocumentsAdd.COLUMN_NAME_AMOUNT + " TEXT," //4
            + StatutoryDocumentsAdd.COLUMN_NAME_REF_DOC_NO + " TEXT," //5
            + StatutoryDocumentsAdd.COLUMN_NAME_REF_DOC_DATE + " TEXT," //6
            + StatutoryDocumentsAdd.COLUMN_NAME_REMARKS + " TEXT," //7
            + StatutoryDocumentsAdd.COLUMN_NAME_PROJECT_TYPE + " TEXT," //8
            + StatutoryDocumentsAdd.COLUMN_NAME_PROJECT_NAME + " TEXT," //9
            + StatutoryDocumentsAdd.COLUMN_NAME_STATUS + " TEXT" + " )"; //10

    private static final String SQL_DELETE_STATUTORY_DOCUMENTS = "DROP TABLE IF EXISTS "
            + StatutoryDocumentsAdd.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_STATUTORY_DOCUMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_STATUTORY_DOCUMENTS);
        onCreate(db);
    }

    public long insertStatutoryDocuments(String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9, String val10) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(StatutoryDocumentsAdd.COLUMN_NAME_VENDOR_NAME, val1);
        initialValues.put(StatutoryDocumentsAdd.COLUMN_NAME_REF_NO, val2);
        initialValues.put(StatutoryDocumentsAdd.COLUMN_NAME_REF_DATE, val3);
        initialValues.put(StatutoryDocumentsAdd.COLUMN_NAME_AMOUNT, val4);
        initialValues.put(StatutoryDocumentsAdd.COLUMN_NAME_REF_DOC_NO, val5);
        initialValues.put(StatutoryDocumentsAdd.COLUMN_NAME_REF_DOC_DATE, val6);
        initialValues.put(StatutoryDocumentsAdd.COLUMN_NAME_REMARKS, val7);
        initialValues.put(StatutoryDocumentsAdd.COLUMN_NAME_PROJECT_TYPE, val8);
        initialValues.put(StatutoryDocumentsAdd.COLUMN_NAME_PROJECT_NAME, val9);
        initialValues.put(StatutoryDocumentsAdd.COLUMN_NAME_STATUS, val10);

        long l = db.insert(StatutoryDocumentsAdd.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public void updateStatutoryDocuments(long val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9, String val10) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StatutoryDocumentsAdd.COLUMN_NAME_VENDOR_NAME, val2);
        values.put(StatutoryDocumentsAdd.COLUMN_NAME_REF_NO, val3);
        values.put(StatutoryDocumentsAdd.COLUMN_NAME_REF_DATE, val4);
        values.put(StatutoryDocumentsAdd.COLUMN_NAME_AMOUNT, val5);
        values.put(StatutoryDocumentsAdd.COLUMN_NAME_REF_DOC_NO, val6);
        values.put(StatutoryDocumentsAdd.COLUMN_NAME_REF_DOC_DATE, val7);
        values.put(StatutoryDocumentsAdd.COLUMN_NAME_REMARKS, val8);
        values.put(StatutoryDocumentsAdd.COLUMN_NAME_PROJECT_TYPE, val9);
        values.put(StatutoryDocumentsAdd.COLUMN_NAME_PROJECT_NAME, val10);

        sqdb.update(StatutoryDocumentsAdd.TABLE_NAME, values, "_id=" + val1, null);
    }

    public int deleteStatutoryDocuments(long id) {
        return getWritableDatabase().delete(StatutoryDocumentsAdd.TABLE_NAME, StatutoryDocumentsAdd._ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Cursor getStatutoryDocumentsList(String ProjectName, String ProjectType) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + StatutoryDocumentsAdd.TABLE_NAME + " WHERE " + StatutoryDocumentsAdd.COLUMN_NAME_PROJECT_NAME + "='" + ProjectName + "' and " + StatutoryDocumentsAdd.COLUMN_NAME_PROJECT_TYPE + " = '" + ProjectType + "' and " + StatutoryDocumentsAdd.COLUMN_NAME_STATUS + " = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getStatutoryDocumentsListFull(int id) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + StatutoryDocumentsAdd.TABLE_NAME + " WHERE " + StatutoryDocumentsAdd._ID + "=" + id + "";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor StatutoryDocumentsForSync(String companyName, String Type) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + StatutoryDocumentsAdd._ID + " , " + StatutoryDocumentsAdd.COLUMN_NAME_VENDOR_NAME + "," + StatutoryDocumentsAdd.COLUMN_NAME_REF_NO + " , " + StatutoryDocumentsAdd.COLUMN_NAME_REF_DATE + " , " + StatutoryDocumentsAdd.COLUMN_NAME_AMOUNT + " , " + StatutoryDocumentsAdd.COLUMN_NAME_REF_DOC_NO + " , " + StatutoryDocumentsAdd.COLUMN_NAME_REF_DOC_DATE + " , " + StatutoryDocumentsAdd.COLUMN_NAME_REMARKS + " from " + StatutoryDocumentsAdd.TABLE_NAME + " where " + StatutoryDocumentsAdd.COLUMN_NAME_PROJECT_NAME + " = '" + companyName + "' and " + StatutoryDocumentsAdd.COLUMN_NAME_PROJECT_TYPE + " = '" + Type + "' and " + StatutoryDocumentsAdd.COLUMN_NAME_STATUS + " = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void StatutoryDocumentsStatusUpdate(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StatutoryDocumentsAdd.COLUMN_NAME_STATUS, "Y");
        System.out.print(val1);

        sqdb.update(StatutoryDocumentsAdd.TABLE_NAME, values, "_id=" + val1, null);
    }
}

package com.rd.strivos.cobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rd.strivos.cobby.PaymentsDBFields.PaymentsAdd;

import java.sql.SQLException;

/**
 * Created by COBURG DESIGN on 22-01-2016.
 */
public class PaymentsDBHelper extends SQLiteOpenHelper {

    public PaymentsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "productslive.db";

    private static final String SQL_CREATE_PAYMENTS = "CREATE TABLE "
            + PaymentsAdd.TABLE_NAME + " (" + PaymentsAdd._ID //0
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PaymentsAdd.COLUMN_NAME_VENDOR_NAME + " TEXT," //1
            + PaymentsAdd.COLUMN_NAME_REF_NO + " TEXT," //2
            + PaymentsAdd.COLUMN_NAME_REF_DATE + " TEXT," //3
            + PaymentsAdd.COLUMN_NAME_AMOUNT + " TEXT," //4
            + PaymentsAdd.COLUMN_NAME_RECD_AMOUNT + " TEXT," //5
            + PaymentsAdd.COLUMN_NAME_REF_DOC_NO + " TEXT," //6
            + PaymentsAdd.COLUMN_NAME_REF_DOC_DATE + " TEXT," //7
            + PaymentsAdd.COLUMN_NAME_PROJECT_TYPE + " TEXT," //8
            + PaymentsAdd.COLUMN_NAME_PROJECT_NAME + " TEXT," //9
            + PaymentsAdd.COLUMN_NAME_STATUS + " TEXT" + " )"; //10

    private static final String SQL_DELETE_PAYMENTS = "DROP TABLE IF EXISTS "
            + PaymentsAdd.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PAYMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PAYMENTS);
        onCreate(db);
    }

    public long insertPayments(String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9, String val10) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(PaymentsAdd.COLUMN_NAME_VENDOR_NAME, val1);
        initialValues.put(PaymentsAdd.COLUMN_NAME_REF_NO, val2);
        initialValues.put(PaymentsAdd.COLUMN_NAME_REF_DATE, val3);
        initialValues.put(PaymentsAdd.COLUMN_NAME_AMOUNT, val4);
        initialValues.put(PaymentsAdd.COLUMN_NAME_RECD_AMOUNT, val5);
        initialValues.put(PaymentsAdd.COLUMN_NAME_REF_DOC_NO, val6);
        initialValues.put(PaymentsAdd.COLUMN_NAME_REF_DOC_DATE, val7);
        initialValues.put(PaymentsAdd.COLUMN_NAME_PROJECT_TYPE, val8);
        initialValues.put(PaymentsAdd.COLUMN_NAME_PROJECT_NAME, val9);
        initialValues.put(PaymentsAdd.COLUMN_NAME_STATUS, val10);

        long l = db.insert(PaymentsAdd.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public void updatePayments(long val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9, String val10) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PaymentsAdd.COLUMN_NAME_VENDOR_NAME, val2);
        values.put(PaymentsAdd.COLUMN_NAME_REF_NO, val3);
        values.put(PaymentsAdd.COLUMN_NAME_REF_DATE, val4);
        values.put(PaymentsAdd.COLUMN_NAME_AMOUNT, val5);
        values.put(PaymentsAdd.COLUMN_NAME_RECD_AMOUNT, val6);
        values.put(PaymentsAdd.COLUMN_NAME_REF_DOC_NO, val7);
        values.put(PaymentsAdd.COLUMN_NAME_REF_DOC_DATE, val8);
        values.put(PaymentsAdd.COLUMN_NAME_PROJECT_TYPE, val9);
        values.put(PaymentsAdd.COLUMN_NAME_PROJECT_NAME, val10);

        sqdb.update(PaymentsAdd.TABLE_NAME, values, "_id=" + val1, null);
    }

    public int deletePayments(long id) {
        return getWritableDatabase().delete(PaymentsAdd.TABLE_NAME, PaymentsAdd._ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Cursor getPaymentsList(String ProjectName, String ProjectType) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + PaymentsAdd.TABLE_NAME + " WHERE " + PaymentsAdd.COLUMN_NAME_PROJECT_NAME + "='" + ProjectName + "' and " + PaymentsAdd.COLUMN_NAME_PROJECT_TYPE + " = '" + ProjectType + "' and " + PaymentsAdd.COLUMN_NAME_STATUS + " = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getPaymentsListFull(int id) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + PaymentsAdd.TABLE_NAME + " WHERE " + PaymentsAdd._ID + "=" + id + "";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor PaymentsForSync(String companyName, String Type) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + PaymentsAdd._ID + " , " + PaymentsAdd.COLUMN_NAME_VENDOR_NAME + "," + PaymentsAdd.COLUMN_NAME_REF_NO + " , " + PaymentsAdd.COLUMN_NAME_REF_DATE + " , " + PaymentsAdd.COLUMN_NAME_AMOUNT + " , " + PaymentsAdd.COLUMN_NAME_RECD_AMOUNT + " , " + PaymentsAdd.COLUMN_NAME_REF_DOC_NO + " , " + PaymentsAdd.COLUMN_NAME_REF_DOC_DATE + " from " + PaymentsAdd.TABLE_NAME + " where " + PaymentsAdd.COLUMN_NAME_PROJECT_NAME + " = '" + companyName + "' and " + PaymentsAdd.COLUMN_NAME_PROJECT_TYPE + " = '" + Type + "' and " + PaymentsAdd.COLUMN_NAME_STATUS + " = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void PaymentsStatusUpdate(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PaymentsAdd.COLUMN_NAME_STATUS, "Y");
        System.out.print(val1);

        sqdb.update(PaymentsAdd.TABLE_NAME, values, "_id=" + val1, null);
    }
}

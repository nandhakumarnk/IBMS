package com.rd.strivos.cobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rd.strivos.cobby.SparesDBFields.SparesAdd;
import com.rd.strivos.cobby.SparesDBFields.SparesLookupType;

import java.sql.SQLException;

/**
 * Created by COBURG DESIGN on 21-01-2016.
 */
public class SparesDBHelper extends SQLiteOpenHelper {

    public SparesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "spareslive.db";

    private static final String SQL_CREATE_SAPRES = "CREATE TABLE "
            + SparesAdd.TABLE_NAME + " (" + SparesAdd._ID //0
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SparesAdd.COLUMN_NAME_PRINCIPAL + " TEXT," //1
            + SparesAdd.COLUMN_NAME_SPARES_ID + " TEXT," //2
            + SparesAdd.COLUMN_NAME_PART_NO + " TEXT," //3
            + SparesAdd.COLUMN_NAME_SHORT_DESCRIPTION + " TEXT," //4
            + SparesAdd.COLUMN_NAME_UOM + " TEXT," //5
            + SparesAdd.COLUMN_NAME_QUANTITY + " TEXT," //6
            + SparesAdd.COLUMN_NAME_PROJECT_TYPE + " TEXT," //7
            + SparesAdd.COLUMN_NAME_PROJECT_NAME + " TEXT," //8
            + SparesAdd.COLUMN_NAME_STATUS + " TEXT" + " )"; //9

    private static final String SQL_DELETE_SPARES = "DROP TABLE IF EXISTS "
            + SparesAdd.TABLE_NAME;

    private static final String SQL_CREATE_SPARES_LOOKUP_TYPE = "CREATE TABLE "
            + SparesLookupType.TABLE_NAME + " (" + SparesLookupType._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SparesLookupType.COLUMN_NAME_PRINCIPAL + " TEXT,"
            + SparesLookupType.COLUMN_NAME_SPARES_ID + " TEXT,"
            + SparesLookupType.COLUMN_NAME_PART_NO + " TEXT,"
            + SparesLookupType.COLUMN_NAME_SHORT_DESCRIPTION + " TEXT,"
            + SparesLookupType.COLUMN_NAME_UOM + " TEXT" + " )";

    private static final String SQL_DELETE_SPARES_LOOKUP_TYPE = "DROP TABLE IF EXISTS "
            + SparesLookupType.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SAPRES);
        db.execSQL(SQL_CREATE_SPARES_LOOKUP_TYPE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_SPARES);
        db.execSQL(SQL_CREATE_SPARES_LOOKUP_TYPE);
        onCreate(db);
    }

    public long insertSpares(String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(SparesAdd.COLUMN_NAME_PRINCIPAL, val1);
        initialValues.put(SparesAdd.COLUMN_NAME_SPARES_ID, val2);
        initialValues.put(SparesAdd.COLUMN_NAME_PART_NO, val3);
        initialValues.put(SparesAdd.COLUMN_NAME_SHORT_DESCRIPTION, val4);
        initialValues.put(SparesAdd.COLUMN_NAME_UOM, val5);
        initialValues.put(SparesAdd.COLUMN_NAME_QUANTITY, val6);
        initialValues.put(SparesAdd.COLUMN_NAME_PROJECT_TYPE, val7);
        initialValues.put(SparesAdd.COLUMN_NAME_PROJECT_NAME, val8);
        initialValues.put(SparesAdd.COLUMN_NAME_STATUS, val9);

        long l = db.insert(SparesAdd.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public void updateSpares(long val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SparesAdd.COLUMN_NAME_PRINCIPAL, val2);
        values.put(SparesAdd.COLUMN_NAME_SPARES_ID, val3);
        values.put(SparesAdd.COLUMN_NAME_PART_NO, val4);
        values.put(SparesAdd.COLUMN_NAME_SHORT_DESCRIPTION, val5);
        values.put(SparesAdd.COLUMN_NAME_UOM, val6);
        values.put(SparesAdd.COLUMN_NAME_QUANTITY, val7);
        values.put(SparesAdd.COLUMN_NAME_PROJECT_TYPE, val8);
        values.put(SparesAdd.COLUMN_NAME_PROJECT_NAME, val9);

        sqdb.update(SparesAdd.TABLE_NAME, values, "_id=" + val1, null);
    }

    public int deleteSpares(long id) {
        return getWritableDatabase().delete(SparesAdd.TABLE_NAME, SparesAdd._ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Cursor getSparesList(String ProjectName, String ProjectType) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + SparesAdd.TABLE_NAME + " WHERE " + SparesAdd.COLUMN_NAME_PROJECT_NAME + "='" + ProjectName + "' and " + SparesAdd.COLUMN_NAME_PROJECT_TYPE + " = '" + ProjectType + "' and " + SparesAdd.COLUMN_NAME_STATUS + " = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getSparesListFull(int id) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + SparesAdd.TABLE_NAME + " WHERE " + SparesAdd._ID + "=" + id + "";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor SparesForSync(String companyName, String Type) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + SparesAdd._ID + " , " + SparesAdd.COLUMN_NAME_SPARES_ID + "," + SparesAdd.COLUMN_NAME_UOM + " , " + SparesAdd.COLUMN_NAME_QUANTITY + " from " + SparesAdd.TABLE_NAME + " where " + SparesAdd.COLUMN_NAME_PROJECT_NAME + " = '" + companyName + "' and " + SparesAdd.COLUMN_NAME_PROJECT_TYPE + " = '" + Type + "' and " + SparesAdd.COLUMN_NAME_STATUS + " = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void SparesStatusUpdate(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SparesAdd.COLUMN_NAME_STATUS, "Y");
        System.out.print(val1);

        sqdb.update(SparesAdd.TABLE_NAME, values, "_id=" + val1, null);
    }

    /**
     * INSERT SPARES LOOKUP TYPE
     */

    public long insertSparesLookup(String val1, String val2, String val3,
                                   String val4, String val5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(SparesLookupType.COLUMN_NAME_PRINCIPAL, val1);
        initialValues.put(SparesLookupType.COLUMN_NAME_SPARES_ID, val2);
        initialValues.put(SparesLookupType.COLUMN_NAME_PART_NO, val3);
        initialValues.put(SparesLookupType.COLUMN_NAME_SHORT_DESCRIPTION, val4);
        initialValues.put(SparesLookupType.COLUMN_NAME_UOM, val5);

        long l = db.insert(SparesLookupType.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public void deleteSparesLookupTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SparesLookupType.TABLE_NAME, null, null);
        db.close();
    }

    public Cursor getSparesLookupType() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from " + SparesLookupType.TABLE_NAME + ";";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectPartno(String Principal) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + SparesLookupType._ID + " , " + SparesLookupType.COLUMN_NAME_PART_NO + " from " + SparesLookupType.TABLE_NAME + " where " + SparesLookupType.COLUMN_NAME_PRINCIPAL + " = '" + Principal + "' ;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectLookUpsSpares(String Part) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + SparesLookupType._ID + " , " + SparesLookupType.COLUMN_NAME_PART_NO + " , " + SparesLookupType.COLUMN_NAME_SHORT_DESCRIPTION + " , " + SparesLookupType.COLUMN_NAME_UOM + " , " + SparesLookupType.COLUMN_NAME_SPARES_ID + " from " + SparesLookupType.TABLE_NAME + " where " + SparesLookupType.COLUMN_NAME_PART_NO + "='" + Part + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

}

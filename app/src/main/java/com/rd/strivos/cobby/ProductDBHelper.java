package com.rd.strivos.cobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rd.strivos.cobby.ProductDBFields.ProductAdd;
import com.rd.strivos.cobby.ProductDBFields.ProductLookupType;

/**
 * Created by COBURG DESIGN on 11-01-2016.
 */
public class ProductDBHelper extends SQLiteOpenHelper {

    public ProductDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "productlive.db";

    private static final String SQL_CREATE_PRODUCT = "CREATE TABLE "
            + ProductAdd.TABLE_NAME + " (" + ProductAdd._ID //0
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ProductAdd.COLUMN_NAME_PRINCIPAL + " TEXT," //1
            + ProductAdd.COLUMN_NAME_PRODUCT_ID + " TEXT," //2
            + ProductAdd.COLUMN_NAME_PART_NO + " TEXT," //3
            + ProductAdd.COLUMN_NAME_SHORT_DESCRIPTION + " TEXT," //4
            + ProductAdd.COLUMN_NAME_PRODUCT_TYPE + " TEXT," //5
            + ProductAdd.COLUMN_NAME_EQUIPMENT_TYPE + " TEXT," //6
            + ProductAdd.COLUMN_NAME_UOM + " TEXT," //7
            + ProductAdd.COLUMN_NAME_QUANTITY + " TEXT," //8
            + ProductAdd.COLUMN_NAME_PROJECT_TYPE + " TEXT," //9
            + ProductAdd.COLUMN_NAME_PROJECT_NAME + " TEXT," //10
            + ProductAdd.COLUMN_NAME_STATUS + " TEXT" + " )"; //11

    private static final String SQL_DELETE_PRODUCT = "DROP TABLE IF EXISTS "
            + ProductAdd.TABLE_NAME;

    private static final String SQL_CREATE_PRODUCT_LOOKUP_TYPE = "CREATE TABLE "
            + ProductLookupType.TABLE_NAME + " (" + ProductLookupType._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ProductLookupType.COLUMN_NAME_PRINCIPAL + " TEXT,"
            + ProductLookupType.COLUMN_NAME_PRODUCT_ID + " TEXT,"
            + ProductLookupType.COLUMN_NAME_PART_NO + " TEXT,"
            + ProductLookupType.COLUMN_NAME_SHORT_DESCRIPTION + " TEXT,"
            + ProductLookupType.COLUMN_NAME_PRODUCT_TYPE + " TEXT,"
            + ProductLookupType.COLUMN_NAME_EQUIPMENT_TYPE + " TEXT,"
            + ProductLookupType.COLUMN_NAME_UOM + " TEXT" + " )";

    private static final String SQL_DELETE_PRODUCT_LOOKUP_TYPE = "DROP TABLE IF EXISTS "
            + ProductLookupType.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PRODUCT);
        db.execSQL(SQL_CREATE_PRODUCT_LOOKUP_TYPE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PRODUCT);
        db.execSQL(SQL_DELETE_PRODUCT_LOOKUP_TYPE);
        onCreate(db);
    }

    public long insertProduct(String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9, String val10, String val11) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(ProductAdd.COLUMN_NAME_PRINCIPAL, val1);
        initialValues.put(ProductAdd.COLUMN_NAME_PRODUCT_ID, val2);
        initialValues.put(ProductAdd.COLUMN_NAME_PART_NO, val3);
        initialValues.put(ProductAdd.COLUMN_NAME_SHORT_DESCRIPTION, val4);
        initialValues.put(ProductAdd.COLUMN_NAME_PRODUCT_TYPE, val5);
        initialValues.put(ProductAdd.COLUMN_NAME_EQUIPMENT_TYPE, val6);
        initialValues.put(ProductAdd.COLUMN_NAME_UOM, val7);
        initialValues.put(ProductAdd.COLUMN_NAME_QUANTITY, val8);
        initialValues.put(ProductAdd.COLUMN_NAME_PROJECT_TYPE, val9);
        initialValues.put(ProductAdd.COLUMN_NAME_PROJECT_NAME, val10);
        initialValues.put(ProductAdd.COLUMN_NAME_STATUS, val11);

        long l = db.insert(ProductAdd.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public void updateProduct(long val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8, String val9, String val10, String val11) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProductAdd.COLUMN_NAME_PRINCIPAL, val2);
        values.put(ProductAdd.COLUMN_NAME_PRODUCT_ID, val3);
        values.put(ProductAdd.COLUMN_NAME_PART_NO, val4);
        values.put(ProductAdd.COLUMN_NAME_SHORT_DESCRIPTION, val5);
        values.put(ProductAdd.COLUMN_NAME_PRODUCT_TYPE, val6);
        values.put(ProductAdd.COLUMN_NAME_EQUIPMENT_TYPE, val7);
        values.put(ProductAdd.COLUMN_NAME_UOM, val8);
        values.put(ProductAdd.COLUMN_NAME_QUANTITY, val9);
        values.put(ProductAdd.COLUMN_NAME_PROJECT_TYPE, val10);
        values.put(ProductAdd.COLUMN_NAME_PROJECT_NAME, val11);

        sqdb.update(ProductAdd.TABLE_NAME, values, "_id=" + val1, null);
    }

    public int deleteProduct(long id) {
        return getWritableDatabase().delete(ProductAdd.TABLE_NAME, ProductAdd._ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Cursor getProductList(String ProjectName, String ProjectType) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + ProductAdd.TABLE_NAME + " WHERE " + ProductAdd.COLUMN_NAME_PROJECT_NAME + "='" + ProjectName + "' and " + ProductAdd.COLUMN_NAME_PROJECT_TYPE + " = '" + ProjectType + "' and " +ProductAdd.COLUMN_NAME_STATUS+" = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getProductListFull(int id) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + ProductAdd.TABLE_NAME + " WHERE " + ProductAdd._ID + "=" + id + "";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor ProductForSync(String companyName, String Type) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + ProductAdd._ID + " , " + ProductAdd.COLUMN_NAME_PRODUCT_ID + "," + ProductAdd.COLUMN_NAME_UOM + " , " + ProductAdd.COLUMN_NAME_QUANTITY + " from " + ProductAdd.TABLE_NAME + " where " + ProductAdd.COLUMN_NAME_PROJECT_NAME + " = '" + companyName + "' and " + ProductAdd.COLUMN_NAME_PROJECT_TYPE + " = '" + Type + "' and " + ProductAdd.COLUMN_NAME_STATUS + " = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void ProductStatusUpdate(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProductAdd.COLUMN_NAME_STATUS, "Y");
        System.out.print(val1);

        sqdb.update(ProductAdd.TABLE_NAME, values, "_id=" + val1, null);
    }

    /**
     * INSERT PRODUCT LOOKUP TYPE
     */

    public long insertProductLookup(String val1, String val2, String val3,
                                    String val4, String val5, String val6, String val7) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(ProductLookupType.COLUMN_NAME_PRINCIPAL, val1);
        initialValues.put(ProductLookupType.COLUMN_NAME_PRODUCT_ID, val2);
        initialValues.put(ProductLookupType.COLUMN_NAME_PART_NO, val3);
        initialValues.put(ProductLookupType.COLUMN_NAME_SHORT_DESCRIPTION, val4);
        initialValues.put(ProductLookupType.COLUMN_NAME_PRODUCT_TYPE, val5);
        initialValues.put(ProductLookupType.COLUMN_NAME_EQUIPMENT_TYPE, val6);
        initialValues.put(ProductLookupType.COLUMN_NAME_UOM, val7);

        long l = db.insert(ProductLookupType.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public void deleteProductLookupTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ProductLookupType.TABLE_NAME, null, null);
        db.close();
    }

    public Cursor getProductLookupType() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from " + ProductLookupType.TABLE_NAME + ";";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectPartno(String Principal) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + ProductLookupType._ID + " , " + ProductLookupType.COLUMN_NAME_PART_NO + " from " + ProductLookupType.TABLE_NAME + " where " + ProductLookupType.COLUMN_NAME_PRINCIPAL + " = '" + Principal + "' ;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectLookUps(String Part) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + ProductLookupType._ID + " , " + ProductLookupType.COLUMN_NAME_PART_NO + " , " + ProductLookupType.COLUMN_NAME_SHORT_DESCRIPTION + " , " + ProductLookupType.COLUMN_NAME_PRODUCT_TYPE + " , " + ProductLookupType.COLUMN_NAME_UOM + " , " + ProductLookupType.COLUMN_NAME_EQUIPMENT_TYPE + " , " + ProductLookupType.COLUMN_NAME_PRODUCT_ID + " from " + ProductLookupType.TABLE_NAME + " where " + ProductLookupType.COLUMN_NAME_PART_NO + "='" + Part + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
}

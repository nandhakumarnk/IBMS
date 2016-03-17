package com.rd.strivos.cobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rd.strivos.cobby.ExpenseDBFields.ExpenseAdd;
import com.rd.strivos.cobby.ExpenseDBFields.ExpenseType;

/**
 * Created by COBURG DESIGN on 08-01-2016.
 */
public class ExpenseDBHelper extends SQLiteOpenHelper {

    public ExpenseDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "expenselive.db";

    private static final String SQL_CREATE_EXPENSE = "CREATE TABLE "
            + ExpenseAdd.TABLE_NAME + " (" + ExpenseAdd._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ExpenseAdd.COLUMN_NAME_PROJECT_NAME + " TEXT,"
            + ExpenseAdd.COLUMN_NAME_EXPENSE_TYPE + " TEXT,"
            + ExpenseAdd.COLUMN_NAME_EXPENSE_AMOUNT + " TEXT,"
            + ExpenseAdd.COLUMN_NAME_PROJECT_TYPE + " TEXT,"
            + ExpenseAdd.COLUMN_NAME_EXPENSE_TYPE_ID + " TEXT,"
            + ExpenseAdd.COLUMN_NAME_STATUS + " TEXT" + " )";

    private static final String SQL_DELETE_EXPENSE = "DROP TABLE IF EXISTS "
            + ExpenseAdd.TABLE_NAME;

    private static final String SQL_CREATE_EXPENSE_TYPE = "CREATE TABLE "
            + ExpenseType.TABLE_NAME + " (" + ExpenseType._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ExpenseType.COLUMN_NAME_EXPENSES_TYPE_ID + " TEXT,"
            + ExpenseType.COLUMN_NAME_EXPENSE_TYPE_DESCRIPTION + " TEXT,"
            + ExpenseType.COLUMN_NAME_EXPENSE_DB_MASTER_TYPE_ID + " TEXT" + " )";

    private static final String SQL_DELETE_EXPENSE_TYPE = "DROP TABLE IF EXISTS "
            + ExpenseType.TABLE_NAME;


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_EXPENSE);
        db.execSQL(SQL_CREATE_EXPENSE_TYPE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_EXPENSE);
        db.execSQL(SQL_DELETE_EXPENSE_TYPE);
        onCreate(db);
    }

    public long insertExpense(String val1, String val2, String val3, String val4, String val5, String val6) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(ExpenseAdd.COLUMN_NAME_PROJECT_NAME, val1);
        initialValues.put(ExpenseAdd.COLUMN_NAME_EXPENSE_TYPE, val2);
        initialValues.put(ExpenseAdd.COLUMN_NAME_EXPENSE_AMOUNT, val3);
        initialValues.put(ExpenseAdd.COLUMN_NAME_PROJECT_TYPE, val4);
        initialValues.put(ExpenseAdd.COLUMN_NAME_EXPENSE_TYPE_ID, val5);
        initialValues.put(ExpenseAdd.COLUMN_NAME_STATUS, val6);

        long l = db.insert(ExpenseAdd.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public void updateExpense(long val1, String val2, String val3, String val4, String val5, String val6) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ExpenseAdd.COLUMN_NAME_PROJECT_NAME, val2);
        values.put(ExpenseAdd.COLUMN_NAME_EXPENSE_TYPE, val3);
        values.put(ExpenseAdd.COLUMN_NAME_EXPENSE_AMOUNT, val4);
        values.put(ExpenseAdd.COLUMN_NAME_PROJECT_TYPE, val5);
        values.put(ExpenseAdd.COLUMN_NAME_EXPENSE_TYPE_ID, val6);

        sqdb.update(ExpenseAdd.TABLE_NAME, values, "_id=" + val1, null);
    }

    public int deleteExpense(long id) {
        return getWritableDatabase().delete(ExpenseAdd.TABLE_NAME, ExpenseAdd._ID + " = ?", new String[]{String.valueOf(id)});
    }

    public Cursor getExpenseList(String ProjectName, String ProjectType) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + ExpenseAdd.TABLE_NAME + " WHERE " + ExpenseAdd.COLUMN_NAME_PROJECT_NAME + "='" + ProjectName + "' and " + ExpenseAdd.COLUMN_NAME_PROJECT_TYPE + " = '" + ProjectType + "' and " +ExpenseAdd.COLUMN_NAME_STATUS+" = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getExpenseListFull(int id) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "SELECT * FROM " + ExpenseAdd.TABLE_NAME + " WHERE " + ExpenseAdd._ID + "=" + id + "";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor ExpenseForSync(String companyName, String Type) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + ExpenseAdd._ID + " , " + ExpenseAdd.COLUMN_NAME_PROJECT_NAME + "," + ExpenseAdd.COLUMN_NAME_EXPENSE_TYPE + " , " + ExpenseAdd.COLUMN_NAME_EXPENSE_AMOUNT + " , " + ExpenseAdd.COLUMN_NAME_PROJECT_TYPE + " , " + ExpenseAdd.COLUMN_NAME_STATUS + " , " + ExpenseAdd.COLUMN_NAME_EXPENSE_TYPE_ID + " from " + ExpenseAdd.TABLE_NAME + " where " + ExpenseAdd.COLUMN_NAME_PROJECT_NAME + " = '" + companyName + "' and " + ExpenseAdd.COLUMN_NAME_PROJECT_TYPE + " = '" + Type + "' and " + ExpenseAdd.COLUMN_NAME_STATUS + " = 'N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void ExpenseStatusUpdate(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ExpenseAdd.COLUMN_NAME_STATUS, "Y");
        System.out.print(val1);
        sqdb.update(ExpenseAdd.TABLE_NAME, values, "_id=" + val1, null);
    }

    /**
     * INSERT EXPENSES TYPE LOOKUP
     */

    public long insertExpenseType(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(ExpenseType.COLUMN_NAME_EXPENSES_TYPE_ID, val1);
        initialValues.put(ExpenseType.COLUMN_NAME_EXPENSE_TYPE_DESCRIPTION, val2);
        initialValues.put(ExpenseType.COLUMN_NAME_EXPENSE_DB_MASTER_TYPE_ID, val3);

        long l = db.insert(ExpenseType.TABLE_NAME, null, initialValues);
        db.close();
        return l;
    }

    public void deleteExpensesTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ExpenseType.TABLE_NAME, null, null);
        db.close();
    }

    public Cursor getExpensesType() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from " + ExpenseType.TABLE_NAME + ";";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getExpensesTypeIDValue(String Description) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select " + ExpenseType.COLUMN_NAME_EXPENSES_TYPE_ID + " from " + ExpenseType.TABLE_NAME + " where " + ExpenseType.COLUMN_NAME_EXPENSE_TYPE_DESCRIPTION + " = '" + Description + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

}

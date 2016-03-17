package com.rd.strivos.cobby;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Data extends SQLiteOpenHelper {
	private static final int VERSION = 7;
	
	public static final String IFORM_DATA = "data";
	public static final String IFORM_DATASTAMP = "datestamp";
	public static final String IFORM_USER = "user";
	public static final String IFORM_SCCODE ="sccode";
	public static final String IFORM_ASSIGNEDTO = "assignedto";
	public static final String IFORM_REPORTSTATUS = "reportstatus";
	public static final String IFORM_INFORMTECH = "informtech";
	public static final String IFORM_INFORMTECHNO = "informtechno";
	public static final String IFORM_INFORMCUST  = "informcust";
	public static final String IFORM_INFORMCUSTNO = "informcustno";
	public static final String IFORM_REMARKS = "remarks";
	public static final String IFORM_FLAG = "flag";
	private SQLiteDatabase db;
		
	String table_create_iformdata = "CREATE TABLE tab_iformdata(_id integer primary key autoincrement," 
			+ "data text,"
			+ "datestamp text,"
			+ "user text,"
			+ "sccode text,"
			+ "assignedto text,"
			+ "reportstatus text,"
			+ "informtech text,"
			+ "informtechno text,"
			+ "informcust text,"
			+ "informcustno text,"
			+ "remarks text,"
			+ "flag text);";
	
	String table_create_pickwords = "CREATE TABLE tab_pickwords(_id integer primary key autoincrement," 
			+ "pick1 text,"
			+ "pick2 text,"
			+ "pick3 text,"
			+ "pick4 text,"
			+ "pick5 text);";
	

	public Data(Context context) {
		super(context, "isf_database", null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(table_create_iformdata);
		db.execSQL(table_create_pickwords);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		try {
			
			db.execSQL("DROP TABLE IF EXISTS tab_iformdata");
			db.execSQL("DROP TABLE IF EXISTS tab_pickwords");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public long value_insert_tab_iformdata(String val1, String val2, String val3,
			String val4,String val5,String val6,String val7,String val8,String val9,String val10, String val11, String val12) throws SQLException {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues initialValues = new ContentValues();
		initialValues.put(IFORM_DATA, val1);
		initialValues.put(IFORM_DATASTAMP, val2);
		initialValues.put(IFORM_USER, val3);
		initialValues.put(IFORM_SCCODE, val4);
		initialValues.put(IFORM_ASSIGNEDTO, val5);
		initialValues.put(IFORM_REPORTSTATUS, val6);
		initialValues.put(IFORM_INFORMTECH, val7);
		initialValues.put(IFORM_INFORMTECHNO, val8);
		initialValues.put(IFORM_INFORMCUST, val9);
		initialValues.put(IFORM_INFORMCUSTNO, val10);
		initialValues.put(IFORM_REMARKS, val11);
		initialValues.put(IFORM_FLAG, val12);
		long l = db.insert("tab_iformdata", null, initialValues);
		db.close();
		return l;
	}

	public Cursor selectfromtab_iformdata() {

		Cursor cursor = getReadableDatabase().
				  rawQuery("SELECT * FROM tab_iformdata WHERE flag ='NO'", null);
	
		return cursor;
	}
	
	public Cursor check() {

		Cursor cursor = getReadableDatabase().
				  rawQuery("SELECT * FROM tab_iformdata WHERE flag ='YES'", null);
	
		return cursor;
	}
	
	
	public Cursor selectfromtab_iformdata_check() {

		Cursor cursor = getReadableDatabase().
				  rawQuery("SELECT * FROM tab_iformdata", null);
	
		return cursor;
	}
	
	public int sync_count() {
    SQLiteDatabase db = this.getWritableDatabase();
    
    Cursor mcursor = db.rawQuery("SELECT * FROM tab_iformdata" , null); //WHERE flag ='NO'"
    mcursor.moveToFirst();
    int icount = mcursor.getInt(0);
    return icount;
	}
	
	public void update_iformdata(String val1) {
	SQLiteDatabase sqdb=this.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("flag","Yes");
		sqdb.update("tab_iformdata",values,"_id='"+val1+"'",null);
	}
	
//	public void deletefromtab_iformdata(String cid1,String cid2,String cid3)
//	{
//		SQLiteDatabase db=this.getWritableDatabase();
//		String del1="DELETE FROM tab_iformdata where colourid1="+cid1;
//		String del2="DELETE FROM tab_iformdata where colourid2="+cid2;
//		String del3="DELETE FROM tab_iformdata where colourid3="+cid3;
//
//		db.execSQL(del1);
//		db.execSQL(del2);
//		db.execSQL(del3);
//		db.close();
//		
//	}	
	
	public long insert_tab_pickwords(String val1, String val2, String val3,
			String val4,String val5) throws SQLException {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues initialValues = new ContentValues();
		initialValues.put("pick1", val1);
		initialValues.put("pick2", val2);
		initialValues.put("pick3", val3);
		initialValues.put("pick4", val4);
		initialValues.put("pick5", val5);
		long l = db.insert("tab_pickwords", null, initialValues);
		db.close();
		return l;
	}
	
	public long update_tab_pickwords(String val1, String val2, String val3,
			String val4,String val5) throws SQLException {
		ContentValues initialValues = new ContentValues();
		initialValues.put("pick1", val1);
		initialValues.put("pick2", val2);
		initialValues.put("pick3", val2);
		initialValues.put("pick4", val2);
		initialValues.put("pick5", val2);
		return db.update("tab_pickwords", initialValues,
				"_id" + "=" + "1", null);
	}
}

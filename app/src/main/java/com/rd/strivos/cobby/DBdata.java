package com.rd.strivos.cobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBdata {
		
	
	public static final String KEY_ROWID = "_id";
	// Login
	
	public static final String LOGIN_USER = "userid";
	public static final String LOGIN_PWD = "pwd";
	public static final String LOGIN_IMEI="imei";	

	
//  public static final String IFORM_SCCODE ="sccode";
//	public static final String IFORM_USER ="userID";
//	public static final String IFORM_NAME = "name";
//	public static final String IFORM_LOC = "location";
//	public static final String IFORM_PHONE = "phoneno";
//	public static final String IFORM_MOBILE = "mobile";
//	public static final String IFORM_EMAIL = "emailid";
//	public static final String IFORM_PIN = "pin";
//	public static final String IFORM_STDATE = "userstartdate";
//	public static final String IFORM_USERGROUP = "usergroup";
	
	//Offline database
	public static final String IFORM_DATA = "data";
	public static final String IFORM_DATASTAMP = "datestamp";
	public static final String IFORM_USER = "userid";
	public static final String IFORM_SCCODE ="sccode";
	public static final String IFORM_ASSIGNEDTO = "assignedto";
	public static final String IFORM_REPORTSTATUS = "assignedstatus";
	public static final String IFORM_INFORMTECH = "informtech";
	public static final String IFORM_INFORMTECHNO = "informtechno";
	public static final String IFORM_INFORMCUST  = "informcust";
	public static final String IFORM_INFORMCUSTNO = "informcustno";
	public static final String IFORM_REMARKS = "remarks";
	public static final String IFORM_FLAG = "NO";
			
	private static final String TAG = "DBAdapter";

	private static final String DATABASE_NAME = "MiCamDB";
	private static final String DATABASE_IFORM = "iform_data" ;
	private static final String DATABASE_LOGIN = "check_login" ;
	
	private static final int DATABASE_VERSION = 2;

	private static final String iform_data_tab_create = "create table iform_data(_id integer primary key autoincrement, " 
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
	
	private static final String check_login_tab_create = "create table check_login(_id integer primary key autoincrement," 
			+ "userid text,"
			+ "pwd text,"
			+ "imei text);";
			
		private Context context = null;
		private DBLogin DBHelper;
		private SQLiteDatabase db;

	public DBdata(Context ctx) {
		this.context = ctx;
		DBHelper = new DBLogin(context);
	}

	private static class DBLogin extends SQLiteOpenHelper {

		DBLogin(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(iform_data_tab_create);
			db.execSQL(check_login_tab_create);
			//db.execSQL(camtemptab_create);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			try {
				Log.w(TAG, "Upgrading database from version " + oldVersion
						+ " to " + newVersion
						+ ", which will destroy all old data");
				db.execSQL("DROP TABLE IF EXISTS login ");
				onCreate(db);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
		
	}	

		

	public void open() throws SQLException {
		db = DBHelper.getWritableDatabase();
	}

	public void close() {
		DBHelper.close();
	}	
	
	//Login
	public Cursor login_get(long rowId) throws SQLException {
		Cursor c = db.query(true, DATABASE_LOGIN, new String[] {LOGIN_USER,LOGIN_PWD},
				null, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	public int deleteUser()
	{
		db.execSQL("delete from "+ DATABASE_LOGIN);
		return 0;
	}
	
	public long login_insert(String val1, String val2) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(LOGIN_USER, val1);
		initialValues.put(LOGIN_PWD, val2);
		return db.insert(DATABASE_LOGIN, null, initialValues);
	}

	public long login_update(String val1, String val2) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(LOGIN_USER, val1);
		initialValues.put(LOGIN_PWD, val2);
		
		return db.update(DATABASE_LOGIN, initialValues,
				KEY_ROWID + "=" + "1", null);
	}
	
	public long iform_insert(String val1, String val2, String val3,String val4,String val5,String val6,String val7,String val8,String val9,String val10,String val11,String val12) {
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
		//long l =  db.insert(DATABASE_IFORM, null, initialValues);
	
		return db.insert(DATABASE_IFORM, null, initialValues);
	}
	
	public Cursor iform_get() throws SQLException {
		Cursor c = db.query("DATABASE_IFORM", new String[] { "IFORM_DATA","IFORM_DATASTAMP","IFORM_USER","IFORM_SCCODE","IFORM_ASSIGNEDTO","IFORM_REPORTSTATUS","IFORM_INFORMTECH","IFORM_INFORMTECHNO","IFORM_INFORMCUST","IFORM_INFORMCUSTNO","IFORM_REMARKS","IFORM_FLAG" }, 
				null,null,null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	

	
	
	public long iform_update(String val1, String val2, String val3,
			String val4,String val5,String val6,String val7,String val8,String val9,String val10, String val11, String val12) {
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
		//long l = db.insert(DATABASE_IFORM, null, initialValues);
		return db.insert(DATABASE_IFORM, null, initialValues);
	}
	
	
	
}

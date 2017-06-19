package com.example.demooflistwithhandler;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "StudentData";
	public static final String TABLE_NAME = "Student";
	public static final String KEY_ID = "Id";
	public static final String KEY_NAME = "Sname";
	public static final String KEY_EMAIL = "Email";
	String ID;
	SQLiteDatabase db;

	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	public void open() {
		db = this.getWritableDatabase();
		}
	public void close() {
		db.close();
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CRETATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_NAME + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME
				+ " TEXT," + KEY_EMAIL + " TEXT " + ")";
		db.execSQL(CRETATE_TABLE_STUDENT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
		onCreate(db);
	}

	public void insertdata(Student stu) {
		 open();
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, stu.getSname());
		values.put(KEY_EMAIL, stu.getEmail());
		db.insert(TABLE_NAME, null, values);
		close();
	}

	public List<Student> getallstudent() {
		ArrayList<Student> studentdata = new ArrayList<Student>();
		 /*db = this.getWritableDatabase();*/
		open();
		Cursor c1 = db.rawQuery("SELECT * FROM  " + TABLE_NAME, null);
		if (c1.moveToFirst()) {
			do {
				Student s = new Student();
				s.SetId(c1.getString(c1.getColumnIndex(KEY_ID)));
				s.SetSname(c1.getString(c1.getColumnIndex(KEY_NAME)));
				s.SetEmail(c1.getString(c1.getColumnIndex(KEY_EMAIL)));
				studentdata.add(s);
			} while (c1.moveToNext());
		}
		c1.close();
		close();
		return studentdata;
	}

	public int updatedata(Student stu) {
		 open();
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, stu.getSname());
		values.put(KEY_EMAIL, stu.getEmail());
		int i = db.update(TABLE_NAME,values,KEY_ID+"="+stu.getId(),null);
		if (i > 0) {
			Log.e("UPATE", "update data" + String.valueOf(i));
		}
		close();
		return i;

	}

	public void Deletestudent(Student stu) {
	   db = this.getWritableDatabase();
		db.delete(TABLE_NAME, KEY_ID + " = ?",new String[] { String.valueOf(stu.getId()) });
		db.close();

	}
}

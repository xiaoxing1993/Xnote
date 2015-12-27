package com.xing.xnote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class XnoteDbHelper extends SQLiteOpenHelper{

	public static final String TABLE_NAME="notes";
	public static final String ID="_id";
	public static final String TIME="time";
	public static final String PATH="path";
	public static final String VIDEO="video";
	public static final String CONTENT="content";
	public static final String CREATE_BOOK="create table "+ TABLE_NAME+"(" +
			ID+" integer primary key autoincrement,"+
			PATH+" text,"+
			VIDEO+" text,"+
			CONTENT+" text not null,"+
			TIME+" text not null)";
			
			
			
			
	public XnoteDbHelper(Context context) {
		super(context, "Xnote", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_BOOK);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}

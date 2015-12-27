package com.xing.xnote;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddContent extends Activity implements OnClickListener{

	private  String flag;
	private  Button butSave,butCancel;
	private  EditText et;
	private  ImageView ivImage;
	private  ImageView ivVideo;
	private  XnoteDbHelper dbHelper;
	private  SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcontent);
		flag=getIntent().getStringExtra(MainActivity.FLAG);
		initView();
		dbHelper=new XnoteDbHelper(this);
		db=dbHelper.getWritableDatabase();
		
	}
	
	private void initView(){
		
		butSave=(Button)findViewById(R.id.but_save);
		butCancel=(Button)findViewById(R.id.but_cancel);
		et=(EditText)findViewById(R.id.et);
		ivImage=(ImageView)findViewById(R.id.iv_image);
		ivVideo=(ImageView)findViewById(R.id.iv_video);
		butSave.setOnClickListener(this);
		butCancel.setOnClickListener(this);
	}
	private void addDB(){
		
		ContentValues values=new ContentValues();
		values.put(XnoteDbHelper.CONTENT, et.getText().toString());
		values.put(XnoteDbHelper.TIME, getTime());
		db.insert(XnoteDbHelper.TABLE_NAME, null, values);
		
	}
	public String getTime(){
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyƒÍMM‘¬dd»’ HH:mm:ss");
		Date date=new Date();
		String time=sdf.format(date);
		return time;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.but_save:
			addDB();
			finish();
			break;

		case R.id.but_cancel:
			finish();
			break;
		default:
			break;
		}
	}
}

package com.xing.xnote;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;

public class AddContent extends Activity implements OnClickListener{

	private  String flag;
	private  Button butSave,butCancel;
	private  EditText et;
	private  ImageView ivImage;
	private  VideoView ivVideo;
	private  XnoteDbHelper dbHelper;
	private  SQLiteDatabase db;
	private  File imageFile,videoFile;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcontent);
		flag=getIntent().getStringExtra(MainActivity.FLAG);
		initView();
		setView();
		dbHelper=new XnoteDbHelper(this);
		db=dbHelper.getWritableDatabase();
		
	}
	private void setView(){
		if(flag.equals("1")){
			ivImage.setVisibility(View.GONE);
			ivVideo.setVisibility(View.GONE);
		}else if(flag.equals("2")){
			ivImage.setVisibility(View.VISIBLE);
			ivVideo.setVisibility(View.GONE);
			Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			imageFile=new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/"
			                   +getTime()+".jpg");
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
			startActivityForResult(intent, 2);
			
		}else  if(flag.equals("3")){
			
			ivImage.setVisibility(View.GONE);
			ivVideo.setVisibility(View.VISIBLE);
			Intent intent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
			videoFile=new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/"
			                   +getTime()+".mp4");
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(videoFile));
			startActivityForResult(intent, 3);
		}
	}
	
	private void initView(){
		
		butSave=(Button)findViewById(R.id.but_save);
		butCancel=(Button)findViewById(R.id.but_cancel);
		et=(EditText)findViewById(R.id.et);
		ivImage=(ImageView)findViewById(R.id.iv_image);
		ivVideo=(VideoView)findViewById(R.id.iv_video);
		butSave.setOnClickListener(this);
		butCancel.setOnClickListener(this);
	}
	private void addDB(){
		
		ContentValues values=new ContentValues();
		values.put(XnoteDbHelper.CONTENT, et.getText().toString());
		values.put(XnoteDbHelper.TIME, getTime());
		values.put(XnoteDbHelper.PATH, imageFile+"");
		values.put(XnoteDbHelper.VIDEO, videoFile+"");
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
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==2){
			
			Bitmap bitmap=BitmapFactory.decodeFile(imageFile.getAbsolutePath());
			ivImage.setImageBitmap(bitmap);
			
		}
		if(requestCode==3){
			ivVideo.setVideoURI(Uri.fromFile(videoFile));
			ivVideo.start();
		}
	}
	
}

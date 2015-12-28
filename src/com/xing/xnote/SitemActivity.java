package com.xing.xnote;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class SitemActivity extends Activity implements OnClickListener{

	private Button delete,back;
	private ImageView image;
	private VideoView video;
	private TextView  tv;
	private XnoteDbHelper dbHelper;
	private SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.item);
		initView();
		dbHelper=new XnoteDbHelper(this);
		db=dbHelper.getWritableDatabase();
		if(getIntent().getStringExtra(XnoteDbHelper.PATH).equals("null")){
			
			image.setVisibility(View.GONE);
		}
		else{
			image.setVisibility(View.VISIBLE);
		}
		if(getIntent().getStringExtra(XnoteDbHelper.VIDEO).equals("null")){
			
			video.setVisibility(View.GONE);
		}
		else{
			video.setVisibility(View.VISIBLE);
		}
		tv.setText(getIntent().getStringExtra(XnoteDbHelper.CONTENT));
		Bitmap bitmap=BitmapFactory.decodeFile(getIntent().getStringExtra(XnoteDbHelper.PATH));
		image.setImageBitmap(bitmap);
		video.setVideoURI(Uri.parse(getIntent().getStringExtra(XnoteDbHelper.VIDEO)));
		video.start();
	}
	
	private void initView(){
		
		delete=(Button)findViewById(R.id.but_delete);
		back=(Button)findViewById(R.id.but_back);
		image=(ImageView)findViewById(R.id.item_image);
		video=(VideoView)findViewById(R.id.item_video);
		tv=(TextView)findViewById(R.id.item_tv);
		delete.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.but_delete:
			delete();
			finish();
			break;

		case R.id.but_back:
			finish();
			break;
		}
	}
	public void delete(){
		
		db.delete(XnoteDbHelper.TABLE_NAME, XnoteDbHelper.ID+"="+getIntent().getIntExtra(XnoteDbHelper.ID, 0), null);
	}
}

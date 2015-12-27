package com.xing.xnote;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener{

	private Button butText,butImage,butVideo;
	private ListView lv;
	private Intent intent;
	private XnoteDbHelper dbHelper;
	private SQLiteDatabase dbReader;
	private Cursor cursor;
	private MyAdapter adapter;
	public static final String FLAG="flag";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		
		
	}
	@Override
	protected void onResume() {

		super.onResume();
		queryDB();
	}
	public void initView(){
		
		butText=(Button)findViewById(R.id.but_text);
		butImage=(Button) findViewById(R.id.but_image);
		butVideo=(Button)findViewById(R.id.but_video);
		lv=(ListView)findViewById(R.id.listview);
		butText.setOnClickListener(this);
		butImage.setOnClickListener(this);
		butVideo.setOnClickListener(this);
		dbHelper=new XnoteDbHelper(this);
		dbReader=dbHelper.getReadableDatabase();
	}
	public void queryDB(){
		
	    cursor=dbReader.query(XnoteDbHelper.TABLE_NAME, null, null, null, null, null, null);
	    adapter=new MyAdapter(cursor, this);
		lv.setAdapter(adapter);
	}
	
	@Override
	public void onClick(View v) {
         intent=new Intent(this,AddContent.class);
		switch (v.getId()) {
		case R.id.but_text:
			intent.putExtra(FLAG, "1");
			startActivity(intent);
			break;
		case R.id.but_image:
			intent.putExtra(FLAG, "2");
			startActivity(intent);
			break;
		case R.id.but_video:
			intent.putExtra(FLAG, "3");
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	
}

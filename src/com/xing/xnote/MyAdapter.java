package com.xing.xnote;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{

	private Context mContext;
	private Cursor  mCursor;
	
	public MyAdapter(Cursor cursor,Context context){
		
		mContext=context;
		mCursor=cursor;
	}
	@Override
	public int getCount() {

		return mCursor.getCount();
	}

	@Override
	public Object getItem(int position) {

		return mCursor.getPosition();
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
 
		View v;
		ViewHolder viewHolder;
		if(convertView==null){
			v=LayoutInflater.from(mContext).inflate(R.layout.cell, null);
			viewHolder=new ViewHolder();
			viewHolder.image=(ImageView)v.findViewById(R.id.list_image);
			viewHolder.video=(ImageView)v.findViewById(R.id.list_video);
			viewHolder.content=(TextView)v.findViewById(R.id.list_content);
			viewHolder.time=(TextView)v.findViewById(R.id.list_time);
			v.setTag(viewHolder);
		}
		else{
			v=convertView;
			viewHolder=(ViewHolder)v.getTag();
		}
		mCursor.moveToPosition(position);
		String content=mCursor.getString(mCursor.getColumnIndex(XnoteDbHelper.CONTENT));
		String time=mCursor.getString(mCursor.getColumnIndex(XnoteDbHelper.TIME));
		viewHolder.content.setText(content);
		viewHolder.time.setText(time);
		
		return v;
	}
	
	class ViewHolder{
		
		private ImageView image;
		private ImageView video;
		private TextView content;
		private TextView time;
	}

}

package com.adapter;

import java.util.List;

import com.drawpicture.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainListAdapter extends BaseAdapter {
	private LayoutInflater inflater = null;
	private List<String> itemList = null;
	private TextView textView;

	public MainListAdapter(Context context, List<String> itemList) {
		// TODO Auto-generated constructor stub
		this.inflater = LayoutInflater.from(context);
		this.itemList = itemList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itemList == null ? 0 : itemList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return itemList == null ? -1 : itemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (view == null) {
			view = inflater.inflate(R.layout.main_listitem, null);
		}
		textView = (TextView) view.findViewById(R.id.textView);
		textView.setText(itemList.get(position));
		return view;
	}

}

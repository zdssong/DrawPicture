package com.drawpicture;

import java.util.ArrayList;
import java.util.List;

import com.adapter.MainListAdapter;
import com.pinballgame.PinBall;
import com.test.PathTest;
import com.test.TextTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {

	private ListView listView;
	private List<String> itemList;
	private String[] itemName = new String[] { "PathTest", "TextTest",
			"DrawView", "BallGame", "MatrixChangeView", "MoveBack",
			"WarpImage", "Shader", "FrameAnimation", "flowerTween",
			"butterfly", "ballfall" };
	private MainListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initListView();
		initListener();
	}

	private void initListView() {
		itemList = new ArrayList<String>();
		for (int i = 0; i < itemName.length; i++) {
			itemList.add(itemName[i]);
		}
		listView = (ListView) findViewById(R.id.listview);
		adapter = new MainListAdapter(this, itemList);
		listView.setAdapter(adapter);
	}

	private void initListener() {
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (position) {
		case 0:
			intent.setClass(MainActivity.this, PathTest.class);
			break;
		case 1:
			intent.setClass(MainActivity.this, TextTest.class);
			break;
		case 2:
			intent.setClass(MainActivity.this, DrawViewActivity.class);
			break;
		case 3:
			intent.setClass(MainActivity.this, PinBall.class);
			break;
		case 4:
			intent.setClass(MainActivity.this, MatrixChangeViewActivity.class);
			break;
		case 5:
			intent.setClass(MainActivity.this, MoveBackActivity.class);
			break;
		case 6:
			intent.setClass(MainActivity.this, WarpImageActivity.class);
			break;
		case 7:
			intent.setClass(MainActivity.this, ShaderActivity.class);
			break;
		case 8:
			intent.setClass(MainActivity.this, FrameActivity.class);
			break;
		case 9:
			intent.setClass(MainActivity.this, FlowerTweenActivity.class);
			break;
		case 10:
			intent.setClass(MainActivity.this, ButterflyActivity.class);
			break;
		case 11:
			intent.setClass(MainActivity.this, BallFallActivity.class);
			break;
		}
		startActivity(intent);
	}
}

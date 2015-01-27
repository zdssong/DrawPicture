package com.drawpicture;

import com.view.DrawView;

import android.app.Activity;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

public class DrawViewActivity extends Activity implements OnClickListener {

	EmbossMaskFilter emboss;
	BlurMaskFilter blur;

	private DrawView dView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_drawview);
		dView = (DrawView) findViewById(R.id.drawView);
		emboss = new EmbossMaskFilter(new float[] { 1.5f, 1.5f, 1.5f }, 0.6f,
				6, 4.2f);
		blur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);
		initListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.draw_picture_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.red:
			dView.mPaint.setColor(Color.RED);
			item.setChecked(true);
			break;
		case R.id.blue:
			dView.mPaint.setColor(Color.BLUE);
			item.setChecked(true);
			break;
		case R.id.green:
			dView.mPaint.setColor(Color.GREEN);
			item.setChecked(true);
			break;
		case R.id.width_1:
			dView.mPaint.setStrokeWidth(1);
			item.setChecked(true);
			break;
		case R.id.width_3:
			dView.mPaint.setStrokeWidth(3);
			item.setChecked(true);
			break;
		case R.id.width_5:
			dView.mPaint.setStrokeWidth(5);
			item.setChecked(true);
			break;
		case R.id.emboss:
			dView.mPaint.setMaskFilter(emboss);
			item.setChecked(true);
			break;
		case R.id.blur:
			dView.mPaint.setMaskFilter(blur);
			item.setChecked(true);
			break;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.circle:
			dView.setDrawCircle(true);
			dView.setDrawRectangle(false);
			dView.setDrawOval(false);
			break;
		case R.id.rectangle:
			dView.setDrawRectangle(true);
			dView.setDrawOval(false);
			dView.setDrawCircle(false);
			break;
		case R.id.oval:
			dView.setDrawOval(true);
			dView.setDrawCircle(false);
			dView.setDrawRectangle(false);
			break;
		case R.id.line:
			dView.setDrawOval(false);
			dView.setDrawCircle(false);
			dView.setDrawRectangle(false);
			break;
		}
	}

	private void initListener() {
		findViewById(R.id.circle).setOnClickListener(this);
		findViewById(R.id.rectangle).setOnClickListener(this);
		findViewById(R.id.oval).setOnClickListener(this);
		findViewById(R.id.line).setOnClickListener(this);
	}
}

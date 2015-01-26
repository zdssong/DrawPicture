package com.drawpicture;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class FrameActivity extends Activity {

	private TextView start;
	private TextView stop;
	private ImageView image;
	private AnimationDrawable anim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_frame);
		initView();
		initListener();
	}

	private void initView() {
		start = (TextView) findViewById(R.id.start);
		stop = (TextView) findViewById(R.id.stop);
		image = (ImageView) findViewById(R.id.imageView);
		anim = (AnimationDrawable) image.getBackground();
	}

	private void initListener() {
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				anim.start();
			}
		});
		stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				anim.stop();
			}
		});
	}

}

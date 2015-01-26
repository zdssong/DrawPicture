package com.drawpicture;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class FlowerTweenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_flowertween);

		final ImageView flower = (ImageView) findViewById(R.id.imageView);
		final Animation anim = AnimationUtils.loadAnimation(this,
				R.anim.flower_anim);
		anim.setFillAfter(true);
		final Animation reverse = AnimationUtils.loadAnimation(this,
				R.anim.flower_reverse);
		reverse.setFillAfter(true);

		TextView textView = (TextView) findViewById(R.id.textView);
		final Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == 0x123) {
					flower.startAnimation(reverse);
				}
				super.handleMessage(msg);
			}

		};
		textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				flower.startAnimation(anim);
				new Timer().schedule(new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						handler.sendEmptyMessage(0x123);
					}
				}, 3500);
			}
		});
	}

}

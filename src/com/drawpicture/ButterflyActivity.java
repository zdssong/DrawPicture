package com.drawpicture;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class ButterflyActivity extends Activity {
	private float currX = 0;
	private float currY = 0;

	float nextX = 0;
	float nextY = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_bufferfly);
		final ImageView butterfly = (ImageView) findViewById(R.id.imageView);
		final Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == 524) {
					if (nextX > 600) {
						nextX = currX = 0;
					} else {
						nextX += 10;
					}
					nextY = currY + (float) (Math.random() * 10 - 5);
					TranslateAnimation animation = new TranslateAnimation(
							currX, nextX, currY, nextY);
					currX = nextX;
					currY = nextY;
					animation.setDuration(200);
					butterfly.startAnimation(animation);
				}
				super.handleMessage(msg);
			}
		};
		final AnimationDrawable bufferflyAnim = (AnimationDrawable) butterfly
				.getBackground();
		butterfly.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bufferflyAnim.start();
				new Timer().schedule(new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						handler.sendEmptyMessage(524);
					}
				}, 0, 200);
			}
		});
	}

}

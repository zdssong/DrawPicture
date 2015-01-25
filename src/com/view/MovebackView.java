package com.view;

import java.util.Timer;
import java.util.TimerTask;

import com.drawpicture.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.view.View;

/**
 * 该View目前只能移动背景
 * */
public class MovebackView extends View {
	// 记录背景位图的实际高度
	final int BACK_HEIGHT = 1700;
	// 背景图片
	private Bitmap back;
	private Bitmap plane;
	// 背景图片的开始位置
	final int WIDTH = 320;
	final int HEIGHT = 440;
	private int startY = BACK_HEIGHT - HEIGHT;

	public MovebackView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		back = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.back_img);
		plane = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.plane);
		final Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 0x123) {
					if (startY <= 0) {
						startY = BACK_HEIGHT - HEIGHT;
					} else {
						startY -= 3;
					}
				}
				invalidate();
			}
		};
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(0x123);
			}
		}, 0, 100);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Bitmap bitmap2 = Bitmap.createBitmap(back, 0, startY, WIDTH, HEIGHT);
		canvas.drawBitmap(bitmap2, 0, 0, null);
		canvas.drawBitmap(plane, 160, 380, null);
		super.onDraw(canvas);
	}

}

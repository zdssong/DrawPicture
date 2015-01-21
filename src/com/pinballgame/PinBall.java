package com.pinballgame;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

public class PinBall extends Activity {

	private int tableWidth;
	private int tableHeight;
	private int rackety;
	private final int RACKET_HEIGHT = 20;
	private final int RACKET_WIDTH = 70;
	private final int BALL_SIZE = 12;
	private int ySpeed = 10;
	Random random = new Random();
	private double xyRate = random.nextDouble() - 0.5;
	private int xSpeed = (int) (ySpeed * xyRate * 2);
	private int ballX = random.nextInt(200) + 20;
	private int ballY = random.nextInt(10) + 20;
	private int racketX = random.nextInt(200);
	private boolean isLose = false;

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		final GameView gameView = new GameView(this);
		setContentView(gameView);
		// 获取窗口管理器
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		tableHeight = metrics.heightPixels;
		tableWidth = metrics.widthPixels;
		rackety = tableHeight - 80;
		final Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == 0x123) {
					gameView.invalidate();
				}
				super.handleMessage(msg);
			}

		};
		gameView.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				switch (event.getKeyCode()) {
				case KeyEvent.KEYCODE_A:
					if (racketX > 0)
						racketX -= 10;
					break;
				case KeyEvent.KEYCODE_D:
					if (racketX < tableWidth - RACKET_WIDTH)
						racketX += 10;
					break;
				}
				gameView.invalidate();
				return true;
			}
		});
		gameView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_MOVE:
					racketX = (int) event.getX();
					break;
				}
				gameView.invalidate();
				return true;
			}
		});

		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (ballX <= 0 || ballX >= tableWidth - BALL_SIZE) {
					xSpeed = -xSpeed;
				}
				if (ballY >= rackety - BALL_SIZE
						&& (ballX < racketX || ballX > racketX + RACKET_WIDTH)) {
					timer.cancel();
					isLose = true;
				}
				if (ballY <= 0
						|| (ballY >= rackety - BALL_SIZE && ballX > racketX && ballX <= racketX
								+ RACKET_WIDTH)) {
					ySpeed = -ySpeed;
				}
				ballX += xSpeed;
				ballY += ySpeed;
				handler.sendEmptyMessage(0x123);
			}
		}, 0, 100);
	}

	class GameView extends View {

		Paint mPaint = new Paint();

		public GameView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			setFocusable(true);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			mPaint.setStyle(Paint.Style.FILL);
			mPaint.setAntiAlias(true);
			if (isLose) {
				mPaint.setColor(Color.RED);
				mPaint.setTextSize(40);
				canvas.drawText("游戏结束", 50, 200, mPaint);
			} else {
				mPaint.setColor(Color.rgb(240, 240, 80));
				canvas.drawCircle(ballX, ballY, BALL_SIZE, mPaint);
				mPaint.setColor(Color.rgb(80, 80, 200));
				canvas.drawRect(racketX, rackety, racketX + RACKET_WIDTH,
						rackety + RACKET_HEIGHT, mPaint);
			}
			super.onDraw(canvas);
		}

	}
}

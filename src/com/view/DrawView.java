package com.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
	float preX;
	float preY;
	private Path mPath;
	public Paint mPaint;
	final int VIEW_WIDTH = 320;
	final int VIEW_HEIGH = 480;
	Bitmap cacheBitmap = null;
	Canvas cacheCanvas = null;

	public DrawView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
	}

	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		cacheBitmap = Bitmap.createBitmap(VIEW_WIDTH, VIEW_HEIGH,
				Config.ARGB_8888);
		cacheCanvas = new Canvas();
		mPath = new Path();
		cacheCanvas.setBitmap(cacheBitmap);
		mPaint = new Paint(Paint.DITHER_FLAG);
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(1);
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
	}

	public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mPath.moveTo(x, y);
			preX = x;
			preY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			mPath.quadTo(preX, preY, x, y);
			preX = x;
			preY = y;
			break;
		case MotionEvent.ACTION_UP:
			cacheCanvas.drawPath(mPath, mPaint);
			mPath.reset();
			break;
		}
		invalidate();
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint bmpPaint = new Paint();
		canvas.drawBitmap(cacheBitmap, 0, 0, bmpPaint);
		canvas.drawPath(mPath, mPaint);
	}

}

package com.view;

import com.tools.Tools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 该View的目前的功能是画线
 * */
public class DrawView extends View {
	float preX;
	float preY;
	float nextX;
	float nextY;
	double radius;
	float left, top, right, bottom;
	float centerX, centerY;
	private Path mPath;
	public Paint mPaint;
	int VIEW_WIDTH = 20;
	int VIEW_HEIGH = 20;
	Bitmap cacheBitmap = null;
	Canvas cacheCanvas = null;

	private boolean isDrawRectangle = false;
	private boolean isDrawCircle = false;
	private boolean isDrawOval = false;

	public DrawView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		VIEW_HEIGH = Tools.getWindowHeigh((Activity) context);
		VIEW_WIDTH = Tools.getWindowWidth((Activity) context);
		cacheBitmap = Bitmap.createBitmap(VIEW_WIDTH, VIEW_HEIGH,
				Config.ARGB_8888);
		cacheCanvas = new Canvas();
		cacheCanvas.setBitmap(cacheBitmap);
		mPath = new Path();
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
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			preX = x;
			preY = y;
			if (isDrawCircle == false && isDrawOval == false
					&& isDrawRectangle == false)
				mPath.moveTo(x, y);
			break;
		case MotionEvent.ACTION_MOVE:
			if (isDrawCircle == false && isDrawOval == false
					&& isDrawRectangle == false) {
				mPath.quadTo(preX, preY, x, y);
				preX = x;
				preY = y;
			} else {
				nextX = x;
				nextY = y;
				if (isDrawCircle == true) {
					radius = Math
							.sqrt((Math.pow(Math.abs(nextX - preX), 2) + Math
									.pow(Math.abs(nextY - preY), 2))) / 2;
				}
				if (nextX > preX) {
					left = preX;
					right = nextX;
				} else {
					left = nextX;
					right = preX;
				}
				if (nextY > preY) {
					top = preY;
					bottom = nextY;
				} else {
					top = nextY;
					bottom = preY;
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			if (isDrawCircle == false && isDrawOval == false
					&& isDrawRectangle == false) {
				cacheCanvas.drawPath(mPath, mPaint);
				mPath.reset();
			} else {
				if (isDrawCircle == true) {
					cacheCanvas.drawCircle(Math.abs(nextX + preX) / 2,
							Math.abs(nextY + preY) / 2, (float) radius, mPaint);
				} else if (isDrawOval == true) {
					cacheCanvas.drawOval(new RectF(left, top, right, bottom),
							mPaint);
				} else if (isDrawRectangle == true) {
					cacheCanvas.drawRect(left, top, right, bottom, mPaint);
				}
			}
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
		if (isDrawCircle == true) {
			canvas.drawCircle(Math.abs(nextX + preX) / 2,
					Math.abs(nextY + preY) / 2, (float) radius, mPaint);
		} else if (isDrawOval == true) {
			canvas.drawOval(new RectF(left, top, right, bottom), mPaint);
		} else if (isDrawRectangle == true) {
			canvas.drawRect(left, top, right, bottom, mPaint);
		} else {
			canvas.drawPath(mPath, mPaint);
		}
	}

	public boolean isDrawRectangle() {
		return isDrawRectangle;
	}

	public void setDrawRectangle(boolean isDrawRectangle) {
		this.isDrawRectangle = isDrawRectangle;
	}

	public boolean isDrawCircle() {
		return isDrawCircle;
	}

	public void setDrawCircle(boolean isDrawCircle) {
		this.isDrawCircle = isDrawCircle;
	}

	public boolean isDrawOval() {
		return isDrawOval;
	}

	public void setDrawOval(boolean isDrawOval) {
		this.isDrawOval = isDrawOval;
	}

}

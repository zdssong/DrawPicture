package com.view;

import com.drawpicture.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 该View目前还没有实现如何功能
 * */
public class MatrixChangeView extends View {

	private Bitmap bitmap;
	private Matrix matrix = new Matrix();
	private float sx = 0.0f;
	private float sy = 0.0f;
	private int width = 0, height = 0;
	private float scaleX = 1.0f;
	private float scaleY = 1.0f;
	private boolean isScale = false;
	private boolean isRotate = false;
	private boolean isTranlate = false;

	private int firstPointPreX = 0, firstPointPreY = 0;
	private int secondPointPreX = 0, secondPointPreY = 0;
	private float distanceX = 1, distanceY = 1;

	private boolean isFirst = true;

	public MatrixChangeView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MatrixChangeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		bitmap = ((BitmapDrawable) context.getResources().getDrawable(
				R.drawable.ic_launcher)).getBitmap();
		width = bitmap.getWidth();
		height = bitmap.getHeight();
		this.setFocusable(true);
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int firstPointPostX = 0, firstPointPostY = 0;
		int secondPointPostX = 0, secondPointPostY = 0;
		int distanceX = 0, distanceY = 0;
		int pointerCount = event.getPointerCount();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
			if (pointerCount >= 2) {
				if (isFirst) {
					firstPointPreX = (int) event.getX(0);
					firstPointPreY = (int) event.getY(0);
					secondPointPreX = (int) event.getX(1);
					secondPointPreY = (int) event.getY(1);
					this.distanceX = Math.abs(firstPointPreX - secondPointPreX);
					this.distanceY = Math.abs(firstPointPreY - secondPointPreY);
					isFirst = false;
				} else {
					firstPointPostX = (int) event.getX(0);
					firstPointPostY = (int) event.getY(0);
					secondPointPostX = (int) event.getX(1);
					secondPointPostY = (int) event.getY(1);
					distanceX = Math.abs(firstPointPostX - secondPointPostX);
					distanceY = Math.abs(firstPointPostY - secondPointPostY);
					scaleX = distanceX / this.distanceX;
					scaleY = distanceY / this.distanceY;
					this.distanceX = distanceX;
					this.distanceY = distanceY;
				}
				if (scaleX > 10)
					scaleX = 10;
				if (scaleX < 0.1)
					scaleX = 0.1f;
				if (scaleY > 10)
					scaleY = 10;
				if (scaleY < 0.1)
					scaleY = 0.1f;
				isScale = true;
			}
			break;
		case MotionEvent.ACTION_UP:
			isFirst = true;
			break;
		}
		postInvalidate();
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		matrix.reset();
		if (isScale) {
			matrix.postScale(scaleX, scaleY);
		}
		if (isTranlate) {
			matrix.setSkew(sx, sy);
		}
		if (isRotate) {
			matrix.postRotate(18);
		}
		Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		width = bitmap2.getWidth();
		height = bitmap2.getHeight();
		bitmap = Bitmap.createBitmap(bitmap2, 0, 0, width, height);
		int viewHeight = (getHeight() - height) / 2;
		int viewWidth = (getWidth() - width) / 2;
		canvas.drawBitmap(bitmap2, viewWidth, viewHeight, null);
	}

}

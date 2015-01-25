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
	private int width = 2, height = 2;
	private float scale = 1.0f;
	private boolean isScale = false;

	private int viewHeight = 0;
	private int viewWidth = 0;

	private int firstPointPreX = 0, firstPointPreY = 0;
	private int secondPointPreX = 0, secondPointPreY = 0;
	private float distance = 0;

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
		int distance = 0;
		int pointerCount = event.getPointerCount();
		System.out.println(pointerCount);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (pointerCount >= 2) {
				firstPointPreX = (int) event.getX(0);
				firstPointPreY = (int) event.getY(0);
				secondPointPreX = (int) event.getX(1);
				secondPointPreY = (int) event.getY(1);
				this.distance = Math.abs(firstPointPreX - secondPointPreX);
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (pointerCount >= 2) {
				// firstPointPostX = (int) event.getX(0);
				// firstPointPostY = (int) event.getY(0);
				// secondPointPostX = (int) event.getX(1);
				// secondPointPostY = (int) event.getY(1);
				// distance = Math.abs(firstPointPostX - secondPointPostX);
				// scale = distance / this.distance;
				// isScale = true;
			}
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		postInvalidate();
		return true;
	}

	@SuppressWarnings("static-access")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		viewHeight = getHeight();
		viewWidth = getWidth();
		matrix.reset();
		if (!isScale) {
			matrix.setSkew(sx, sy);
		} else {
			matrix.setScale(scale, scale);
		}
		Bitmap bitmap2 = bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		matrix.setTranslate((viewWidth - width) / 2, (viewHeight - height) / 2);
		canvas.drawBitmap(bitmap2, matrix, null);
	}

}

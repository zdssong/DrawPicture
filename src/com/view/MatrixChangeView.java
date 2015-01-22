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

public class MatrixChangeView extends View {

	private Bitmap bitmap;
	private Matrix matrix = new Matrix();
	private float sx = 0.0f;
	private int width, height;
	private float scale = 1.0f;
	private boolean isScale = false;

	private int viewHeight = 0;
	private int viewWidth = 0;

	public MatrixChangeView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		bitmap = ((BitmapDrawable) context.getResources().getDrawable(
				R.drawable.ic_launcher)).getBitmap();
		width = bitmap.getWidth();
		height = bitmap.getHeight();
		this.setFocusable(true);
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
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			isScale = false;
			sx += 0.1;
			postInvalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		default:
			break;
		}
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
			matrix.setSkew(sx, 0);
		} else {
			matrix.setScale(scale, scale);
		}
		Bitmap bitmap2 = bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		matrix.setTranslate((viewWidth - width) / 2, (viewHeight - height) / 2);
		canvas.drawBitmap(bitmap2, matrix, null);
	}

}

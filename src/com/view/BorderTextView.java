package com.view;

import com.drawpicture.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class BorderTextView extends TextView {
	private Paint borderPaint;
	private boolean topBorder;
	private boolean bottomBorder;
	private boolean rightBorder;
	private boolean leftBorder;

	public BorderTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initPaint();
		TypedArray param = context.obtainStyledAttributes(attrs,
				R.styleable.BorderTestView);
		topBorder = param
				.getBoolean(R.styleable.BorderTestView_topBorder, true);
		bottomBorder = param.getBoolean(
				R.styleable.BorderTestView_bottomBorder, true);
		rightBorder = param.getBoolean(R.styleable.BorderTestView_rightBorder,
				true);
		leftBorder = param.getBoolean(R.styleable.BorderTestView_leftBorder,
				true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		if (topBorder == true) {
			canvas.drawLine(0, 0, getWidth() - 1, 0, borderPaint);
		}
		if (bottomBorder == true) {
			canvas.drawLine(0, getHeight() - 1, getWidth() - 1,
					getHeight() - 1, borderPaint);
		}
		if (rightBorder == true) {
			canvas.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1,
					borderPaint);
		}
		if (leftBorder == true) {
			canvas.drawLine(0, 0, 0, getHeight() - 1, borderPaint);
		}
		super.onDraw(canvas);
	}

	@SuppressLint("ResourceAsColor")
	private void initPaint() {
		borderPaint = new Paint();
		borderPaint.setColor(R.color.black);
	}

	public void setTopBorder(boolean topBorder) {
		this.topBorder = topBorder;
		invalidate();
	}

	public void setBottomBorder(boolean bottomBorder) {
		this.bottomBorder = bottomBorder;
		invalidate();
	}

	public void setRightBorder(boolean rightBorder) {
		this.rightBorder = rightBorder;
		invalidate();
	}

	public void setLeftBorder(boolean leftBorder) {
		this.leftBorder = leftBorder;
		invalidate();
	}

}

package com.view;

import com.drawpicture.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * 该View目前只有扭曲部分图片的功能
 * */
public class WarpImageView extends View {
	private Bitmap bitmap;
	// 定义两个常量，这两个常量指定该图片横向，纵向上被划分为20格
	private final int WIDTH = 20;
	private final int HEIGHT = 20;
	// 记录该图片上包含的441个点
	private final int COUNT = (WIDTH + 1) * (HEIGHT + 1);
	// 定义一个数组，保存Bitmap上的21*21个点的坐标
	private final float[] verts = new float[COUNT * 2];
	// 定义一个数组，记录Bitmap上21*21个点经过扭曲后的坐标
	// 对图片进行扭曲的关键就是修改该数组里元素的值
	private final float[] orig = new float[COUNT * 2];

	public WarpImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setFocusable(true);
		bitmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.jinta);
		float bitmapWidth = bitmap.getWidth();
		float bitmapHeight = bitmap.getHeight();
		int index = 0;
		for (int y = 0; y <= HEIGHT; y++) {
			float fy = bitmapHeight * y / HEIGHT;
			for (int x = 0; x <= WIDTH; x++) {
				float fx = bitmapWidth * x / WIDTH;
				// 初始化orig、verts数组。初始化后，orig、verts两个数组均匀的保存了21*21个点的x，y坐标
				orig[index * 2 + 0] = verts[index * 2 + 0] = fx;
				orig[index * 2 + 1] = verts[index * 2 + 1] = fy;
				index += 1;
			}
		}
	}

	private void warp(float cx, float cy) {
		for (int i = 0; i < COUNT * 2; i += 2) {
			float dx = cx - orig[i + 0];
			float dy = cy - orig[i + 1];
			float dd = dx * dx + dy * dy;
			// 计算每一个坐标与当前点（cx，cy）之间的距离
			float d = (float) Math.sqrt(dd);
			// 计算扭曲度，距离当前点（cx，cy）越远，扭曲度越小
			float pull = 80000 / ((float) (dd * d));
			// 对verts数据（保存Bitmap上21*21个点经过扭曲后的坐标）重新赋值
			if (pull >= 1) {
				verts[i + 0] = cx;
				verts[i + 1] = cy;
			} else {
				// 控制个顶点想触摸事件发生偏移
				verts[i + 0] = orig[i + 0] + dx*pull;
				verts[i + 1] = orig[i + 1] + dy*pull;
			}
		}
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		warp(event.getX(), event.getY());
		return true;
	}

}

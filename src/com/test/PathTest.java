package com.test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class PathTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(new MyView(this));
	}

	class MyView extends View {
		float phase;
		PathEffect[] effects = new PathEffect[7];
		int[] colors;
		private Paint paint;
		Path path;

		public MyView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			paint = new Paint();
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(4);
			path = new Path();
			path.moveTo(0, 0);
			for (int i = 1; i <= 35; i++) {
				path.lineTo(i * 20, (float) Math.random() * 60);
			}
			colors = new int[] { Color.BLACK, Color.BLUE, Color.CYAN,
					Color.GREEN, Color.MAGENTA, Color.RED, Color.YELLOW };
		}

		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);
			canvas.drawColor(Color.WHITE);
			effects[0] = null;
			effects[1] = new CornerPathEffect(10);
			effects[2] = new DiscretePathEffect(3.0f, 5.0f);
			effects[3] = new DashPathEffect(new float[] { 20, 10, 5, 10 },
					phase);
			Path p = new Path();
			p.addRect(0, 0, 8, 8, Path.Direction.CCW);
			effects[4] = new PathDashPathEffect(p, 12, phase,
					PathDashPathEffect.Style.ROTATE);
			effects[5] = new ComposePathEffect(effects[2], effects[4]);
			effects[6] = new SumPathEffect(effects[4], effects[3]);
			canvas.translate(8, 8);
			for (int i = 0; i < effects.length; i++) {
				paint.setPathEffect(effects[i]);
				paint.setColor(colors[i]);
				canvas.drawPath(path, paint);
				canvas.translate(0, 60);
			}
			phase += 1;
			invalidate();
		}

	}

}

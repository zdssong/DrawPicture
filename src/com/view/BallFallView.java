package com.view;

import java.util.ArrayList;

import com.drawpicture.R;
import com.model.ShapeHolder;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class BallFallView extends View implements AnimatorUpdateListener {

	static final float BALL_SIZE = 50f;
	static final float FULL_TIME = 1000;

	public final ArrayList<ShapeHolder> balls = new ArrayList<ShapeHolder>();

	public BallFallView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		// 加载动画资源
		ObjectAnimator colorAnim = (ObjectAnimator) AnimatorInflater
				.loadAnimator(context, R.animator.color_anim);
		colorAnim.setEvaluator(new ArgbEvaluator());
		// 对该View本身应用属性动画
		colorAnim.setTarget(this);
		// 开始指定动画
		colorAnim.start();
	}

	@SuppressLint("InlinedApi")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() != MotionEvent.ACTION_DOWN
				&& event.getAction() != MotionEvent.ACTION_MOVE) {
			return false;
		}
		ShapeHolder newBall = addBall(event.getX(), event.getY());
		float startY = newBall.getY();
		float endY = getHeight() - BALL_SIZE;
		float h = getHeight();
		float eventY = event.getY();
		int duration = (int) (FULL_TIME * ((h - eventY) / h));
		ValueAnimator fallAnim = ObjectAnimator.ofFloat(newBall, "y", startY,
				endY);
		fallAnim.setDuration(duration);
		fallAnim.setInterpolator(new AccelerateInterpolator());
		// fallAnim.addUpdateListener(this);

		ValueAnimator squashAnim1 = ObjectAnimator.ofFloat(newBall, "x",
				newBall.getX(), newBall.getX() - BALL_SIZE / 2);
		squashAnim1.setDuration(duration / 4);
		squashAnim1.setRepeatCount(1);
		squashAnim1.setRepeatMode(ValueAnimator.REVERSE);
		squashAnim1.setInterpolator(new DecelerateInterpolator());

		ValueAnimator squashAnim2 = ObjectAnimator.ofFloat(newBall, "width",
				newBall.getWidth(), newBall.getWidth() + BALL_SIZE);
		squashAnim2.setDuration(duration / 4);
		squashAnim2.setRepeatCount(1);
		squashAnim2.setRepeatMode(ValueAnimator.REVERSE);
		squashAnim2.setInterpolator(new DecelerateInterpolator());

		ObjectAnimator stretchAnim1 = ObjectAnimator.ofFloat(newBall, "y",
				endY, endY + BALL_SIZE / 2);
		stretchAnim1.setDuration(duration / 4);
		stretchAnim1.setRepeatCount(1);
		stretchAnim1.setRepeatMode(ValueAnimator.REVERSE);
		stretchAnim1.setInterpolator(new DecelerateInterpolator());

		ValueAnimator stretchAnim2 = ObjectAnimator.ofFloat(newBall, "height",
				newBall.getHeight(), newBall.getHeight() - BALL_SIZE / 2);
		stretchAnim2.setDuration(duration / 4);
		stretchAnim2.setRepeatCount(1);
		stretchAnim2.setRepeatMode(ValueAnimator.REVERSE);
		stretchAnim2.setInterpolator(new DecelerateInterpolator());

		ObjectAnimator bounceBackAnim = ObjectAnimator.ofFloat(newBall, "y",
				endY, startY);
		bounceBackAnim.setDuration(duration);
		bounceBackAnim.setInterpolator(new DecelerateInterpolator());

		AnimatorSet bouncer = new AnimatorSet();
		bouncer.play(fallAnim).before(squashAnim1);
		bouncer.play(squashAnim1).with(squashAnim2);
		bouncer.play(squashAnim1).with(stretchAnim1);
		bouncer.play(squashAnim1).with(stretchAnim2);
		bouncer.play(bounceBackAnim).after(stretchAnim2);

		ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(newBall, "alpha", 1f,
				0f);
		fadeAnim.setDuration(250);
		fadeAnim.addListener(new AnimatorListenerAdapter() {

			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				balls.remove(((ObjectAnimator) animation).getTarget());
				super.onAnimationEnd(animation);
			}

		});
		// fadeAnim.addUpdateListener(this);
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.play(bouncer).before(fadeAnim);
		animatorSet.start();
		return true;
	}

	private ShapeHolder addBall(float x, float y) {
		OvalShape circle = new OvalShape();
		circle.resize(BALL_SIZE, BALL_SIZE);
		ShapeDrawable drawable = new ShapeDrawable(circle);
		ShapeHolder shapeHolder = new ShapeHolder(drawable);
		shapeHolder.setX(x - BALL_SIZE / 2);
		shapeHolder.setY(y - BALL_SIZE / 2);
		int red = (int) (Math.random() * 255);
		int green = (int) (Math.random() * 255);
		int blue = (int) (Math.random() * 255);
		int color = 0xff000000 + red << 16 | green << 8 | blue;
		Paint paint = drawable.getPaint();
		int darkColor = 0xff000000 | red / 4 << 16 | green / 4 << 8 | blue / 4;
		RadialGradient gradient = new RadialGradient(37.5f, 12.5f, BALL_SIZE,
				color, darkColor, Shader.TileMode.CLAMP);
		paint.setShader(gradient);
		shapeHolder.setPaint(paint);
		balls.add(shapeHolder);
		return shapeHolder;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		for (ShapeHolder holder : balls) {
			canvas.save();
			canvas.translate(holder.getX(), holder.getY());
			holder.getShape().draw(canvas);
			canvas.restore();
		}
		super.onDraw(canvas);
	}

	@Override
	public void onAnimationUpdate(ValueAnimator animation) {
		// TODO Auto-generated method stub
		this.invalidate();
	}

}

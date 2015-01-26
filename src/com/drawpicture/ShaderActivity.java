package com.drawpicture;

import com.view.ShaderView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

public class ShaderActivity extends Activity implements OnClickListener {

	private Shader[] shaders = new Shader[5];
	private int[] colors;
	ShaderView shaderView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_shader);
		shaderView = (ShaderView) findViewById(R.id.shaderView);
		Bitmap bm = BitmapFactory.decodeResource(getResources(),
				R.drawable.water);
		colors = new int[] { Color.RED, Color.GREEN, Color.BLUE, };
		shaders[0] = new BitmapShader(bm, TileMode.REPEAT, TileMode.MIRROR);
		shaders[1] = new LinearGradient(0, 0, 100, 100, colors, null,
				TileMode.REPEAT);
		shaders[2] = new RadialGradient(100, 100, 80, colors, null,
				TileMode.REPEAT);
		shaders[3] = new SweepGradient(160, 160, colors, null);
		shaders[4] = new ComposeShader(shaders[2], shaders[3],
				PorterDuff.Mode.DARKEN);
		initListener();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bn1:
			shaderView.paint.setShader(shaders[0]);
			break;
		case R.id.bn2:
			shaderView.paint.setShader(shaders[1]);
			break;
		case R.id.bn3:
			shaderView.paint.setShader(shaders[2]);
			break;
		case R.id.bn4:
			shaderView.paint.setShader(shaders[3]);
			break;
		case R.id.bn5:
			shaderView.paint.setShader(shaders[4]);
			break;
		}
		shaderView.invalidate();
	}

	private void initListener() {
		findViewById(R.id.bn1).setOnClickListener(this);
		findViewById(R.id.bn2).setOnClickListener(this);
		findViewById(R.id.bn3).setOnClickListener(this);
		findViewById(R.id.bn4).setOnClickListener(this);
		findViewById(R.id.bn5).setOnClickListener(this);
	}

}

package com.drawpicture;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MatrixChangeViewActivity extends Activity {

//	private MatrixChangeView matrixChangeView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_matrixchange);

		initView();
	}

	private void initView() {
//		matrixChangeView = (MatrixChangeView) findViewById(R.id.matrixchangeview);
	}

}

package com.drawpicture;

import com.view.BallFallView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;

public class BallFallActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_ballfall);
		LinearLayout container = (LinearLayout) findViewById(R.id.container);
		container.addView(new BallFallView(this));
	}

}

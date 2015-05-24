package com.rao.kiran.square;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.rao.kiran.tools.IntentTools;

public class MainMenu extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupLayout();
	}

	private void setupLayout() {
		setContentView (R.layout.main);
		Button temp = (Button) findViewById (R.id.bStart);
		temp.setOnClickListener(this);
		temp = (Button) findViewById (R.id.bPrefs);
		temp.setOnClickListener(this);
		temp = (Button) findViewById (R.id.bAbout);
		temp.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bStart :
			IntentTools.startActivityFromClassName(MainMenu.this, getString(R.string.package_name) + ".GameActivity");
			break;
		case R.id.bPrefs :
			IntentTools.startActivityFromClassName(MainMenu.this, getString(R.string.package_name) + ".Preferences");
			break;
		case R.id.bAbout :
			IntentTools.startActivityFromClassName(MainMenu.this, getString(R.string.package_name) + ".About");
			break;
		}
	}

}

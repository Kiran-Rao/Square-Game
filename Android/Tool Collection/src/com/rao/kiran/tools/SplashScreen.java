package com.rao.kiran.tools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class SplashScreen extends Activity implements Runnable {

	boolean proceed = true;
	int backgroundId;
	int time;
	Intent startMainMenu;

	/**
	 * Called when the activity is first created. Requests activity to be full
	 * screen. Sets the view to the layout splash.xml. Starts a thread to wait 5
	 * seconds, then call the Main Activity.
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getInfoFromBundle();
		setupLayout();

		Thread timer = new Thread(this);
		timer.start();
	}

	private void setupLayout() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		LinearLayout splashLayout = new LinearLayout(this);
		splashLayout.setOrientation(LinearLayout.VERTICAL);
		splashLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		splashLayout.setBackgroundResource(backgroundId);
		setContentView(splashLayout);
	}

	private void getInfoFromBundle() {
		Bundle bundle = getIntent().getExtras();
		backgroundId = bundle.getInt("bgId",
				R.drawable.default_splash_background);
		time = bundle.getInt("time", 5000);
		startMainMenu = new Intent(bundle.getString("nextIntent"));
	}

	@Override
	protected void onPause() {
		proceed = false;
		super.onPause();
	}

	/**
	 * Called when Thread t starts. Pauses Thread t for 5 seconds then calls
	 * startMainActivity method.
	 */
	@Override
	public void run() {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			startMainActivity();
		}
	}

	/**
	 * Starts the MainActivity (Main Menu) through an intent.
	 */
	private void startMainActivity() {
		if (proceed) {
			startActivity(startMainMenu);
		}
		finish();
	}
}
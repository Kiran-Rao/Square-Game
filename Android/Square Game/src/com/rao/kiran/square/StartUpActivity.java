package com.rao.kiran.square;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartUpActivity extends Activity {
	/**
	 * Called when the Activity is first created.
	 * Starts the splash activity, then destroys itself.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = new Bundle();
		bundle.putInt("bgId", R.drawable.splash2);
		bundle.putInt("time", 2000);
		bundle.putString("nextIntent", getString(R.string.package_name)
				.toString() + ".TITLEMENU");
		//bundle.putInt("song", R.raw.carl_tutton);
		Intent splashIntent = new Intent("com.rao.kiran.tools.SPLASHSCREEN");
		splashIntent.putExtras(bundle);
		startActivity(splashIntent);
		finish();
	}
}
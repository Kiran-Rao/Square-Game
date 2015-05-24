package com.rao.kiran.square;

import com.rao.kiran.tools.GameView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;

public class GameActivity extends Activity implements SensorEventListener {

	private static final float SIZE = 75;
	
	protected Sensor sensor;
	protected SensorManager sensorManager;

	private float x, y = 0;
	private float speedX, speedY = 0;
	private float screenX, screenY = 0;
	private int score = 0;
	private int highScore;
	private MediaPlayer mPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new SquareGameView(this));
		sensorSetup();
		mPlayer = MediaPlayer.create(GameActivity.this, R.raw.carl_tutton);
	}
	
	private void sensorSetup() {
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE).size() != 0) {
			sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		} else {
			finish();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
		//mPlayer.stop();
	}

	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(this, sensor,
				SensorManager.SENSOR_DELAY_FASTEST);
		//mPlayer.start();
	}

	class SquareGameView extends GameView {

		private Paint textPaint, blackPaint;

		public SquareGameView(Context context) {
			super(context);
			textPaint = new Paint (Color.BLACK);
			textPaint.setTextSize(18);
			blackPaint = new Paint(Color.BLACK);
			new Paint(Color.argb(50, 255, 255, 255));
		}

		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			screenX = canvas.getWidth();
			screenY = canvas.getHeight();
			canvas.drawColor(Color.RED);
			canvas.drawRect(x, y, x + SIZE, y + SIZE, blackPaint);
			canvas.drawText("Score: " + score, 1.0f, 20.0f, textPaint);
			canvas.drawText("High Score: " + highScore, 1.0f, 40.0f, textPaint);
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int val) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
		score++;

		speedX += (speedX > 0) ? 0.02f : -0.02f;
		speedY += (speedY > 0) ? 0.02f : -0.02f;

		if ((speedX < 0) != (event.values[1] < 0.01)) {
			speedX = -speedX;
		}
		if ((speedY < 0) != (event.values[0] < 0.01)){
			speedY = -speedY;
		}

		x += speedX;
		y += speedY;

		if (x < 0) {
			reset();
			x = 0;
		} else if (x + SIZE > screenX) {
			reset();
			x = screenX - SIZE;
		}
		if (y < 0) {
			reset();
			y = 0;
		} else if (y + SIZE > screenY) {
			reset();
			y = screenY - SIZE;
		}
		
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void reset() {
		highScore = (score > highScore)? score : highScore;
		score = 0;
		speedX = 0;
		speedY = 0;
	}

}

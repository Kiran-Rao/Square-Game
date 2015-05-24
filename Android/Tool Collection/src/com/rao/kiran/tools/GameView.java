package com.rao.kiran.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author Kiran Rao
 *
 */
public abstract class GameView extends SurfaceView implements Callback {
	
	protected GameThread gThread;
	
	public GameView(Context context) {
		super(context);
		getHolder().addCallback(this);
		gThread = new GameThread(getHolder(), this);
	}
	
	public GameView(Context context, GameThread gameThread) {
		super(context);
		getHolder().addCallback(this);
		gThread = gameThread;
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (gThread.getState() == Thread.State.TERMINATED) {
			gThread = new GameThread(getHolder(), this);
		}
		gThread.startRunning();
		gThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		gThread.stopRunning();
		while (true)
			try {
				gThread.join();
				break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	@Override
	protected abstract void onDraw(Canvas canvas);

}

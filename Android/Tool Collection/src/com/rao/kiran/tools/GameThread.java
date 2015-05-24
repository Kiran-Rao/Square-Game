package com.rao.kiran.tools;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

	protected SurfaceHolder sHolder;
	protected GameView gView;
	private boolean run;

	/**
	 * Constructor for GameThread
	 * @param sHolder Surface holder for the surfaceView class that will be 
	 * @param gView
	 */
	public GameThread(SurfaceHolder sHolder, GameView gView) {
		this.sHolder = sHolder;
		this.gView = gView;
	}
	
	public GameThread(SurfaceHolder sHolder, GameView gView, int frameRate) {
		this.sHolder = sHolder;
		this.gView = gView;
	}

	public void startRunning() {
		run = true;
	}
	
	public void stopRunning () {
		run = false;
	}

	public void run() {
		Canvas c;
		while (run) {
			if (!sHolder.getSurface().isValid())
				continue;
			
			c = null;
			try {
				c = sHolder.lockCanvas();
				synchronized (sHolder) {
					if (c!= null)
						gView.onDraw(c);
					
				}
			} finally {
				if (c != null) {
					sHolder.unlockCanvasAndPost(c);
				}
				//cFPS.threadPause();
			}
		}
	}
}

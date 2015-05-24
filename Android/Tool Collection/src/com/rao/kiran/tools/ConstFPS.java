package com.rao.kiran.tools;

/**
 * A class to enable a stable framerate. Once constructed, the threadWait()
 * method should be called once per iteration of the loop.
 * 
 * @author Kiran Rao
 * @version 1.0, May 26, 2012
 */
public class ConstFPS {

	/** Time (milliseconds) when the last frame was updated. */
	protected long last;

	/** Pre-calculated time between frames */
	protected int timeBetweenFrames;

	/**
	 * Constructs class based on default speed of 30 FPS.
	 */
	public ConstFPS() {
		last = System.currentTimeMillis();
		timeBetweenFrames = 1000 / 30;
	}

	/**
	 * Constructs class based on user defined speed.
	 * 
	 * @param framesPerSecond
	 *            Target number of frames per second.
	 */
	public ConstFPS(int framesPerSecord) {
		last = System.currentTimeMillis();
		timeBetweenFrames = 1000 / framesPerSecord;
	}

	/**
	 * Constructs class based on user defined speed.
	 * 
	 * @param timeBetweenFrames
	 *            Time (milliseconds) between frames.
	 */
	public ConstFPS(long timeBetweenFrames) {
		last = System.currentTimeMillis();
		this.timeBetweenFrames = (int) timeBetweenFrames;
	}

	/**
	 * Method pauses
	 */
	public void threadPause() {
		long current = System.currentTimeMillis();
		if (current < last + timeBetweenFrames) {
			try {
				Thread.sleep((timeBetweenFrames + last) - current);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		last = current;
	}
}
package com.estrelsteel.engine2.window;

public class Stats {
	private int ticks;
	private int frames;
	private int fps;
	private int tps;
	private boolean running;
	private boolean applet;
	
	public Stats() {
		this.ticks = 0;
		this.frames = 0;
		this.fps = 0;
		this.tps = 0;
		this.running = false;
		this.applet = false;
	}
	
	/**
	 * Gets the total ticks that have occurred in the current session.
	 * @return the tick count
	 */
	public int getTicks() {
		return ticks;
	}
	
	/**
	 * Gets the total frames that have occurred in the current session.
	 * @return the frame count
	 */
	public int getFrames() {
		return frames;
	}
	
	/**
	 * Gets the amount of frames in the last second.
	 * @return the fps
	 */
	public int getFPS() {
		return fps;
	}
	
	/**
	 * Gets the amount of ticks in the last second.
	 * @return the tps
	 */
	public int getTPS() {
		return tps;
	}
	
	/**
	 * Gets if the session is still running
	 * @return state of running
	 */
	public boolean isRunning() {
		return running;
	}
	
	/**
	 * Gets if the session is an applet
	 * @return state of being an applet
	 */
	public boolean isApplet() {
		return applet;
	}
	
	/**
	 * Sets the tick count of the session
	 * @param ticks
	 * @return void
	 */
	public void setTicks(int ticks) {
		this.ticks = ticks;
	}
	
	/**
	 * Sets the frame count of the session
	 * @param frames
	 * @return void
	 */
	public void setFrames(int frames) {
		this.frames = frames;
	}
	
	/**
	 * Sets the frame count for the last second.
	 * @param fps
	 * @return void
	 */
	public void setFPS(int fps) {
		this.fps = fps;
	}
	
	/**
	 * Sets the tick count for the last second.
	 * @param tps
	 * @return void
	 */
	public void setTPS(int tps) {
		this.tps = tps;
	}
	
	/**
	 * Sets if the session is running
	 * @param running
	 * @return void
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	/**
	 * Sets if the session is an applet
	 * @param applet
	 * @return void
	 */
	public void setApplet(boolean applet) {
		this.applet = applet;
	}
}

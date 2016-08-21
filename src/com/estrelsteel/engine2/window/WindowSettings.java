package com.estrelsteel.engine2.window;

import com.estrelsteel.engine2.shape.rectangle.AbstractedRectangle;

public class WindowSettings {
	private AbstractedRectangle rect;
	private final String TITLE;
	private final String VERSION;
	private final Integer BUILD;
	
	public WindowSettings(AbstractedRectangle rect, String TITLE, String VERSION, Integer BUILD) {
		this.rect = rect;
		this.TITLE = TITLE;
		this.VERSION = VERSION;
		this.BUILD = BUILD;
	}
	
	/**
	 * Returns the rectangle which is the window's size
	 * @return the window's size
	 * @see AbstractedRectangle
	 */
	public AbstractedRectangle getRectangle() {
		return rect;
	}
	
	/**
	 * Gets the title of the session
	 * @return window title
	 */
	public String getTitle() {
		return TITLE;
	}
	
	/**
	 * Gets the version of the session
	 * @return the version
	 */
	public String getVersion() {
		return VERSION;
	}
	
	/**
	 * Gets the build of the session
	 * @return the build number
	 */
	public Integer getBuild() {
		return BUILD;
	}
	
	public boolean equals(Object other) {
		if(TITLE.equals(((WindowSettings) other).getTitle()) && VERSION.equals(((WindowSettings) other).getVersion()) && BUILD.equals(((WindowSettings) other).getBuild())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Sets the size of the window.
	 *
	 */
	public void setRectangle(AbstractedRectangle rect) {
		this.rect = rect;
	}
}

package com.estrelsteel.engine2.image;

import java.awt.image.BufferedImage;

import com.estrelsteel.engine2.image.Image;
import com.estrelsteel.engine2.shape.rectangle.AbstractedRectangle;

public class ConfinedImage extends Image {

	private AbstractedRectangle loc;
	
	public ConfinedImage(String src, AbstractedRectangle loc) {
		super(src);
		this.loc = loc;
	}
	
	public BufferedImage getImage() {
		return super.getImage().getSubimage((int) loc.getTop().getX(), (int) loc.getTop().getY(), (int) loc.getWidth(), (int) loc.getHeight());
	}
	
	public AbstractedRectangle getLocation() {
		return loc;
	}
	
	public void setLocation(AbstractedRectangle loc) {
		this.loc = loc;
	}

}

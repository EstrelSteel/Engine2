package com.estrelsteel.engine2.shape.rectangle;

import com.estrelsteel.engine2.point.AbstractedPoint;
import com.estrelsteel.engine2.shape.CollideArea;

public class RectangleCollideArea implements CollideArea {

	private Rectangle rect;
	
	public RectangleCollideArea(Rectangle rect) {
		this.rect = rect;
	}
	
	@Override
	public boolean checkCollision(AbstractedPoint point) {
		if(rect.getTop().getX() <= point.getX()
				&& rect.getTop().getX() + rect.getWidth() >= point.getX()
				&& rect.getTop().getY() <= point.getY()
				&& rect.getTop().getY() + rect.getHeight() >= point.getY() ) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkCollision(AbstractedRectangle collide) {
		if(checkCollision(collide.getTop()) || checkCollision(collide.getBottom())) {
			return true;
		}
		return false;
	}
	
}
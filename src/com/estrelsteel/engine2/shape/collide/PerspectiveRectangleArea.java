package com.estrelsteel.engine2.shape.collide;

import com.estrelsteel.engine2.point.AbstractedPoint;
import com.estrelsteel.engine2.shape.rectangle.AbstractedRectangle;
import com.estrelsteel.engine2.shape.rectangle.Rectangle;

public class PerspectiveRectangleArea implements CollideArea {

	private Rectangle rect;
	
	public PerspectiveRectangleArea(Rectangle rect) {
		this.rect = rect;
	}
	
	public Rectangle getRectangle() {
		return rect;
	}
	
	@Override
	public boolean checkCollision(AbstractedPoint point) {
		if(rect.getTop().getX()<= point.getX()
				&& rect.getTop().getX() + rect.getWidth() >= point.getX()
				&& rect.getTop().getY() * 0.8  <= point.getY()
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
	
	public void setRectangle(Rectangle rect) {
		this.rect = rect;
	}
	
}

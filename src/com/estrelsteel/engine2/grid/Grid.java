package com.estrelsteel.engine2.grid;

import com.estrelsteel.engine2.point.Point2;
import com.estrelsteel.engine2.shape.rectangle.AbstractedRectangle;
import com.estrelsteel.engine2.shape.rectangle.Rectangle;

public class Grid {
	private AbstractedRectangle rect;
	
	public Grid(AbstractedRectangle rect) {
		this.rect = rect;
	}
	
	public AbstractedRectangle getRectangle() {
		return rect;
	}
	
	public boolean equals(Object other) {
		if(rect.equals(((Grid) other).getRectangle())) {
			return true;
		}
		return false;
	}
	
	public Point2 moveToGrid(Point2 point) {
		Point2 newPoint = new Point2(0, 0, this);
		newPoint.setX(point.getX() * point.getGrid().getRectangle().getWidth() / rect.getWidth());
		newPoint.setY(point.getY() * point.getGrid().getRectangle().getHeight() / rect.getHeight());
		return newPoint;
	}
	
	public Rectangle moveToGrid(Rectangle rect) {
		Rectangle newRect = new Rectangle(new Point2(0, 0, this), new Point2(0, 0, this));
		newRect.setRotation(rect.getRotation());
		newRect.setTop(moveToGrid(rect.getTop()));
		newRect.setBottom(moveToGrid(rect.getBottom()));
		return newRect;
	}
	
	public void setRectangle(AbstractedRectangle rect) {
		this.rect = rect;
	}
}

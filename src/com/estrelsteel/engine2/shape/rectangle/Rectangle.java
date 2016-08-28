package com.estrelsteel.engine2.shape.rectangle;

import com.estrelsteel.engine2.point.Point2;
import com.estrelsteel.engine2.shape.Rotation;

public class Rectangle extends AbstractedRectangle {
	
	public Rectangle(Point2 top, Point2 bottom) {
		super(top, bottom);
	}
	
	public Rectangle(Point2 top, Point2 bottom, Rotation rotation) {
		super(top, bottom, rotation);
	}
	
	public Point2 getTop() {
		return (Point2) super.getTop();
	}
	
	public Point2 getBottom() {
		return (Point2) super.getBottom();
	}
	
	public double getX() {
		return getTop().getX();
	}
	
	public double getY() {
		return getTop().getY();
	}
	
	public boolean equals(Object other) {
		if(((Point2) getTop()).equals(((Rectangle) other).getTop()) 
				&& ((Point2) getBottom()).equals(((Rectangle) other).getBottom())) {
			return true;
		}
		return false;
	}
}

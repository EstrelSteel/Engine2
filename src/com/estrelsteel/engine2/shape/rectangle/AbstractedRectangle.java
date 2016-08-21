package com.estrelsteel.engine2.shape.rectangle;

import com.estrelsteel.engine2.point.AbstractedPoint;
import com.estrelsteel.engine2.shape.Rotation;

public class AbstractedRectangle {
	private AbstractedPoint top;
	private AbstractedPoint bottom;
	private Rotation rotation;
	
	public AbstractedRectangle(AbstractedPoint top, AbstractedPoint bottom) {
		this.top = top;
		this.bottom = bottom;
		this.rotation = new Rotation(0.0);
	}
	
	public AbstractedRectangle(AbstractedPoint top, AbstractedPoint bottom, Rotation rotation) {
		this.top = top;
		this.bottom = bottom;
		this.rotation = rotation;
	}
	
	public AbstractedPoint getTop() {
		return top;
	}
	
	public AbstractedPoint getBottom() {
		return bottom;
	}
	
	public Rotation getRotation() {
		return rotation;
	}
	
	public double getWidth() {
		return top.getWidth(bottom);
	}
	
	public double getHeight() {
		return top.getHeight(bottom);
	}
	
	public double getX() {
		return top.getX();
	}
	
	public double getY() {
		return top.getY();
	}
	
	public boolean equals(Object other) {
		if(top.equals(((AbstractedRectangle) other).getTop()) && bottom.equals(((AbstractedRectangle) other).getBottom())) {
			return true;
		}
		return false;
	}
	
	public void setTop(AbstractedPoint top) {
		this.top = top;
	}
	
	public void setBottom(AbstractedPoint bottom) {
		this.bottom = bottom;
	}
	
	public void setRotation(Rotation rotation) {
		this.rotation = rotation;
	}
	
	public void setX(double x) {
		top.setX(x);
	}
	
	public void setY(double y) {
		top.setY(y);
	}
}

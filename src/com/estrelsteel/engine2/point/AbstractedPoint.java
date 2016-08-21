package com.estrelsteel.engine2.point;

public class AbstractedPoint {
	private double x;
	private double y;
	
	public AbstractedPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getWidth(AbstractedPoint point) {
		return Math.abs(getX() - point.getX());
	}
	
	public double getHeight(AbstractedPoint point) {
		return Math.abs(getY() - point.getY());
	}
	
	public boolean equals(Object other) {
		if(x == ((AbstractedPoint) other).getX() && y == ((AbstractedPoint) other).getY()) {
			return true;
		}
		return false;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
}

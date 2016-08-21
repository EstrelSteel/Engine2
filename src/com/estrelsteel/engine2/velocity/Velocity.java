package com.estrelsteel.engine2.velocity;

import com.estrelsteel.engine2.Engine2;
import com.estrelsteel.engine2.point.AbstractedPoint;

public class Velocity {
	private double velocityX;
	private double velocityY;
	
	public Velocity(double velocity) {
		this.velocityX = velocity;
		this.velocityY = velocity;
	}
	
	public double getVelocityX() {
		return velocityX;
	}
	
	public double getVelocityY() {
		return velocityY;
	}
	
	public AbstractedPoint applyVelocity(AbstractedPoint point) {
		double x = point.getX() + velocityX;
		double y = point.getY() + velocityY;
		x = Double.parseDouble(Engine2.ROUNDING_FORMAT.format(x));
		y = Double.parseDouble(Engine2.ROUNDING_FORMAT.format(y));
		point.setX(x);
		point.setY(y);
		return point;
	}
	
	public void setVelocityX(double velocity) {
		this.velocityX = velocity;
	}
	
	public void setVelocityY(double velocity) {
		this.velocityY = velocity;
	}
}

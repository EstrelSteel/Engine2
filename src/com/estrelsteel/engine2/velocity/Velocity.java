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
	
	public Velocity getAntiVelocity() {
		Velocity v = new Velocity(0.0);
		v.setVelocityX(-velocityX);
		v.setVelocityY(-velocityY);
		return v;
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
	
	public Velocity applyVelocity(Velocity v) {
		velocityX = velocityX - v.getVelocityX();
		velocityY = velocityY - v.getVelocityY();
		return this;
	}
	
	public Velocity setVelocityX(double velocity) {
		this.velocityX = velocity;
		return this;
	}
	
	public Velocity setVelocityY(double velocity) {
		this.velocityY = velocity;
		return this;
	}
}

package com.estrelsteel.engine2.velocity;

import com.estrelsteel.engine2.point.AbstractedPoint;

public class DecayingVelocity extends Velocity {
	
	private double startVelocityX;
	private double startVelocityY;
	private Friction friction;

	public DecayingVelocity(double velocity, Friction friction) {
		super(velocity);
		this.startVelocityX = velocity;
		this.startVelocityY = velocity;
		this.friction = friction;
	}
	
	public double getStartVelocityX() {
		return startVelocityX;
	}
	
	public double getStartVelocityY() {
		return startVelocityY;
	}
	
	public Friction getFriction() {
		return friction;
	}
	
	public boolean equals(Object other) {
		if(startVelocityX == ((DecayingVelocity) other).getStartVelocityX()
				&& startVelocityY == ((DecayingVelocity) other).getStartVelocityY() && super.equals(other)) {
			return true;
		}
		return false;
	}
	
	public AbstractedPoint applyVelocity(AbstractedPoint point) {
		point = super.applyVelocity(point);
		setVelocityX(getVelocityX() - friction.getFriction());
		setVelocityY(getVelocityY() - friction.getFriction());
		return point;
	}
	
	public void setVelocityX(double velocity) {
		startVelocityX = velocity;
		super.setVelocityX(velocity);
	}
	
	public void setVelocityY(double velocity) {
		startVelocityY = velocity;
		super.setVelocityY(velocity);
	}
	
	public void setStartVelocityX(double startVelocity) {
		this.startVelocityX = startVelocity;
	}
	
	public void setStartVelocityY(double startVelocity) {
		this.startVelocityY = startVelocity;
	}
	
	public void setFriction(Friction friction) {
		this.friction = friction;
		
	}
	
}

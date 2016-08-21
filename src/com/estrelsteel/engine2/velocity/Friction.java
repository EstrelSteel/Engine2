package com.estrelsteel.engine2.velocity;

public class Friction {
	private double friction;
	
	public Friction(double friction) {
		this.friction = friction;
	}
	
	public double getFriction() {
		return friction;
	}
	
	public boolean equals(Object other) {
		if(friction == ((Friction) other).getFriction()) {
			return true;
		}
		return false;
	}
	
	public void setFriction(double friction) {
		this.friction = friction;
	}
}

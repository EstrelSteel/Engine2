package com.estrelsteel.engine2.shape.collide;

public class Collision {
	private boolean collide;
	private CollideArea area;
	
	public Collision(boolean collide, CollideArea area) {
		this.collide = collide;
		this.area = area;
	}
	
	public CollideArea getCollideArea() {
		return area;
	}
	
	public boolean doesCollide() {
		return collide;
	}
	
	public boolean equals(Object other) {
		if(collide == ((Collision) other).doesCollide()) {
			return true;
		}
		return false;
	}
	
	public void setCollide(boolean collide) {
		this.collide = collide;
	}
	
	public void setCollideArea(CollideArea area) {
		this.area = area;
	}
}

package com.estrelsteel.engine2.actor;

import com.estrelsteel.engine2.point.Point2;
import com.estrelsteel.engine2.shape.Rotation;

public class Camera {
	private String name;
	private Point2 loc;
	private Rotation rotation;

	public Camera(String name, Point2 loc) {
		this.name = name;
		this.loc = loc;
		this.rotation = new Rotation(0.0);
	}
	
	public String getName() {
		return name;
	}
	
	public Point2 getLocation() {
		return loc;
	}
	
	public Rotation getRotation() {
		return rotation;
	}
	
	public boolean equals(Object other) {
		if(name.equals(((Camera) other).getName()) && loc.equals(((Camera) other).getLocation())
				&& rotation.equals(((Camera) other).getRotation())) {
			return true;
		}
		return false;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLocation(Point2 loc) {
		this.loc = loc;
	}
	
	public void setRotation(Rotation rotation) {
		this.rotation = rotation;
	}
}

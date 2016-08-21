package com.estrelsteel.engine2.shape.rectangle;

import com.estrelsteel.engine2.point.Point3;
import com.estrelsteel.engine2.shape.Rotation;

public class Box extends Rectangle {
	
	private Point3 depth;

	public Box(Point3 top, Point3 bottom, Point3 depth) {
		super(top, bottom);
		this.depth = depth;
	}
	
	public Box(Point3 top, Point3 bottom, Point3 depth, Rotation rotation) {
		super(top, bottom, rotation);
		this.depth = depth;
	}
	
	public Point3 getTop() {
		return (Point3) super.getTop();
	}
	
	public Point3 getBottom() {
		return (Point3) super.getBottom();
	}
	
	public Point3 getDepth() {
		return depth;
	}
	
	public double getX() {
		return getTop().getX();
	}
	
	public double getY() {
		return getTop().getY();
	}
	
	public double getZ() {
		return getTop().getZ();
	}
	
	public boolean equals(Object other) {
		if(depth.equals(((Box) other).getDepth()) && super.equals(other)) {
			return true;
		}
		return false;
	}
	
	public void setDepth(Point3 depth) {
		this.depth = depth;
	}

}

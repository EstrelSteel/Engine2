package com.estrelsteel.engine2.point;

import com.estrelsteel.engine2.grid.Grid;

public class Point3 extends Point2 {
	private double z;
	
	public Point3(double x, double y, double z, Grid grid) {
		super(x, y, grid);
		this.z = z;
	}
	
	public double getZ() {
		return z;
	}
	
	public double getDepth(Point3 point) {
		return Math.abs(z - point.getZ());
	}
	
	public boolean equals(Object other) {
		if(z == ((Point3) other).getZ()) {
			return super.equals(other);
		}
		return false;
	}
	
	public void setZ(double z) {
		this.z = z;
	}
}

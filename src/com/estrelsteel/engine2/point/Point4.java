package com.estrelsteel.engine2.point;

import com.estrelsteel.engine2.grid.Grid;

public class Point4 extends Point3 {
	private double w;
	
	public Point4(double x, double y, double z, double w, Grid grid) {
		super(x, y, z, grid);
		this.w = w;
	}
	
	public double getW() {
		return w;
	}
	
	public double get4Distance(Point4 point) {
		return Math.abs(w - point.getW());
	}
	
	public boolean equals(Object other) {
		if(w == ((Point4) other).getW()) {
			return super.equals(other);
		}
		return false;
	}
	
	public void setW(double w) {
		this.w = w;
	}
}

package com.estrelsteel.engine2.point;

import com.estrelsteel.engine2.grid.Grid;

public class Point2 extends AbstractedPoint {
	
	private Grid grid;
	
	public Point2(double x, double y, Grid grid) {
		super(x, y);
		this.grid = grid;
	}
	
	public Grid getGrid() {
		return grid;
	}
	
//	public void translate(double x, double y) {
//		setX(getX() + x);
//		setY(getY() + y);
//	}
	
	public boolean equals(Object other) {
		if(grid.equals(((Point2) other).getGrid())) {
			return super.equals(other);
		}
		return false;
	}
	
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
}

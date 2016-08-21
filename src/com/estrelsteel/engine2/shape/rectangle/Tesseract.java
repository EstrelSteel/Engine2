package com.estrelsteel.engine2.shape.rectangle;

import com.estrelsteel.engine2.point.Point4;
import com.estrelsteel.engine2.shape.Rotation;

public class Tesseract extends Box {
	
	private Point4 fourDistance;


	public Tesseract(Point4 top, Point4 bottom, Point4 depth, Point4 fourDistance) {
		super(top, bottom, depth);
		this.fourDistance = fourDistance;
	}
	
	public Tesseract(Point4 top, Point4 bottom, Point4 depth, Point4 fourDistance, Rotation rotation) {
		super(top, bottom, depth, rotation);
		this.fourDistance = fourDistance;
	}
	
	public Point4 getTop() {
		return (Point4) super.getTop();
	}
	
	public Point4 getBottom() {
		return (Point4) super.getBottom();
	}
	
	public Point4 getDepth() {
		return (Point4) super.getDepth();
	}
	
	public Point4 getFourDistance() {
		return fourDistance;
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
	
	public double getW() {
		return getTop().getW();
	}
	
	public boolean equals(Object other) {
		if(fourDistance.equals(((Tesseract) other).getFourDistance()) && super.equals(other)) {
			return true;
		}
		return false;
	}
	
	public void setFourDistance(Point4 fourDistance) {
		this.fourDistance = fourDistance;
	}

}

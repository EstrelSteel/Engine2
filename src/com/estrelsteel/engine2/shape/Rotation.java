package com.estrelsteel.engine2.shape;

import java.awt.geom.AffineTransform;

import com.estrelsteel.engine2.point.AbstractedPoint;


public class Rotation {
	private double degrees;
	private double radians;
	private AbstractedPoint centre;
	
	public Rotation(double degrees) {
		this.degrees = degrees;
		this.radians = Math.toRadians(degrees);
		this.centre = null;
	}
	
	public Rotation(double degrees, AbstractedPoint centre) {
		this.degrees = degrees;
		this.radians = Math.toRadians(degrees);
		this.centre = centre;
	}
	
	public double getDegrees() {
		return degrees;
	}
	
	public double getRadians() {
		return radians;
	}
	
	public AbstractedPoint getCentre() {
		return centre;
	}
	
	public AffineTransform applyRotation(AffineTransform trans) {
		trans.rotate(radians, centre.getX(), centre.getY());
		return trans;
	}
	
	public boolean equals(Object other) {
		if(degrees == ((Rotation) other).getDegrees() && centre.equals(((Rotation) other).getCentre())) {
			return true;
		}
		return false;
	}
	
	public void setDegrees(double degrees) {
		this.degrees = degrees;
		this.radians = Math.toRadians(this.degrees);
	}
	
	public void setRadians(double radians) {
		this.radians = radians;
		this.degrees = Math.toDegrees(this.radians);
	}
	
	public void setCentre(AbstractedPoint centre) {
		this.centre = centre;
	}
}

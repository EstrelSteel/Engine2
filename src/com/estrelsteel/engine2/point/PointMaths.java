package com.estrelsteel.engine2.point;

import com.estrelsteel.engine2.Engine2;
import com.estrelsteel.engine2.shape.Rotation;
import com.estrelsteel.engine2.shape.rectangle.AbstractedRectangle;

public class PointMaths {
	public static Rotation getDirectionTowards(AbstractedPoint point, AbstractedPoint target) {
		Rotation rotation = new Rotation(0);
		double width = target.getX() - point.getX();
		double height = target.getY() - point.getY();
		double rad = Math.atan2(-height, width);
//		if(width < 0 && height > 0) {
//			rad = rad + Math.PI;
//		}
//		else if(width < 0 && height < 0) {
//			rad = rad + Math.PI;
//		}
//		else if(width > 0 && height < 0) {
//			rad = rad + 2 * Math.PI;
//		}
//		System.err.println(rad);
		rotation.setRadians(Double.parseDouble(Engine2.ROUNDING_FORMAT.format(rad)));
		return rotation;
	}
	
	public static double getDistanceTo(AbstractedPoint point, AbstractedPoint target) {
		double distance = 0;
		double width = point.getWidth(target);
		double height = point.getHeight(target);
		distance = Double.parseDouble(Engine2.ROUNDING_FORMAT.format(Math.sqrt(width * width + height * height)));
		return distance;
	}
	
	public static AbstractedPoint getCentre(AbstractedRectangle rect) {
		AbstractedPoint centre = new AbstractedPoint(0, 0);
		centre.setX(rect.getTop().getX() + rect.getWidth() / 2);
		centre.setY(rect.getTop().getY() + rect.getHeight() / 2);
		return centre;
	}
	
	public static AbstractedPoint getMidpoint(AbstractedPoint point1, AbstractedPoint point2) {
		return new AbstractedPoint((point1.getX() + point2.getX()) / 2, (point1.getY() + point2.getY()) / 2);
	}
}

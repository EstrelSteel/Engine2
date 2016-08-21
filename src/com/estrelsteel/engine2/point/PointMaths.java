package com.estrelsteel.engine2.point;

import com.estrelsteel.engine2.Engine2;
import com.estrelsteel.engine2.shape.Rotation;
import com.estrelsteel.engine2.shape.rectangle.AbstractedRectangle;

public class PointMaths {
	public static Rotation getDirectionTowards(AbstractedPoint point, AbstractedPoint target) {
		Rotation rotation = new Rotation(0);
		double width = point.getWidth(target);
		double height = point.getHeight(target);
		rotation.setRadians(Double.parseDouble(Engine2.ROUNDING_FORMAT.format(Math.atan(height / width))));
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
}

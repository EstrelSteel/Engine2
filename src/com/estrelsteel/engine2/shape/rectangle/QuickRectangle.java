package com.estrelsteel.engine2.shape.rectangle;

import com.estrelsteel.engine2.grid.Grid;
import com.estrelsteel.engine2.grid.PixelGrid;
import com.estrelsteel.engine2.point.Point2;
import com.estrelsteel.engine2.shape.Rotation;

public class QuickRectangle {
	public static final Rectangle location(Grid grid, double x, double y, double width, double height) {
		Point2 top = new Point2(x, y, grid);
		Point2 bottom = new Point2(x + width, y + height, grid);
		Rectangle rect = new Rectangle(top, bottom);
		return rect;
	}
	
	public static final Rectangle location(double x, double y, double width, double height) {
		return location(new PixelGrid(), x, y, width, height);
	}
	
	public static final Rectangle location(Grid grid, double x, double y, double width, double height, double rotation) {
		Rectangle rect = location(grid, x, y, width, height);
		rect.setRotation(new Rotation(rotation));
		return rect;
	}
	
	public static final Rectangle location(double x, double y, double width, double height, double rotation) {
		return location(new PixelGrid(), x, y, width, height, rotation);
	}
	
	public static final Rectangle translate(double x, double y, Rectangle rect) {
		rect.getTop().setX(rect.getTop().getX() + x);
		rect.getBottom().setX(rect.getBottom().getX() + x);
		rect.getTop().setY(rect.getTop().getY() + y);
		rect.getBottom().setY(rect.getBottom().getY() + y);
		return rect;
	}
}

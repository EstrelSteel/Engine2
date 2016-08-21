package com.estrelsteel.engine2.grid;

import com.estrelsteel.engine2.point.AbstractedPoint;
import com.estrelsteel.engine2.shape.rectangle.AbstractedRectangle;

public final class PixelGrid extends Grid {
	
	public PixelGrid() {
		super(new AbstractedRectangle(new AbstractedPoint(0, 0), new AbstractedPoint(1, 1)));
	}
}

package com.estrelsteel.engine2.shape;

import com.estrelsteel.engine2.point.AbstractedPoint;
import com.estrelsteel.engine2.shape.rectangle.AbstractedRectangle;

public interface CollideArea {
	public boolean checkCollision(AbstractedPoint point);
	public boolean checkCollision(AbstractedRectangle rect);
	public boolean equals(Object other);
}

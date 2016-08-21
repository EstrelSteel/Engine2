package com.estrelsteel.engine2.image;

import java.awt.Graphics2D;

import com.estrelsteel.engine2.shape.rectangle.Rectangle;
import com.estrelsteel.engine2.world.FrozenWorld;

public interface Renderable {
	public Rectangle getLocation();
	public Graphics2D render(Graphics2D ctx, FrozenWorld world);
	public Graphics2D simpleRender(Graphics2D ctx, FrozenWorld world);
	public boolean isSortable();
	public void setLocation(Rectangle loc);
	public void setSortable(boolean sortable);
}

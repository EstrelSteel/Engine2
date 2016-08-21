package com.estrelsteel.engine2.world;

import java.awt.Graphics2D;

import com.estrelsteel.engine2.grid.PixelGrid;

public class Menu extends FrozenWorld {
	private String name;
	private boolean visible;
	
	public Menu(String name, boolean visible) {
		super(new PixelGrid());
		this.name = name;
		this.visible = visible;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public boolean equals(Object other) {
		if(name.equals(((Menu) other).getName()) && visible == ((Menu) other).isVisible()
				&& super.equals(other)) {
			return true;
		}
		return false;
	}
	
	public Graphics2D renderWorld(Graphics2D ctx) {
		if(visible) {
			ctx = super.renderWorld(ctx);
		}
		return ctx;
	}
	
	public Graphics2D simpleRenderWorld(Graphics2D ctx) {
		if(visible) {
			ctx = super.simpleRenderWorld(ctx);
		}
		return ctx;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}

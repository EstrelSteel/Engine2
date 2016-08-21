package com.estrelsteel.engine2.actor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.estrelsteel.engine2.image.Renderable;
import com.estrelsteel.engine2.shape.rectangle.Rectangle;
import com.estrelsteel.engine2.world.FrozenWorld;
import com.estrelsteel.engine2.world.World;

public class Text implements Renderable {

	private String text;
	private Font font;
	private Color colour;
	private Rectangle loc;
	private boolean sortable;
	
	public Text(String text, Rectangle loc) {
		this.text = text;
		this.font = new Font("Menlo", Font.BOLD, 16);
		this.colour = Color.BLACK;
		this.loc = loc;
		this.sortable = false;
	}
	
	public Text(String text, Rectangle loc, Font font, Color colour) {
		this.text = text;
		this.font = font;
		this.colour = colour;
		this.loc = loc;
		this.sortable = false;
	}
	
	public String getText() {
		return text;
	}
	
	public Rectangle getLocation() {
		return loc;
	}
	
	public Font getFont() {
		return font;
	}
	
	public Color getColour() {
		return colour;
	}
	
	@Override
	public Graphics2D render(Graphics2D ctx, FrozenWorld world) {
		double x = 0;
		double y = 0;
		if(world instanceof World) {
			x = world.getGrid().moveToGrid(((World) world).getMainCamera().getLocation()).getX();
			y = world.getGrid().moveToGrid(((World) world).getMainCamera().getLocation()).getY();
		}
		ctx.setFont(font);
		ctx.setColor(colour);
		ctx.drawString(text, (int) (world.getGrid().moveToGrid(loc).getTop().getX() + x),
				(int) (world.getGrid().moveToGrid(loc).getTop().getY() + y));
		return ctx;
	}

	@Override
	public Graphics2D simpleRender(Graphics2D ctx, FrozenWorld world) {
		return render(ctx, world);
	}

	@Override
	public boolean isSortable() {
		return sortable;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setLocation(Rectangle loc) {
		this.loc = loc;
	}
	
	public void setFont(Font font) {
		this.font = font;
	}
	
	public void setColour(Color colour) {
		this.colour = colour;
	}

	@Override
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

}

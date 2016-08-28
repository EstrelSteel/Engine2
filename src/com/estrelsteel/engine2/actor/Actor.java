package com.estrelsteel.engine2.actor;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import com.estrelsteel.engine2.image.Animation;
import com.estrelsteel.engine2.image.Renderable;
import com.estrelsteel.engine2.shape.Rotation;
import com.estrelsteel.engine2.shape.rectangle.Rectangle;
import com.estrelsteel.engine2.world.FrozenWorld;
import com.estrelsteel.engine2.world.World;

public class Actor implements Renderable {
	private String name;
	private Rectangle loc;
	private Rotation rotation;
	private ArrayList<Animation> animations;
	private int animation;
	private boolean sortable;
	
	public Actor(String name, Rectangle loc) {
		this.name = name;
		this.loc = loc;
		this.rotation = new Rotation(0.0);
		this.animations = new ArrayList<Animation>();
		this.sortable = true;
	}
	
	public Actor(String name, Rectangle loc, Rotation rotation) {
		this.name = name;
		this.loc = loc;
		this.rotation = rotation;
		this.animations = new ArrayList<Animation>();
		this.sortable = true;
	}
	
	public String getName() {
		return name;
	}
	
	public Rectangle getLocation() {
		return loc;
	}
	
	public Rotation getRotation() {
		return rotation;
	}
	
	public ArrayList<Animation> getAnimations() {
		return animations;
	}
	
	public Animation getRunningAnimation() {
		return getAnimations().get(animation);
	}
	
	public int getRunningAnimationNumber() {
		return animation;
	}
	
	public Graphics2D render(Graphics2D ctx, FrozenWorld world) {
		AffineTransform trans;
		double x = 0;
		double y = 0;
		double r = 0;
		if(world instanceof World) {
			x = world.getGrid().moveToGrid(((World) world).getMainCamera().getLocation()).getX();
			y = world.getGrid().moveToGrid(((World) world).getMainCamera().getLocation()).getY();
			r = ((World) world).getMainCamera().getRotation().getRadians();
		}
		
		if(!getRunningAnimation().getCurrentImage().isImageLoaded()) {
			getRunningAnimation().getCurrentImage().loadImage();
		}
		trans = new AffineTransform();
		
		trans.translate(world.getGrid().moveToGrid(getLocation().getTop()).getX() - x,
				world.getGrid().moveToGrid(getLocation().getTop()).getY() - y);
//		trans.scale(getRunningAnimation().getCurrentImage().getImage().getWidth() / world.getGrid().moveToGrid(getLocation()).getWidth(),
//				getRunningAnimation().getCurrentImage().getImage().getHeight() / world.getGrid().moveToGrid(getLocation()).getHeight());
		trans.scale(getLocation().getWidth() / getRunningAnimation().getCurrentImage().getImage().getWidth(), getLocation().getHeight() / getRunningAnimation().getCurrentImage().getImage().getHeight());
		trans.rotate(getRotation().getRadians() + r);
		if((loc.getX() - x >= 0 || loc.getX() + loc.getWidth() - x >= 0) && loc.getX() - x <= 640) {
			if((loc.getY() - y >= 0 || loc.getY() + loc.getHeight() - y >= 0) && loc.getY() - y <= 640) {
				
				ctx.drawImage(getRunningAnimation().getCurrentImage().getImage(), trans, null);
			}
		}
		return ctx;
	}
	
	public Graphics2D simpleRender(Graphics2D ctx, FrozenWorld world) {
		double x = 0;
		double y = 0;
		if(world instanceof World) {
			x = world.getGrid().moveToGrid(((World) world).getMainCamera().getLocation()).getX();
			y = world.getGrid().moveToGrid(((World) world).getMainCamera().getLocation()).getY();
		}
		if(!getRunningAnimation().getCurrentImage().isImageLoaded()) {
			getRunningAnimation().getCurrentImage().loadImage();
		}
		if((loc.getX() - x >= 0 || loc.getX() + loc.getWidth() - x >= 0) && loc.getX() - x <= 640) {
			if((loc.getY() - y >= 0 || loc.getY() + loc.getHeight() - y >= 0) && loc.getY() - y <= 640) {
				ctx.drawImage(getRunningAnimation().getCurrentImage().getImage(),
						(int) (getLocation().getTop().getX() - x), (int) (getLocation().getTop().getY() - y),
						(int) (getLocation().getWidth()), (int) (getLocation().getHeight()), null);
//				ctx.drawRect((int) (getLocation().getTop().getX() - x), (int) (getLocation().getTop().getY() - y),
//						(int) (getLocation().getWidth()), (int) (getLocation().getHeight()));
			}
		}
		return ctx;
	}
	
	public boolean isSortable() {
		return sortable;
	}
	
	public boolean equals(Object other) {
		if(name.equals(((Actor) other).getName())) {
			return true;
		}
		return false;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLocation(Rectangle loc) {
		this.loc = loc;
	}
	
	public void setRotation(Rotation rotation) {
		this.rotation = rotation;
	}
	
	public void setAnimations(ArrayList<Animation> animations) {
		this.animations = animations;
	}
	
	public void setRunningAnimationNumber(int animation) {
		this.animation = animation;
	}
	
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
}

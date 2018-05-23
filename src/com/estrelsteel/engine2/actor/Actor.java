package com.estrelsteel.engine2.actor;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.estrelsteel.engine2.shape.Rotation;
import com.estrelsteel.engine2.shape.rectangle.Rectangle;
import com.estrelsteel.engine2.world.FrozenWorld;
import com.estrelsteel.engine2.world.World;

public class Actor extends UndrawnActor {
	
	public Actor(String name, Rectangle loc) {
		super(name, loc);
	}
	
	public Actor(String name, Rectangle loc, Rotation rotation) {
		super(name, loc, rotation);
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
		if(!doHideOffScreen() || ((getLocation().getX() - x >= 0 || getLocation().getX() + getLocation().getWidth() - x >= 0) && getLocation().getX() - x <= 640)) {
			if(!doHideOffScreen() || ((getLocation().getY() - y >= 0 || getLocation().getY() + getLocation().getHeight() - y >= 0) && getLocation().getY() - y <= 640)) {
				
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
		if(!doHideOffScreen() || ((getLocation().getX() - x >= 0 || getLocation().getX() + getLocation().getWidth() - x >= 0) && getLocation().getX() - x <= 640)) {
			if(!doHideOffScreen() || ((getLocation().getY() - y >= 0 || getLocation().getY() + getLocation().getHeight() - y >= 0) && getLocation().getY() - y <= 640)) {
				ctx.drawImage(getRunningAnimation().getCurrentImage().getImage(),
						(int) (getLocation().getTop().getX() - x), (int) (getLocation().getTop().getY() - y),
						(int) (getLocation().getWidth()), (int) (getLocation().getHeight()), null);
//				ctx.drawRect((int) (getLocation().getTop().getX() - x), (int) (getLocation().getTop().getY() - y),
//						(int) (getLocation().getWidth()), (int) (getLocation().getHeight()));
			}
		}
		return ctx;
	}
	
	public boolean equals(Object other) {
		if(getName().equals(((Actor) other).getName())) {
			return true;
		}
		return false;
	}
}

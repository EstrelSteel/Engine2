package com.estrelsteel.engine2.actor;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.estrelsteel.engine2.image.Animation;
import com.estrelsteel.engine2.image.Renderable;
import com.estrelsteel.engine2.shape.Rotation;
import com.estrelsteel.engine2.shape.collide.Collision;
import com.estrelsteel.engine2.shape.collide.RectangleCollideArea;
import com.estrelsteel.engine2.shape.rectangle.Rectangle;
import com.estrelsteel.engine2.world.FrozenWorld;

public abstract class UndrawnActor implements Renderable {
	private String name;
	private Rectangle loc;
	private Rotation rotation;
	private ArrayList<Animation> animations;
	private int animation;
	private boolean sortable;
	private Collision collide;
	private boolean hideOffScreen;
	
	public UndrawnActor(String name, Rectangle loc) {
		this.hideOffScreen = true;
		this.name = name;
		this.loc = loc;
		this.rotation = new Rotation(0.0);
		this.animations = new ArrayList<Animation>();
		this.sortable = true;
		this.collide = new Collision(false, new RectangleCollideArea(loc));
	}
	
	public UndrawnActor(String name, Rectangle loc, Rotation rotation) {
		this.hideOffScreen = true;
		this.name = name;
		this.loc = loc;
		this.rotation = rotation;
		this.animations = new ArrayList<Animation>();
		this.sortable = true;
		this.collide = new Collision(false, new RectangleCollideArea(loc));
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
	
	public Collision getCollision() {
		return collide;
	}
	
	public void updateCollision() {
		collide.setCollideArea(new RectangleCollideArea(loc));
	}
	
	public abstract Graphics2D render(Graphics2D ctx, FrozenWorld world);
	
	public abstract Graphics2D simpleRender(Graphics2D ctx, FrozenWorld world);
	
	public boolean isSortable() {
		return sortable;
	}
	
	public boolean doHideOffScreen() {
		return hideOffScreen;
	}
	
	public boolean equals(Object other) {
		if(name.equals(((UndrawnActor) other).getName())) {
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
	
	public void setCollision(Collision collide) {
		this.collide = collide;
	}
	
	public UndrawnActor setHideOffScreen(boolean hide) {
		this.hideOffScreen = hide;
		return this;
	}
}

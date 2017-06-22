package com.estrelsteel.engine2.world;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import com.estrelsteel.engine2.actor.Actor;
import com.estrelsteel.engine2.chunk.Chunk;
import com.estrelsteel.engine2.grid.Grid;
import com.estrelsteel.engine2.image.Renderable;
import com.estrelsteel.engine2.shape.collide.CollideArea;

public class FrozenWorld {
	private ArrayList<Renderable> objects;
	private Grid grid;
	private ArrayList<Renderable> helper;
	
	public FrozenWorld(Grid grid) {
		this.grid = grid;
		this.objects = new ArrayList<Renderable>();
	}
	
	/**
	 * Gets the grid the world is using.
	 * @return the grid
	 * @see Grid
	 */
	public Grid getGrid() {
		return grid;
	}
	
	/**
	 * Gets the objects that are held in this world
	 * @return an ArrayList<Renderable>
	 * @see Renderable, ArrayList<Renderable>
	 */
	public ArrayList<Renderable> getObjects() {
		return objects;
	}
	
	public boolean equals(Object other) {
		if(grid.equals(((FrozenWorld) other).getGrid()) && objects.equals(((FrozenWorld) other).getObjects())) {
			return true;
		}
		return false;
	}
	
	public static Renderable checkCollide(ArrayList<Renderable> objects, CollideArea area, Renderable r1) {
		for(Renderable r : objects) {
			if(r != r1) {
				if(r instanceof Actor) {
					if(((Actor) r).getCollision().doesCollide()) {
						if(area.checkCollision(r.getLocation())) {
							return r;
						}
					}
				}
				else if(r instanceof Chunk) {
					if(checkCollide(((Chunk) r).getObjects(), area, r1) != null) {
						return r;
					}
				}
			}
		}
		return null;
	}
	
	public static Renderable checkCollideIgnoreDeclaration(ArrayList<Renderable> objects, CollideArea area, Renderable r1) {
		for(Renderable r : objects) {
			if(r != r1) {
				if(r instanceof Actor) {
					if(area.checkCollision(r.getLocation())) {
						return r;
					}
				}
				else if(r instanceof Chunk) {
					if(checkCollide(((Chunk) r).getObjects(), area, r1) != null) {
						return r;
					}
				}
			}
		}
		return null;
	}
	
	public Renderable checkCollide(CollideArea area, Renderable r) {
		return checkCollide(objects, area, r);
	}
	
	public Renderable checkCollideIgnoreDeclaration(CollideArea area, Renderable r) {
		return checkCollideIgnoreDeclaration(objects, area, r);
	}
	
	/**
	 * Draws on the objects in this world onto the graphics context.
	 * <p>
	 * Uses transformations which allows rotation
	 * @param the Graphics2D context
	 * @return the Grahpics2D context with the actors of this world rendered on it
	 * @see Graphics2D
	 * @see AffineTransform
	 */
	public Graphics2D renderWorld(Graphics2D ctx) {
		for(int i = 0; i < objects.size(); i++) {
			ctx = objects.get(i).render(ctx, this);
		}
		return ctx;
	}
	
	/**
	 * Draws on the objects in this world onto the graphics context.
	 * <p>
	 * Doesn't use transformation, not allowing rotations
	 * @param the Graphics2D context
	 * @return the Grahpics2D context with the actors of this world rendered on it
	 * @see Graphics2D
	 */
	public Graphics2D simpleRenderWorld(Graphics2D ctx) {
		for(int i = 0; i < objects.size(); i++) {
			ctx = objects.get(i).simpleRender(ctx, this);
		}
		return ctx;
	}
	
	/**
	 * Sorts the renderable objects with merge sort. The sorting is done with the highest Y value first.
	 * <p>
	 * Any renderables which want to opt out of the search should use setSortable(boolean sortable);
	 * @return void
	 * @see Renderable
	 */
	public void sortObjects() {
		if(!checkSort()) {
			ArrayList<Renderable> keep = new ArrayList<Renderable>();
			helper = new ArrayList<Renderable>();
			for(int i = 0; i < objects.size(); i++) {
				helper.add(objects.get(i));
				if(!objects.get(i).isSortable()) {
					keep.add(objects.get(i));
					objects.remove(i);
					i--;
				}
			}
			mergeSort(0, objects.size() - 1);
			for(int i = 0; i < keep.size(); i++) {
				if(keep.get(i) != null) {
					objects.add(0, keep.get(i));
				}
			}
		}
		
	}
	
	public boolean checkSort() {
		int l = 0;
		for(int i = 1; i < objects.size(); i++) {
			if(!(objects.get(i).getLocation().getY() + objects.get(i).getLocation().getHeight()
					<= objects.get(l).getLocation().getY() + objects.get(l).getLocation().getHeight()) 
					&& objects.get(i).isSortable() && objects.get(l).isSortable()) {
				return false;
			}
			if(objects.get(i).isSortable()) {
				l = i;
			}
		}
		return true;
	}
	
	private void mergeSort(int low, int high) {
		if(low < high) {
			int middle = low + (high - low) / 2;
			mergeSort(low, middle);
			mergeSort(middle + 1, high);
			merge(low, middle, high);
		}
	}
	
	private void merge(int low, int middle, int high) {
		for(int i = low; i <= high; i++) {
			helper.set(i, objects.get(i));
		}
		int i = low;
		int j = middle + 1;
		int k = low;
		while(i <= middle && j <= high) {
			if(helper.get(i).getLocation().getY() + helper.get(i).getLocation().getHeight()
					<= helper.get(j).getLocation().getY() + helper.get(j).getLocation().getHeight()) {
				objects.set(k, helper.get(i));
				i++;
			}
			else {
				objects.set(k, helper.get(j));
				j++;
			}
			k++;
		}
		
		while(i <= middle) {
			objects.set(k, helper.get(i));
			k++;
			i++;
		}
	}
	
	/**
	 * Sets the objects in the world.
	 * @param an ArrayList<Renderable>
	 * @return void
	 * @see Renderable, ArrayList<Renderable>
	 */
	public void setObjects(ArrayList<Renderable> objects) {
		this.objects = objects;
	}
}

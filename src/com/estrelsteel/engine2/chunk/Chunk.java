package com.estrelsteel.engine2.chunk;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.estrelsteel.engine2.file.GameFile;
import com.estrelsteel.engine2.file.Saveable;
import com.estrelsteel.engine2.image.Renderable;
import com.estrelsteel.engine2.shape.rectangle.QuickRectangle;
import com.estrelsteel.engine2.shape.rectangle.Rectangle;
import com.estrelsteel.engine2.tile.Tile;
import com.estrelsteel.engine2.tile.TileType;
import com.estrelsteel.engine2.world.FrozenWorld;
import com.estrelsteel.engine2.world.World;

public class Chunk implements Renderable, Saveable {
	
	private Rectangle loc;
	private boolean sortable;
	private ArrayList<Renderable> objects;
	private double transX;
	private double transY;
	private boolean altered;
	private boolean structured;
	private boolean saveMe;
	
	public Chunk(Rectangle loc) {
		this.loc = loc;
		this.sortable = false;
		this.objects = new ArrayList<Renderable>();
		this.altered = false;
		this.structured = false;
		this.saveMe = false;
	}
	
	@Override
	public Rectangle getLocation() {
		return loc;
	}
	
	public ArrayList<Renderable> getObjects() {
		return objects;
	}
	
	@Override
	public Graphics2D render(Graphics2D ctx, FrozenWorld world) {
		transX = 0;
		transY = 0;
		if(world instanceof World) {
			transX = world.getGrid().moveToGrid(((World) world).getMainCamera().getLocation()).getX();
			transY = world.getGrid().moveToGrid(((World) world).getMainCamera().getLocation()).getY();
		}
		if((transX + loc.getX() >= 0 || transX + loc.getX() + loc.getWidth() >= 0) && transX + loc.getX() <= 640) {
			if((transY + loc.getY() >= 0 || transY + loc.getY() + loc.getHeight() >= 0) && transY + loc.getY() <= 640) {
				for(int i = 0; i < objects.size(); i++) {
					objects.get(i).render(ctx, world);
				}
			}
		}
		return ctx;
	}
	
	@Override
	public Graphics2D simpleRender(Graphics2D ctx, FrozenWorld world) {
		double transX = 0;
		double transY = 0;
		if(world instanceof World) {
			transX = world.getGrid().moveToGrid(((World) world).getMainCamera().getLocation()).getX();
			transY = world.getGrid().moveToGrid(((World) world).getMainCamera().getLocation()).getY();
		}
		if((loc.getX() - transX >= 0 || loc.getX() + loc.getWidth() - transX >= 0) && loc.getX() - transX <= 640) {
			if((loc.getY() - transY >= 0 || loc.getY() + loc.getHeight() - transY >= 0) && loc.getY() - transY <= 640) {
				for(int i = 0; i < objects.size(); i++) {
					objects.get(i).simpleRender(ctx, world);
				}
			}
		}
		return ctx;
	}
	
	@Override
	public boolean isSortable() {
		return sortable;
	}
	
	public boolean isAltered() {
		return altered;
	}
	
	public boolean isStructured() {
		return structured;
	}
	
	public boolean isSaveMe() {
		return saveMe;
	}
	
	@Override
	public void setLocation(Rectangle loc) {
		this.loc = loc;
	}
	
	@Override
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
	
	public void setObjects(ArrayList<Renderable> objects) {
		this.objects = objects;
	}
	
	public void setAltered(boolean altered) {
		this.altered = altered;
	}
	
	public void setStructured(boolean structured) {
		this.structured = structured;
	}
	
	public void setSaveMe(boolean saveMe) {
		this.saveMe = saveMe;
	}

	@Override
	public String getIdentifier() {
		return "GCS";
	}

	@Override
	public Renderable load(GameFile file, int line) {
		String[] args = file.getLines().get(line).split(" ");
		Chunk c = new Chunk(QuickRectangle.location(0, 0, 0, 0));
		if(args[0].trim().equalsIgnoreCase(getIdentifier()) && args[1].trim().equalsIgnoreCase(":")) {
			Tile t = new Tile(TileType.types.get(0), QuickRectangle.location(0, 0, 0, 0));
			c.setLocation(QuickRectangle.location(Double.parseDouble(args[2].trim()), Double.parseDouble(args[3].trim()), Double.parseDouble(args[4].trim()), Double.parseDouble(args[5].trim())));
			c.setAltered(Boolean.parseBoolean(args[6].trim()));
			c.setSortable(false);
			line = line++;
			while(line < file.getLines().size() && file.getLines().get(line) != null) {
				args = file.getLines().get(line).split(" ");
				c.getObjects().add(t.load(file, line));
				line++;
			}
		}
		return c;
	}

	@Override
	@Deprecated
	public GameFile save(GameFile file) {
		file.getLines().add(getIdentifier() + " : " + loc.getX() + " " + loc.getY() + " " + loc.getWidth() + " " + loc.getHeight() + " " + isAltered());
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i) instanceof Tile) {
				((Tile) objects.get(i)).save(file);
			}
		}
		return file;
	}
}

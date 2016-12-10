package com.estrelsteel.engine2.tile;

import com.estrelsteel.engine2.actor.Actor;
import com.estrelsteel.engine2.file.GameFile;
import com.estrelsteel.engine2.file.Saveable;
import com.estrelsteel.engine2.image.Renderable;
import com.estrelsteel.engine2.shape.rectangle.QuickRectangle;
import com.estrelsteel.engine2.shape.rectangle.Rectangle;

public class Tile extends Actor implements Saveable {
	
	private TileType type;
	
	public Tile(TileType type, Rectangle loc) {
		super("TILE", loc);
		setSortable(false);
		this.type = type;
		getAnimations().add(type.getAnimation());
		setRunningAnimationNumber(0);
		getCollision().setCollide(type.doesCollide());
	}
	
	public TileType getType() {
		return type;
	}
	
	public void setType(TileType type) {
		this.type = type;
		setRunningAnimationNumber(type.getID());
		getCollision().setCollide(type.doesCollide());
	}

	public String getIdentifier() {
		return "GTS";
	}

	public Renderable load(GameFile file, int line) {
		String[] args = file.getLines().get(line).split(" ");
		Tile tile = new Tile(TileType.types.get(0), QuickRectangle.location(0, 0, 0, 0));
		if(args[0].trim().equalsIgnoreCase(getIdentifier())) {
			tile.setType(TileType.types.get(Integer.parseInt(args[2].trim())));
			tile.setLocation(QuickRectangle.location(Double.parseDouble(args[3].trim()), Double.parseDouble(args[4].trim()), Double.parseDouble(args[5].trim()), Double.parseDouble(args[6].trim())));
			return tile;
		}
		return tile;
	}

	public GameFile save(GameFile file) {
		file.getLines().add(getIdentifier() + " " + type.getID() + " " + getLocation().getX() + " " + getLocation().getY() + " " + getLocation().getWidth() + " " + getLocation().getHeight());
		return file;
	}
}

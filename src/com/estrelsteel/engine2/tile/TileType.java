package com.estrelsteel.engine2.tile;

import java.io.IOException;
import java.util.ArrayList;

import com.estrelsteel.engine2.file.GameFile;
import com.estrelsteel.engine2.file.Saveable;
import com.estrelsteel.engine2.image.Animation;

public class TileType implements Saveable {

	public static ArrayList<TileType> types;
	
	private int id;
	private String name;
	private boolean collide;
	private Animation animation;
	
	public TileType(int id, String name) {
		this.id = id;
		this.name = name;
		this.collide = false;
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Animation getAnimation() {
		return animation;
	}
	
	public boolean doesCollide() {
		return collide;
	}

	public void setID(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setCollide(boolean collide) {
		this.collide = collide;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	@Override
	public String getIdentifier() {
		return "TTY";
	}

	@Override
	public ArrayList<TileType> load(GameFile file, int line) {
		try {
			file.updateLines();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		if(types == null) {
			types = new ArrayList<TileType>();
		}
		String[] args;
		TileType type;
		for(int i = line; i < file.getLines().size(); i++) {
			args = file.getLines().get(i).split(" ");
			if(args[0].trim().equalsIgnoreCase(getIdentifier())) {
				type = new TileType(-1, name);
				type.setID(Integer.parseInt(args[1].trim()));
				type.setName(args[2].trim());
				type.setCollide(Boolean.parseBoolean(args[3].trim()));
				type.setAnimation(animation.load(file, i + 1));
				
				types.add(type);
			}
		}
		return types;
	}

	@Override
	public GameFile save(GameFile file) {
		return file;
	}
}

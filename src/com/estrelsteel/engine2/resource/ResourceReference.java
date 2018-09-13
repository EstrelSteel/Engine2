package com.estrelsteel.engine2.resource;

import java.util.HashMap;

import com.estrelsteel.engine2.file.GameFile;
import com.estrelsteel.engine2.file.Saveable;
import com.estrelsteel.engine2.image.ConfinedImage;
import com.estrelsteel.engine2.image.Image;
import com.estrelsteel.engine2.shape.rectangle.QuickRectangle;

public class ResourceReference implements Saveable {

	private HashMap<Integer, Resource> resources;
	
	public ResourceReference() {
		resources = new HashMap<Integer, Resource>();
	}
	
	public HashMap<Integer, Resource> getResources() {
		return resources;
	}
	
	public ResourceReference addResource(int id, Resource res) {
		resources.put(id, res);
		return this;
	}
	
	public void setResources(HashMap<Integer, Resource> resources) {
		this.resources = resources;
	}

	@Override
	public String getIdentifier() {
		return "RES";
	}

	@Override
	public ResourceReference load(GameFile file, int line) {
		String[] args;
		int id;
		for(int i = line; i < file.getLines().size(); i++) {
			args = file.getLines().get(i).split(" ");
			if(args[0].trim().equalsIgnoreCase("+cimg")) {
				//+cimg id src x y w h
				id = Integer.parseInt(args[1].trim());
				resources.put(id, new ConfinedImage(GameFile.getDataPath() + args[2].trim(), id, QuickRectangle.location(
						Double.parseDouble(args[3].trim()) * 16, Double.parseDouble(args[4].trim()) * 16, 
						Double.parseDouble(args[5].trim()), Double.parseDouble(args[6].trim()))));	
			}
			else if(args[0].trim().equalsIgnoreCase("+img")) {
				//+img id src
				id = Integer.parseInt(args[1].trim());
				resources.put(id, new Image(GameFile.getDataPath() + args[2].trim(), id));
			}
			else {
				break;
			}
		}
		return this;
	}

	@Override
	public GameFile save(GameFile file) {
		return file;
	}
	
}

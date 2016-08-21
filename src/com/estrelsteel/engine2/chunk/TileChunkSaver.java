package com.estrelsteel.engine2.chunk;

import java.util.ArrayList;

import com.estrelsteel.engine2.file.GameFile;
import com.estrelsteel.engine2.file.Saveable;
import com.estrelsteel.engine2.image.Renderable;
import com.estrelsteel.engine2.shape.rectangle.QuickRectangle;
import com.estrelsteel.engine2.tile.Tile;
import com.estrelsteel.engine2.tile.TileType;

public class TileChunkSaver implements Saveable {

	private Chunk c;
	private ArrayList<Renderable> helper;
	
	public TileChunkSaver(Chunk c) {
		this.c = c;
		this.helper = new ArrayList<Renderable>();
	}
	
	public Chunk getChunk() {
		return c;
	}
	
	@Override
	public String getIdentifier() {
		return "TCS";
	}

	@Override
	public Renderable load(GameFile file, int line) {
		return load(file.getLines().get(line));
	}
	
	public Renderable load(String line) {
		Chunk c = new Chunk(QuickRectangle.location(0, 0, 0, 0));
		String[] args = line.split("~");
		if(args[0].trim().equalsIgnoreCase(getIdentifier())) {
			c.setLocation(QuickRectangle.location(Double.parseDouble(args[1].trim()), Double.parseDouble(args[2].trim()), Double.parseDouble(args[3].trim()), Double.parseDouble(args[4].trim())));
			c.setAltered(Boolean.parseBoolean(args[5].trim()));
			c.setSortable(false);
			int x = 0;
			int y = 0;
			for(int i = 6; i < args.length; i++) {
				c.getObjects().add(new Tile(TileType.types.get(Integer.parseInt(args[i].trim())), 
						QuickRectangle.location(x * 64 + c.getLocation().getX(), y * 64 + c.getLocation().getY(), 64, 64)));
				x = x + 1;
				if(x >= c.getLocation().getWidth() / 64) {
					x = 0;
					y = y + 1;
				}
			}
		}
		return c;	
	}

	@Override
	public GameFile save(GameFile file) {
		if(file.getLines().size() > 0) {
			file.getLines().set(0, generateSaveLine());
		}
		else {
			file.getLines().add(0, generateSaveLine());
		}
		return file;
	}
	
	public String generateSaveLine() {
		String line = getIdentifier() + "~" + c.getLocation().getX() + "~" + c.getLocation().getY() + "~" + c.getLocation().getWidth() + "~" + c.getLocation().getHeight();
		line = line + "~" + c.isAltered();
		for(int i = 0; i < c.getObjects().size(); i++) {
			if(c.getObjects().get(i) instanceof Tile) {
				line = line + "~" + ((Tile) c.getObjects().get(i)).getType().getID();
			}
			else {
				System.err.println("ERROR: Tile attempted to be saved in tile only chunk saver!!!!");
			}
		}
		return line;
	}
	
	public void sortObjects() {
		if(checkSort()) {
			helper = new ArrayList<Renderable>();
			for(int i = 0; i < c.getObjects().size(); i++) {
				helper.add(c.getObjects().get(i));
			}
			mergeSort(0, c.getObjects().size() - 1);
		}
	}
	
	public boolean checkSort() {
		int l = 0;
		for(int i = 1; i < c.getObjects().size(); i++) {
			if(determineValue(helper.get(i))
					<= determineValue(helper.get(l))) {
				return false;
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
			helper.set(i, c.getObjects().get(i));
		}
		int i = low;
		int j = middle + 1;
		int k = low;
		while(i <= middle && j <= high) {
			if(determineValue(helper.get(i))
					<= determineValue(helper.get(j))) {
				c.getObjects().set(k, helper.get(i));
				i++;
			}
			else {
				c.getObjects().set(k, helper.get(j));
				j++;
			}
			k++;
		}
		
		while(i <= middle) {
			c.getObjects().set(k, helper.get(i));
			k++;
			i++;
		}
	}
	
	private double determineValue(Renderable r) {
		return ((r.getLocation().getX() - c.getLocation().getX()) / 64) + (((r.getLocation().getY() - c.getLocation().getY()) / 64) * ((c.getLocation().getWidth() + 10) / 64));
	}
	
	public void setChunk(Chunk c) {
		this.c = c;
	}
	
}

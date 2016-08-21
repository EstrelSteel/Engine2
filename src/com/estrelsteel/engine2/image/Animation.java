package com.estrelsteel.engine2.image;

import java.util.ArrayList;

import com.estrelsteel.engine2.file.GameFile;
import com.estrelsteel.engine2.shape.rectangle.QuickRectangle;

public class Animation {
	private String name;
	private int id;
	private ArrayList<Image> frames;
	private int frame;
	private int wait;
	private int maxWait;
	private boolean running;
	
	public Animation(String name, int id) {
		this.name = name;
		this.id = id;
		this.frames = new ArrayList<Image>();
		this.frame = 0;
		this.wait = 0;
		this.maxWait = 1;
		this.running = true;
	}
	
	public static Animation build(GameFile file, int line, String name, int id) {
		Animation a = new Animation(name, id);
		String[] args;
		for(int i = line; i < file.getLines().size(); i++) {
			args = file.getLines().get(line).split(" ");
			if(args[0].trim().equalsIgnoreCase("+cimg")) {
				a.getFrames().add(new ConfinedImage(GameFile.getDataPath() + args[1].trim(), QuickRectangle.location(
						Double.parseDouble(args[2].trim()) * 16, Double.parseDouble(args[3].trim()) * 16,
						Double.parseDouble(args[4].trim()), Double.parseDouble(args[5].trim()))));	
			}
			else if(args[0].trim().equalsIgnoreCase("+img")) {
				a.getFrames().add(new Image(GameFile.getDataPath() + args[1].trim()));
			}
			else {
				break;
			}
		}
		
		return a;
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return id;
	}
	
	public ArrayList<Image> getFrames() {
		return frames;
	}
	
	public int getCurrentFrame() {
		return frame;
	}
	
	public int getCurrentWaitTime() {
		return wait;
	}
	
	public int getMaxWaitTime() {
		return maxWait;
	}
	
	public Image getCurrentImage() {
		return frames.get(frame);
	}
	
	public Image run() {
		if(running) {
			wait = wait + 1;
			if(wait >= maxWait) {
				wait = 0;
				frame = frame + 1;
			}
			if(frame >= frames.size()) {
				frame = 0;
			}
		}
		return frames.get(frame);
	}
	
	public boolean equals(Object other) {
		if(frames.equals(((Animation) other).getFrames()) && name.equals(((Animation) other).getName())
				&& id == ((Animation) other).getID() && maxWait == ((Animation) other).getMaxWaitTime()) {
			return true;
		}
		return false;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setFrames(ArrayList<Image> frames) {
		this.frames = frames;
	}
	
	public void setCurrentFrame(int frame) {
		this.frame = frame;
	}
	
	public void setCurrentWaitTime(int wait) {
		this.wait = wait;
	}
	
	public void setMaxWaitTime(int maxWait) {
		this.maxWait = maxWait;
	}
}

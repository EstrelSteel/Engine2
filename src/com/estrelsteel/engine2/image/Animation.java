package com.estrelsteel.engine2.image;

import java.util.ArrayList;

import com.estrelsteel.engine2.Engine2;
import com.estrelsteel.engine2.file.GameFile;
import com.estrelsteel.engine2.file.Saveable;
import com.estrelsteel.engine2.shape.rectangle.QuickRectangle;

public class Animation implements Saveable {
	private String name;
	private int id;
	private ArrayList<Image> framesImage;
	private ArrayList<Integer> framesReference;
	private int frame;
	private int wait;
	private int maxWait;
	private boolean running;
	private boolean highUseage;
	
	public Animation() {
		this.name = "NULL";
		this.id = -1;
		this.framesImage = new ArrayList<Image>();
		this.framesReference = new ArrayList<Integer>();
		this.frame = 0;
		this.wait = 0;
		this.maxWait = 1;
		this.running = true;
		this.highUseage = false;
	}
	
	public Animation(String name, int id, boolean highUseage) {
		this.name = name;
		this.id = id;
		this.framesImage = new ArrayList<Image>();
		this.framesReference = new ArrayList<Integer>();
		this.frame = 0;
		this.wait = 0;
		this.maxWait = 1;
		this.running = true;
		this.highUseage = highUseage;
	}
	
	@Deprecated
	public Animation(String name, int id) {
		this.name = name;
		this.id = id;
		this.framesImage = new ArrayList<Image>();
		this.framesReference = new ArrayList<Integer>();
		this.frame = 0;
		this.wait = 0;
		this.maxWait = 1;
		this.running = true;
		this.highUseage = true;
	}
	
	@Deprecated
	public static Animation build(GameFile file, int line, String name, int id, int maxWait) {
		Animation a = build(file, line, name, id);
		a.setMaxWaitTime(maxWait);
		return a;
	}
	
	@Deprecated
	public static Animation build(GameFile file, int line, String name, int id) {
		Animation a = new Animation(name, id);
		String[] args;
		for(int i = line; i < file.getLines().size(); i++) {
			args = file.getLines().get(i).split(" ");
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
	
	@Deprecated
	public ArrayList<Image> getFrames() {
		return framesImage;
	}
	
	public ArrayList<Image> getFramesImage() {
		return framesImage;
	}
	
	public ArrayList<Integer> getFramesReference() {
		return framesReference;
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
		if(highUseage) {
			return framesImage.get(frame);
		}
		return (Image) Engine2.GLOBAL_RESOURCE_REFERENCE.getResources().get(framesReference.get(frame));
	}
	
	public Image run() {
		if(running) {
			wait = wait + 1;
			if(wait >= maxWait) {
				wait = 0;
				frame = frame + 1;
			}
			if(frame >= framesReference.size()) {
				frame = 0;
			}
		}
		return getCurrentImage();
	}
	
	public boolean equals(Object other) {
		if(name.equals(((Animation) other).getName()) && id == ((Animation) other).getID() && maxWait == ((Animation) other).getMaxWaitTime()) {
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
	
	@Deprecated
	public void setFrames(ArrayList<Image> framesImage) {
		this.framesImage = framesImage;
	}
	
	public void setFramesImage(ArrayList<Image> framesImage) {
		this.framesImage = framesImage;
	}
	
	public void setFramesReference(ArrayList<Integer> framesReference) {
		this.framesReference = framesReference;
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

	public boolean isHighUseage() {
		return highUseage;
	}

	public void setHighUseage(boolean highUseage) {
		this.highUseage = highUseage;
		loadHighUseage();
	}

	@Override
	public String getIdentifier() {
		return "ANI";
	}
	
	public Animation load(String line) {
		String[] args = line.split(" ");
		if(args[0].equalsIgnoreCase(getIdentifier())) {
			//	ANI id name maxWait highUse src
			name = args[2];
			id = Integer.parseInt(args[1]);
			maxWait = Integer.parseInt(args[3]);
			highUseage = Boolean.parseBoolean(args[4]);
			loadFramesReference(args, 5);
		}
		return this;
	}

	@Override
	public Animation load(GameFile file, int line) {
		return load(file.getLines().get(line));
	}
	
	public void loadHighUseage() {
		if(highUseage) {
			framesImage = new ArrayList<Image>();
			for(int i = 0; i < framesReference.size(); i++) {
				framesImage.add(i, (Image) Engine2.GLOBAL_RESOURCE_REFERENCE.getResources().get(framesReference.get(i)));
			}
		}
		framesImage = null;
	}
	
	private void loadFramesReference(String[] args, int start) {
		int id = -1;
		for(int i = start; i < args.length; i++) {
			id = Integer.parseInt(args[i]);
			framesReference.add(id);
			if(highUseage) {
				framesImage.add((Image) Engine2.GLOBAL_RESOURCE_REFERENCE.getResources().get(id));
			}
		}
	}

	@Override
	public GameFile save(GameFile file) {
		return file;
	}
}

package com.estrelsteel.engine2.setting;

import java.util.ArrayList;
import java.util.Iterator;

import com.estrelsteel.engine2.file.GameFile;
import com.estrelsteel.engine2.file.Saveable;
import com.estrelsteel.engine2.setting.BaseSetting;

@SuppressWarnings("hiding")
public class Settings<BaseSetting> implements Iterator<BaseSetting>, Saveable {
	
	private String name;
	private String path;
	private ArrayList<BaseSetting> settings;

	public Settings(String name, String path) {
		this.name = name;
		this.path = path;
		this.settings = new ArrayList<BaseSetting>();
	}
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	@Override
	public boolean hasNext() {
		return settings.iterator().hasNext();
	}

	@Override
	public BaseSetting next() {
		return settings.iterator().next();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String getIdentifier() {
		return "SET";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Settings<BaseSetting> load(GameFile file, int line) {
		if(file.getLines().get(line).equalsIgnoreCase(getIdentifier())) {
			if(com.estrelsteel.engine2.setting.BaseSetting.types == null) {
				com.estrelsteel.engine2.setting.BaseSetting.loadTypes();
			}
			for(line++; line < file.getLines().size(); line++) {
				for(com.estrelsteel.engine2.setting.BaseSetting setting : com.estrelsteel.engine2.setting.BaseSetting.types) {
					if(setting.getIdentifier().equalsIgnoreCase(file.getLines().get(line).substring(0, 3))) {
						settings.add((BaseSetting) setting.load(file, line));
					}
				}
			}
		}
		return this;
	}

	@Override
	public GameFile save(GameFile file) {
		return null;
	}
}

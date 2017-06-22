package com.estrelsteel.engine2.setting;

import java.io.IOException;
import java.util.ArrayList;

import com.estrelsteel.engine2.file.GameFile;
import com.estrelsteel.engine2.file.Saveable;

public class Settings implements Saveable {
	
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
	
	public ArrayList<BaseSetting> getSettings() {
		return settings;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	public void setSettings(ArrayList<BaseSetting> settings) {
		this.settings = settings;
	}
	
	@Override
	public String getIdentifier() {
		return "SET";
	}
	
	public BaseSetting findSetting(String name) {
		for(BaseSetting s : settings) {
			if(s.getName().equalsIgnoreCase(name)) {
				return s;
			}
		}
		return null;
	}

	public Settings load() {
		GameFile file = new GameFile(path);
		try {
			file.setLines(file.readFile());
			return load(file, 0);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Settings load(GameFile file, int line) {
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
	
	public GameFile save() {
		GameFile file = new GameFile(path);
		return save(file);
	}

	@Override
	public GameFile save(GameFile file) {
		for(BaseSetting s : settings) {
			s.save(file);
		}
		return file;
	}
}

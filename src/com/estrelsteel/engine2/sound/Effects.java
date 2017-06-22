package com.estrelsteel.engine2.sound;

import com.estrelsteel.engine2.file.GameFile;

public enum Effects {
	INVALID(-1, null);

	private int id;
	private Sound sound;
	
	public static String soundPath = GameFile.getDataPath() + "/res/sfx";
	
	Effects(int id, Sound sound) {
		this.id = id;
		this.sound = sound;
	}
	
	public int getID() {
		return id;
	}
	
	public Sound getSound() {
		return sound;
	}
	
	public static Effects findByID(int id) {
		for(Effects sfx : values()) {
			if(sfx.getID() == id) {
				return sfx;
			}
		}
		return Effects.INVALID;
	}
	
	public static void updateSRC(String filesPath) {
		for(int i = 0; i < Effects.values().length; i++) {
			Effects.values()[i].setSRC(filesPath + Effects.values()[i].getSound().getSRC());
		}
	}
	
	private void setSRC(String src) {
		//sound.setSRC(src);
		sound = new Sound(sound.getName(), src);
	}
}

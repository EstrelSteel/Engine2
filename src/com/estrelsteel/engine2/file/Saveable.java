package com.estrelsteel.engine2.file;


public interface Saveable {
	public String getIdentifier();
	public Object load(GameFile file, int line);
	public GameFile save(GameFile file);
}

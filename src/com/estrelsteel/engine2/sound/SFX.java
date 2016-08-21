package com.estrelsteel.engine2.sound;

import java.io.IOException;
import java.util.ArrayList;

import com.estrelsteel.engine2.file.GameFile;
import com.estrelsteel.engine2.file.Saveable;

public class SFX implements Saveable {

	public static ArrayList<Sound> sounds;
	
	public SFX() {
		sounds = new ArrayList<Sound>();
	}
	
	public static ArrayList<Sound> getSounds() {
		return sounds;
	}

	@Override
	public String getIdentifier() {
		return "SAR";
	}

	@Override
	public SFX load(GameFile file, int line) {
		try {
			file.updateLines();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		if(file.getLines().get(line).equalsIgnoreCase(getIdentifier())) {
			Sound s;
			for(line++; line < file.getLines().size(); line++) {
				s = new Sound("", "");
				s.load(file, line);
				sounds.add(s);
			}
		}
		return null;
	}

	@Override
	public GameFile save(GameFile file) {
		file.getLines().add(getIdentifier());
		for(Sound s : sounds) {
			s.save(file);
		}
		return file;
	}

}

package com.estrelsteel.engine2.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.estrelsteel.engine2.file.GameFile;
import com.estrelsteel.engine2.file.Saveable;

public class Sound implements Saveable {
	private String name;
	private String src;
	private Clip clip;
	private float vol;
	
	public Sound(String name, String src) {
		this.name = name;
		this.src = src;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSRC() {
		return src;
	}
	
	public Clip getClip() {
		return clip;
	}
	
	public synchronized void play() {
		if(clip != null && clip.isRunning()) {
			FloatControl volume = (FloatControl) SFX.getSounds().get(1).getClip().getControl(FloatControl.Type.MASTER_GAIN);
			volume.setValue(vol);
		}
		if(clip == null || !clip.isRunning()) {
			try {
				File file = new File(src);
				clip = AudioSystem.getClip();
				AudioInputStream ais = AudioSystem.getAudioInputStream(file);
				clip.open(ais);
				FloatControl volume = (FloatControl) SFX.getSounds().get(1).getClip().getControl(FloatControl.Type.MASTER_GAIN);
				volume.setValue(vol);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} 
			catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			clip.flush();
			clip.start();
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSRC(String src) {
		this.src = src;
	}
	
	public void setClip(Clip clip) {
		this.clip = clip;
	}

	@Override
	public String getIdentifier() {
		return "SFX";
	}

	@Override
	public Sound load(GameFile file, int line) {
		String[] args = file.getLines().get(line).split(" ");
		if(args[0].trim().equalsIgnoreCase(getIdentifier())) {
			name = args[1].trim();
			src = GameFile.getDataPath() + args[2].trim();
			vol = Float.parseFloat(args[3].trim());
		}
		return this;
	}

	@Override
	public GameFile save(GameFile file) {
		src = src.substring(GameFile.getDataPath().length());
		file.getLines().add(getIdentifier() + " " + name + " " + src + " " + vol);
		return file;
	}
}

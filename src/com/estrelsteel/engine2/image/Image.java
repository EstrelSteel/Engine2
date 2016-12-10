package com.estrelsteel.engine2.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Image {
	private BufferedImage img;
	private String src;
	
	public Image(String src) {
		this.src = src;
	}
	
	public BufferedImage getImage() {
		return this.img;
	}
	
	public String getSRC() {
		return this.src;
	}
	
	public boolean equals(Object other) {
		if(src.equals(((Image) other).getSRC())) {
			return true;
		}
		return false;
	}
	
	public void loadImage() {
		InputStream is = getClass().getResourceAsStream(src);
		try {
			img = (BufferedImage) ImageIO.read(new File(src));
		}
		catch (IOException e1) {
			try {
				System.err.println(src);
				img = ImageIO.read(is);
			}
			catch (IOException e) {
				System.err.println(src);
				e.printStackTrace();
			}
		}
		
		
	}
	
	public boolean isImageLoaded() {
		if(img != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void unloadImage() {
		this.img = null;
	}
	
	public void setSRC(String src) {
		this.src = src;
	}
	
	public void setImage(BufferedImage img) {
		this.img = img;
	}
}

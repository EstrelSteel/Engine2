package com.estrelsteel.engine2.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.estrelsteel.engine2.resource.Resource;

public class Image implements Resource {
	private int id;
	private BufferedImage img;
	private String src;
	
	public Image(String src, int id) {
		this.src = src;
		this.id = id;
	}
	
	@Deprecated
	public Image(String src) {
		this.src = src;
		this.id = -1;
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
	
	public Image loadImage() {
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
		return this;
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

	@Override
	public int getID() {
		return id;
	}
}

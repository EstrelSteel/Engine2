package com.estrelsteel.engine2.chunk;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.estrelsteel.engine2.file.GameFile;
import com.estrelsteel.engine2.grid.PixelGrid;
import com.estrelsteel.engine2.image.Image;
import com.estrelsteel.engine2.image.Renderable;
import com.estrelsteel.engine2.shape.Rotation;
import com.estrelsteel.engine2.shape.rectangle.Rectangle;
import com.estrelsteel.engine2.world.FrozenWorld;
import com.estrelsteel.engine2.world.World;

public class ImageChunk extends Chunk {

	private Image saveImg;
	private Rotation r;
	private double transX;
	private double transY;
	private double transR;
	
	public ImageChunk(Rectangle loc) {
		super(loc);
		this.saveImg = null;
		this.r = new Rotation(0);
	}
	
	public Image getSavedImage() {
		return saveImg;
	}
	
	public Rotation getRotation() {
		return r;
	}
	
	public static ImageChunk convert(Chunk c) {
		ImageChunk imgC = new ImageChunk(c.getLocation());
		imgC.setObjects(c.getObjects());
		imgC.setSortable(c.isSortable());
		imgC.generateImage();
		return imgC;
	}
	
	public void generateImage() {
		if(getObjects() != null) {
			BufferedImage img = new BufferedImage((int) getLocation().getWidth(), (int) getLocation().getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D ctx = img.createGraphics();
			World temp = new World(new PixelGrid());
			temp.getMainCamera().setLocation(getLocation().getTop());
			for(int i = 0; i < getObjects().size(); i++) {
				ctx = getObjects().get(i).simpleRender(ctx, temp);
			}
			ctx.dispose();
			
			saveImg = new Image("null", -1);
			saveImg.setImage(img);
		}
	}
	
	@Override
	public Graphics2D render(Graphics2D ctx, FrozenWorld world) {
		if(saveImg == null) {
			generateImage();
		}
		if(saveImg == null) {
			super.render(ctx, world);
		}
		else {
			AffineTransform trans;
			transX = 0;
			transY = 0;
			transR = 0;
			if(world instanceof World) {
				transX = world.getGrid().moveToGrid(((World) world).getMainCamera().getLocation()).getX();
				transY = world.getGrid().moveToGrid(((World) world).getMainCamera().getLocation()).getY();
				transR = ((World) world).getMainCamera().getRotation().getRadians();
			}
			
			trans = new AffineTransform();
			
			trans.translate(world.getGrid().moveToGrid(getLocation().getTop()).getX() - transX,
					world.getGrid().moveToGrid(getLocation().getTop()).getY() - transY);
			trans.scale(getLocation().getWidth() / getSavedImage().getImage().getWidth(), getLocation().getHeight() / getSavedImage().getImage().getHeight());
			trans.rotate(getRotation().getRadians() + transR);
			
			if((getLocation().getX() - transX >= -640 || getLocation().getX() + getLocation().getWidth() - transX >= -640) && getLocation().getX() - transX <= 1280
					&& (getLocation().getY() - transY >= -640 || getLocation().getY() + getLocation().getHeight() - transY >= -640) && getLocation().getY() - transY <= 1280) {
				if((getLocation().getX() - transX >= 0 || getLocation().getX() + getLocation().getWidth() - transX >= 0) && getLocation().getX() - transX <= 640 
						&& (getLocation().getY() - transY >= 0 || getLocation().getY() + getLocation().getHeight() - transY >= 0) && getLocation().getY() - transY <= 640) {
					if(!getSavedImage().isImageLoaded()) {
						getSavedImage().loadImage();
					}
					ctx.drawImage(getSavedImage().getImage(), trans, null);
				}
			}
			else {
				int i = world.getObjects().indexOf(this);
				((Chunk) world.getObjects().get(i)).setSaveMe(true);
			}
			trans = null;
		}
		return ctx;
	}
	
	@Override
	public Graphics2D simpleRender(Graphics2D ctx, FrozenWorld world) {
		if(saveImg == null) {
			generateImage();
		}
		if(saveImg == null) {
			super.simpleRender(ctx, world);
		}
		else {
			double transX = 0;
			double transY = 0;
			if(world instanceof World) {
				transX = world.getGrid().moveToGrid(((World) world).getMainCamera().getLocation()).getX();
				transY = world.getGrid().moveToGrid(((World) world).getMainCamera().getLocation()).getY();
			}
			if((getLocation().getX() - transX >= -640 || getLocation().getX() + getLocation().getWidth() - transX >= -640) && getLocation().getX() - transX <= 1280
					&& (getLocation().getY() - transY >= -640 || getLocation().getY() + getLocation().getHeight() - transY >= -640) && getLocation().getY() - transY <= 1280) {
				if((getLocation().getX() - transX >= 0 || getLocation().getX() + getLocation().getWidth() - transX >= 0) && getLocation().getX() - transX <= 640 
						&& (getLocation().getY() - transY >= 0 || getLocation().getY() + getLocation().getHeight() - transY >= 0) && getLocation().getY() - transY <= 640) {
					if(!getSavedImage().isImageLoaded()) {
						getSavedImage().loadImage();
					}
					ctx.drawImage(getSavedImage().getImage(),
							(int) (getLocation().getTop().getX() - transX), (int) (getLocation().getTop().getY() - transY),
							(int) (getLocation().getWidth()), (int) (getLocation().getHeight()), null);
				}
			}
			else {
				int i = world.getObjects().indexOf(this);
				((Chunk) world.getObjects().get(i)).setSaveMe(true);
			}
		}
		return ctx;
	}
	
	public void setSavedImage(Image saveImg) {
		this.saveImg = saveImg;
	}

	public void setRotation(Rotation r) {
		this.r = r;
	}
	
	public Renderable load(GameFile file, int line) {
		return convert((Chunk) super.load(file, line));
	}
}

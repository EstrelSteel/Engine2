package com.estrelsteel.engine2.world;

import java.util.ArrayList;

import com.estrelsteel.engine2.actor.Camera;
import com.estrelsteel.engine2.grid.Grid;
import com.estrelsteel.engine2.point.Point2;

public class World extends FrozenWorld {
	private ArrayList<Camera> cameras;
	private Camera mainCamera;
	
	public World(Grid grid) {
		super(grid);
		this.cameras = new ArrayList<Camera>();
		this.mainCamera = new Camera("Main", new Point2(0, 0, grid));
		this.cameras.add(this.mainCamera);
	}
	
	/**
	 * Gets the cameras that are held in this world
	 * @return an array of cameras
	 * @see Camera
	 */
	public ArrayList<Camera> getCameras() {
		return cameras;
	}
	
	/**
	 * Gets the current main camera, which is the camera being used to render
	 * @return the main camera
	 * @see Camera
	 */
	public Camera getMainCamera() {
		return mainCamera;
	}
	
	public boolean equals(Object other) {
		if(cameras.equals(((World) other).getCameras()) && super.equals(other)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Sets the cameras in the world.
	 * @param an array list of cameras
	 * @return void
	 * @see Camera
	 */
	public void setCameras(ArrayList<Camera> cameras) {
		this.cameras = cameras;
	}
	
	/**
	 * Sets the main camera of the world.
	 * @param a new main camera
	 * @return void
	 * @see Camera
	 */
	public void setMainCamera(Camera mainCamera) {
		this.mainCamera = mainCamera;
	}
}

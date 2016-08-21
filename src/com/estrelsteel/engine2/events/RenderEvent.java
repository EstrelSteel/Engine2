package com.estrelsteel.engine2.events;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.estrelsteel.engine2.events.listener.RenderListener;

public class RenderEvent {
	private ArrayList<RenderListener> listeners;
	
	public RenderEvent() {
		listeners = new ArrayList<RenderListener>();
	}
	
	public void addListener(RenderListener listener) {
		listeners.add(listener);
	}
	
	public void render(Graphics2D ctx) {
		for(RenderListener listener : listeners) {
			listener.render(ctx);
		}
	}
}

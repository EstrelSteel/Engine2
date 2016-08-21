package com.estrelsteel.engine2.events;

import java.util.ArrayList;

import com.estrelsteel.engine2.events.listener.TickListener;

public class TickEvent {
	private ArrayList<TickListener> listeners;
	
	public TickEvent() {
		listeners = new ArrayList<TickListener>();
	}
	
	public void addListener(TickListener listener) {
		listeners.add(listener);
	}
	
	public void tick() {
		for(TickListener listener : listeners) {
			listener.tick();
		}
	}
}

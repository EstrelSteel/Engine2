package com.estrelsteel.engine2.events;

import java.util.ArrayList;

import com.estrelsteel.engine2.events.listener.StopListener;

public class StopEvent {
	private ArrayList<StopListener> listeners;
	
	public StopEvent() {
		listeners = new ArrayList<StopListener>();
	}
	
	public void addListener(StopListener listener) {
		listeners.add(listener);
	}
	
	public void stop() {
		for(StopListener listener : listeners) {
			listener.stop();
		}
	}
}

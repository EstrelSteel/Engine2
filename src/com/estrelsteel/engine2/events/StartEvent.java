package com.estrelsteel.engine2.events;

import java.util.ArrayList;

import com.estrelsteel.engine2.events.listener.StartListener;

public class StartEvent {
	private ArrayList<StartListener> listeners;
	
	public StartEvent() {
		listeners = new ArrayList<StartListener>();
	}
	
	public void addListener(StartListener listener) {
		listeners.add(listener);
	}
	
	public void start() {
		for(StartListener listener : listeners) {
			listener.start();
		}
	}
}

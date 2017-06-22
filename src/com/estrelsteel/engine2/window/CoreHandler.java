package com.estrelsteel.engine2.window;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowStateListener;

import com.estrelsteel.engine2.Engine2;

public class CoreHandler extends WindowAdapter implements WindowStateListener, WindowFocusListener, FocusListener, ComponentListener {

	Engine2 engine;
	
	public CoreHandler(Engine2 engine) {
		this.engine = engine;
	}

	public void windowClosing(WindowEvent e) {
		engine.stop();
    }

	public void focusGained(FocusEvent e) {
//		System.out.println("focus gained");
	}

	public void focusLost(FocusEvent e) {
//		System.out.println("focus lost");
	}

	public void componentHidden(ComponentEvent e) {
		
	}

	public void componentMoved(ComponentEvent e) {
		
	}

	public void componentResized(ComponentEvent e) {

	}

	public void componentShown(ComponentEvent e) {
		
	}
}

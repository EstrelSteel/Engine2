
package com.estrelsteel.engine2;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.estrelsteel.engine2.window.CoreHandler;

@SuppressWarnings("serial")
public class Launcher extends Applet {
	private static Engine2 engine = new Engine2();
	
	public Engine2 getEngine() {
		return engine;
	}
	
	public void init() {
		setLayout(new BorderLayout());
		add(engine, BorderLayout.CENTER);
		setFocusable(true);
		setMinimumSize(new Dimension((int) engine.getWindowSettings().getRectangle().getWidth(), (int) engine.getWindowSettings().getRectangle().getHeight()));
		setMaximumSize(new Dimension((int) engine.getWindowSettings().getRectangle().getWidth(), (int) engine.getWindowSettings().getRectangle().getHeight()));
		setPreferredSize(new Dimension((int) engine.getWindowSettings().getRectangle().getWidth(), (int) engine.getWindowSettings().getRectangle().getHeight()));
		setVisible(true);
		engine.getStats().setApplet(true);
	}
	
	@Override
	public void start() {
		engine.start();
		return;
	}
	
	@Override
	public void stop() {
		engine.stop();
		return;
	}
	
	public JFrame boot() {
		engine.setFocusable(true);
		engine.setMinimumSize(new Dimension((int) engine.getWindowSettings().getRectangle().getWidth(), (int) engine.getWindowSettings().getRectangle().getHeight()));
		engine.setMaximumSize(new Dimension((int) engine.getWindowSettings().getRectangle().getWidth(), (int) engine.getWindowSettings().getRectangle().getHeight()));
		engine.setPreferredSize(new Dimension((int) engine.getWindowSettings().getRectangle().getWidth(), (int) engine.getWindowSettings().getRectangle().getHeight()));
		
		JFrame frame = new JFrame(engine.getWindowSettings().getTitle() + " " + engine.getWindowSettings().getVersion() + " (" + engine.getWindowSettings().getBuild() + ")");
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		engine.coreHandler = new CoreHandler(engine);
		
		frame.addWindowListener(engine.coreHandler);
		frame.addWindowFocusListener(engine.coreHandler);
		frame.addComponentListener(engine.coreHandler);
		
		frame.add(engine, BorderLayout.CENTER);
		
		frame.setSize((int) engine.getWindowSettings().getRectangle().getWidth(), (int) engine.getWindowSettings().getRectangle().getHeight());
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		engine.start();
		return frame;
	}

	
	
	public static void main(String[] args) {
		new Launcher().boot();
	}
}

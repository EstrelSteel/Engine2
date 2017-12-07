package com.estrelsteel.engine2;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import com.estrelsteel.engine2.events.RenderEvent;
import com.estrelsteel.engine2.events.StartEvent;
import com.estrelsteel.engine2.events.StopEvent;
import com.estrelsteel.engine2.events.TickEvent;
import com.estrelsteel.engine2.file.GameFile;
import com.estrelsteel.engine2.grid.PixelGrid;
import com.estrelsteel.engine2.point.AbstractedPoint;
import com.estrelsteel.engine2.shape.rectangle.AbstractedRectangle;
import com.estrelsteel.engine2.window.CoreHandler;
import com.estrelsteel.engine2.window.Stats;
import com.estrelsteel.engine2.window.WindowSettings;

public class Engine2 extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public CoreHandler coreHandler;
	public static boolean showFPS = true;
	public static boolean development = false;
	
	private Thread thread; 
	
	private Stats stats = new Stats();
	private WindowSettings windowSettings = new WindowSettings(
			new AbstractedRectangle(new AbstractedPoint(0, 0), new AbstractedPoint(650, 650)),
			"Engine", "v2.0a", 0);
	
	public final StartEvent START_EVENT = new StartEvent();
	public final RenderEvent RENDER_EVENT = new RenderEvent();
	public final TickEvent TICK_EVENT = new TickEvent();
	public final StopEvent STOP_EVENT = new StopEvent();
	
	public static DecimalFormat ROUNDING_FORMAT = new DecimalFormat("0.####");
	public static String devPath = "";
	public static final PixelGrid pixels = new PixelGrid();
	
	private boolean mac_pressandhold_starting;
	
	public WindowSettings getWindowSettings() {
		return windowSettings;
	}
	
	public Stats getStats() {
		return stats;
	}
	
	public void setWindowSettings(WindowSettings windowSettings) {
		this.windowSettings = windowSettings;
	}
	
	public synchronized void start() {
		if(System.getProperty("os.name").startsWith("Mac")) {
			try {
				Process p = Runtime.getRuntime().exec("defaults read -g ApplePressAndHoldEnabled");

				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()) );
				mac_pressandhold_starting = Boolean.parseBoolean(in.readLine());
				in.close();
				if(mac_pressandhold_starting) {
					Runtime.getRuntime().exec("defaults write -g ApplePressAndHoldEnabled false");
				}
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		stats.setRunning(true);
		
		devPath = GameFile.getDataPath();
		
		coreHandler = new CoreHandler(this);
		addFocusListener(coreHandler);
		
		START_EVENT.start();
		
		thread = new Thread(this, windowSettings.getTitle() + windowSettings.getVersion() + "_main");
		thread.start();
		if(mac_pressandhold_starting) {
			try {
				Runtime.getRuntime().exec("defaults write -g ApplePressAndHoldEnabled true");
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void stop() {
		stats.setRunning(false);
		STOP_EVENT.stop();
		if(thread != null) {
			try {
				thread.join();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if(mac_pressandhold_starting) {
			try {
				Runtime.getRuntime().exec("defaults write -g ApplePressAndHoldEnabled true");
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.exit(0);
	}
	
	public final void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		
		while(stats.isRunning()) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			
			while(delta >= 5) {
				ticks++;
				tick();
				delta = delta - 1;
				shouldRender = true;
			}
			
			try {
				Thread.sleep(2);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(shouldRender) {
				frames = frames + 1;
				render();
			}
			
			if(System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				if(showFPS) {
					System.out.println(frames + " fps, " + ticks + " tps");
				}
				stats.setFPS(frames);
				stats.setTPS(ticks);
				ticks = 0;
				frames = 0;
			}
		}	
	}
	
	public void tick() {
		stats.setTicks(stats.getTicks() + 1);
		windowSettings.getRectangle().getBottom().setX(getWidth());
		windowSettings.getRectangle().getBottom().setY(getWidth());
		
		TICK_EVENT.tick();
	}
	
	public void render() {
		stats.setFrames(stats.getFrames() + 1);
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics2D ctx = (Graphics2D) bs.getDrawGraphics();
		ctx.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		ctx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ctx.clearRect(0, 0, getWidth(), getHeight());
		
		RENDER_EVENT.render(ctx);
		
		ctx.dispose();
		bs.show();
	}
}

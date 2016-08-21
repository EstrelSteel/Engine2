package com.estrelsteel.engine2.setting;

public class ConsoleMessage {
	private String msg;
	private boolean debug;
	private boolean urgent;
	
	public ConsoleMessage(String msg, boolean urgent, boolean debug) {
		this.msg = msg;
		this.urgent = urgent;
		this.debug = debug;
	}
	
	public String getMessage() {
		return msg;
	}
	
	public boolean isDebug() {
		return debug;
	}
	
	public boolean isUrgent() {
		return urgent;
	}
	
	public void setMessage(String msg) {
		this.msg = msg;
	}
	
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}
}

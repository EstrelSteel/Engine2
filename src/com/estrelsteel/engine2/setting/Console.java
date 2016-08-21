package com.estrelsteel.engine2.setting;

import java.util.ArrayList;

public class Console {
	
	private String name;
	private boolean debug;
	private ArrayList<ConsoleMessage> msgs;
	
	public Console(String name, boolean debug) {
		this.name = name;
		this.debug = debug;
		this.msgs = new ArrayList<ConsoleMessage>();
	}
	
	public ArrayList<ConsoleMessage> getMessages() {
		return msgs;
	}
	
	public void addMessage(ConsoleMessage msg) {
		String str = "[" + name + "] ";
		if(debug && msg.isDebug()) {
			str = str + "[DEBUG] ";
		}
		if(msg.isUrgent()) {
			System.err.println(str + msg.getMessage());
		}
		else {
			System.out.println(str + msg.getMessage());
		}
		msgs.add(msg);
	}
	//"Loading game settings...", false ,false
	public void addMessage(String s, boolean urgent, boolean debug) {
		ConsoleMessage msg = new ConsoleMessage(s, urgent, debug);
		String str = "[" + name + "] ";
		if(debug && msg.isDebug()) {
			str = str + "[DEBUG] ";
		}
		if(msg.isUrgent()) {
			System.err.println(str + msg.getMessage());
		}
		else {
			System.out.println(str + msg.getMessage());
		}
		msgs.add(msg);
	}
	
	public void setMessages(ArrayList<ConsoleMessage> msgs) {
		this.msgs = msgs;
	}
}

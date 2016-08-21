package com.estrelsteel.engine2.setting.types;

import com.estrelsteel.engine2.file.GameFile;
import com.estrelsteel.engine2.setting.BaseSetting;

public class IntSetting extends BaseSetting {

	private int value;
	
	public IntSetting(String name) {
		super(name);
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public String getIdentifier() {
		return "INT";
	}

	@Override
	public BaseSetting load(GameFile file, int line) {
		String[] args = file.getLines().get(line).split(" ");
		if(args[0].trim().equalsIgnoreCase(getIdentifier()) && args[2].trim().equalsIgnoreCase("=")) {
			setName(args[1].trim());
			value = Integer.parseInt(args[3].trim());
		}
		return this;
	}

	@Override
	public GameFile save(GameFile file) {
		file.getLines().add(getIdentifier() + " " + getName() + " = " + value);
		return file;
	}

	@Override
	public void setValue(Object value) {
		if(value instanceof Integer) {
			this.value = (Integer) value;
		}
		else {
			System.err.println("INVALID SAVE TYPE");
		}
	}

}

package com.estrelsteel.engine2.setting.types;

import com.estrelsteel.engine2.file.GameFile;
import com.estrelsteel.engine2.setting.BaseSetting;

public class DoubleSetting extends BaseSetting {

	private double value;
	
	public DoubleSetting(String name) {
		super(name);
	}

	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public String getIdentifier() {
		return "DUB";
	}

	@Override
	public BaseSetting load(GameFile file, int line) {
		BaseSetting bs = new DoubleSetting("null");
		String[] args = file.getLines().get(line).split(" ");
		if(args[0].trim().equalsIgnoreCase(getIdentifier()) && args[2].trim().equalsIgnoreCase("=")) {
			bs.setName(args[1].trim());
			bs.setValue(Double.parseDouble(args[3].trim()));
		}
		return bs;
	}

	@Override
	public GameFile save(GameFile file) {
		file.getLines().add(getIdentifier() + " " + getName() + " = " + value);
		return file;
	}

	@Override
	public void setValue(Object value) {
		if(value instanceof Double) {
			this.value = (Double) value;
		}
		else {
			System.err.println("INVALID SAVE TYPE");
		}
	}

}

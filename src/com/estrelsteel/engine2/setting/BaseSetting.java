package com.estrelsteel.engine2.setting;

import java.util.ArrayList;

import com.estrelsteel.engine2.file.GameFile;
import com.estrelsteel.engine2.file.Saveable;
import com.estrelsteel.engine2.setting.types.BooleanSetting;
import com.estrelsteel.engine2.setting.types.DoubleSetting;
import com.estrelsteel.engine2.setting.types.IntSetting;
import com.estrelsteel.engine2.setting.types.StringSetting;

public abstract class BaseSetting implements Saveable {
	
	public static ArrayList<BaseSetting> types;
	
	private String name;
	
	public BaseSetting(String name) {
		this.name = name;
		if(types == null) {
			loadTypes();
		}
	}
	
	public static void loadTypes() {
		types = new ArrayList<BaseSetting>();
		
		types.add(new IntSetting("dummy_int"));
		types.add(new DoubleSetting("dummy_double"));
		types.add(new BooleanSetting("dummy_boolean"));
		types.add(new StringSetting("dummy_string"));
	}
	
	public String getName() {
		return name;
	}
	
	public abstract Object getValue();
	
	public void setName(String name) {
		this.name = name;
	}
	
	public abstract void setValue(Object value);

	@Override
	public abstract String getIdentifier();

	@Override
	public abstract BaseSetting load(GameFile file, int line);

	@Override
	public abstract GameFile save(GameFile file);
}

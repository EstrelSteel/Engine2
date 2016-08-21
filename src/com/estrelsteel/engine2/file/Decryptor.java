package com.estrelsteel.engine2.file;


public class Decryptor {
	
	private GameFile encrypted;
	
	public Decryptor(GameFile file, String key) {
		encrypted = new GameFile(file.getPath());
		encrypted.setLines(file.getLines());
		String line;
		String lineA;
		String lineB;
		char c;
		long lKey = getSimplifiedKey(key);
		key = null;
		for(int i = 0; i < file.getLines().size(); i++) {
			line = file.getLines().get(i);
			for(int j = 0; j < line.length(); j++) {
				lineA = line.substring(0, j);
				lineB = line.substring(j + 1);
				c = line.substring(j, j + 1).toCharArray()[0];
				c = (char) ((int) (c) + (lKey / (i + j + 1)));
				line = lineA + String.valueOf(c) + lineB;
				encrypted.getLines().set(i, line);
			}
		}
		c = 0;
		lineA = null;
		lineB = null;
		line = null;
		file = null;
		lKey = 0;
	}
	
	public static long getSimplifiedKey(String key) {
		long lKey = 0;
		int num;
		for(int i = 0; i < key.length(); i++) {
			num = (int) key.substring(i, i + 1).toCharArray()[0];
			
			if(i % 3 == 1) {
				num = -num;
			}
			lKey = lKey + num;
		}
		num = 0;
		key = null;
		return lKey;
	}
	
	public GameFile getDecryptedFile() {
		return encrypted;
	}
	
	
}

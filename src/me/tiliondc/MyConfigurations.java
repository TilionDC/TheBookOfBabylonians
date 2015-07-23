package me.tiliondc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;

public class MyConfigurations {

	public MyConfigurations() {
	}

	static File file;
	static YamlConfiguration config;

	public static final String DATABASE_HOST = "Database Adress";
	public static final String DATABASE_PORT = "Database Port";
	public static final String DATABASE_USERNAME = "Database Username";
	public static final String DATABASE_PASSWORD = "Database Password";
	public static final String DATABASE_NAME = "Database Name";
	
	public static final String FILE_NAME = "config.yml";

	private static HashMap<String, Object> defaultSettings = new HashMap<>();

	public static HashMap<String, Object> getDefaultSettings() {
		defaultSettings.put(DATABASE_HOST, "localhost");
		defaultSettings.put(DATABASE_PORT, 3306);
		defaultSettings.put(DATABASE_USERNAME, "root");
		defaultSettings.put(DATABASE_PASSWORD, "");
		defaultSettings.put(DATABASE_NAME, "BookOfBabylonians");
		return defaultSettings;
	}
	
	private static void loadFile() {
		file = new File(FILE_NAME);
		config = YamlConfiguration.loadConfiguration(file);
	}

	public static boolean loadConfig() {
		loadFile();
		if(!file.exists()) {
			return createConfig();
		}
		return false;
	}
	
	public static HashMap<String, Object> getSettings() {
		
		@SuppressWarnings("unchecked")
		ArrayList<String> keys = (ArrayList<String>) defaultSettings.keySet();
		HashMap<String, Object> settings = new HashMap<String, Object>();

		for(int i = 0; 0 < defaultSettings.size(); i++) {
			settings.put(keys.get(i), config.get(keys.get(i)));
		}
		
		return settings;
	}

	private static boolean createConfig() {
		config.addDefaults(getDefaultSettings());
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}

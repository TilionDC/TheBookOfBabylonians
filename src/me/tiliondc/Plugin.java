package me.tiliondc;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin{
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		this.getCommand("book").setExecutor(new MyCommandExecutor());
		getLogger().info("The book of babylonians is created!");
		
		
		
		
	}
	
	@Override
	public void onDisable() {

		super.onDisable();
	}

}

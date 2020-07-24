package plugin.riftiro.farmtools;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import plugin.riftiro.farmtools.commands.CommandFarm;
import plugin.riftiro.farmtools.events.ClickEvent;
import plugin.riftiro.farmtools.events.OnBreak;

public class FarmTools extends JavaPlugin {
	//Run when program is enabled
	
	PluginManager manager = getServer().getPluginManager();
	
	@Override
	public void onEnable() {
		getCommand("farm").setExecutor(new CommandFarm());	
		manager.registerEvents(new OnBreak(), this);
		manager.registerEvents(new ClickEvent(), this);
	}
	//Run when program is disabled
	@Override 
	public void onDisable() {
		
	}
	
}

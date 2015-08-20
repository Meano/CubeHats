package net.meano.CubeHats;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CubeHatsMain extends JavaPlugin{
	public void onEnable(){
		//Log开始记录
		getLogger().info("CubeHats 0.1,by Meano. 正在载入.");
		PluginManager PM = Bukkit.getServer().getPluginManager();
		PM.registerEvents(new CubeHatsListeners(this), this);
	}
}

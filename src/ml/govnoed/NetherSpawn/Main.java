package ml.govnoed.NetherSpawn;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		return false;

	}
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (player.getWorld().getEnvironment() == Environment.NORMAL) {
			String loc = player.getWorld().getName();
			World w = Bukkit.getWorld(loc + "_nether");
			player.teleport(w.getSpawnLocation());
		}
		
	}
	
	
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		if (player.getBedSpawnLocation() == null) {
			BukkitRunnable tp = new BukkitRunnable() {
				
				@Override
				public void run() {
					String loc = player.getWorld().getName();
					World w = Bukkit.getWorld(loc + "_nether");
					player.teleport(w.getSpawnLocation());
					
				}
			};
			tp.runTaskLater(this, 10L);
			player.sendMessage(ChatColor.RED + "No overworld for you unless you sleep on the bed somehow...");
			
		}
	}
	
}

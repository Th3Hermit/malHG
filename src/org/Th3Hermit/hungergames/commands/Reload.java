package org.Th3Hermit.hungergames.commands;

import org.Th3Hermit.hungergames.Game;
import org.Th3Hermit.hungergames.GameManager;
import org.Th3Hermit.hungergames.MessageManager;
import org.Th3Hermit.hungergames.SettingsManager;
import org.Th3Hermit.hungergames.MessageManager.PrefixType;
import org.Th3Hermit.hungergames.logging.QueueManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Reload implements SubCommand{

	@Override
	public boolean onCommand(final Player player, String[] args) {
		if(player.hasPermission(permission())){
			if(args.length != 1){
				MessageManager.getInstance().sendMessage(PrefixType.INFO, "Valid reload types <Settings | Games |All>", player);
				MessageManager.getInstance().sendMessage(PrefixType.INFO, "Settings will reload the settings configs and attempt to reapply them", player);
				MessageManager.getInstance().sendMessage(PrefixType.INFO, "Games will reload all games currently running", player);
				MessageManager.getInstance().sendMessage(PrefixType.INFO, "All will attempt to reload the entire plugin", player);

				return true;

			}
			if(args[0].equalsIgnoreCase("settings")){
				SettingsManager.getInstance().reloadChest();
				SettingsManager.getInstance().reloadKits();
				SettingsManager.getInstance().reloadMessages();
				SettingsManager.getInstance().reloadSpawns();
				SettingsManager.getInstance().reloadSystem();
				SettingsManager.getInstance().reloadConfig();
				for(Game g: GameManager.getInstance().getGames()){
					g.reloadConfig(); 
				}
				MessageManager.getInstance().sendMessage(PrefixType.INFO, "Settings Reloaded", player);
			}
			else if(args[0].equalsIgnoreCase("games")){	
				for(Game g:GameManager.getInstance().getGames()){
					QueueManager.getInstance().rollback(g.getID(), true);
					g.disable();
					g.enable();
				}
				MessageManager.getInstance().sendMessage(PrefixType.INFO, "Games Reloaded", player);
			}
			else if(args[0].equalsIgnoreCase("all")){	
				final Plugin pinstance = GameManager.getInstance().getPlugin();
				Bukkit.getPluginManager().disablePlugin(pinstance);
				Bukkit.getPluginManager().enablePlugin(pinstance);
				MessageManager.getInstance().sendMessage(PrefixType.INFO, "Plugin reloaded", player);
			}

		} else {
			MessageManager.getInstance().sendFMessage(PrefixType.ERROR, "error.nopermission", player);
		}
		return true;
	}

	@Override
	public String help(Player p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String permission() {
		return "sg.admin.reload";
	}

}

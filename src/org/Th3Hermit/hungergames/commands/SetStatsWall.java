package org.Th3Hermit.survivalgames.commands;

import org.Th3Hermit.survivalgames.SettingsManager;
import org.bukkit.entity.Player;



public class SetStatsWall implements SubCommand {

    @Override
    public boolean onCommand(Player player, String[] args) {
        //StatsWallManager.getInstance().setStatsSignsFromSelection(player);
        return false;
    }

    
    public String help(Player p){
        return "/sg setstatswall - "+ SettingsManager.getInstance().getMessageConfig().getString("messages.help.setstatswall", "Sets the stats wall");
    }

	@Override
	public String permission() {
		return null;
	}
}
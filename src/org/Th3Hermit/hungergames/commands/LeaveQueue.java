package org.Th3Hermit.hungergames.commands;

import org.Th3Hermit.hungergames.GameManager;
import org.Th3Hermit.hungergames.SettingsManager;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;


public class LeaveQueue implements SubCommand{

    @Override
    public boolean onCommand(Player player, String[] args) {
        GameManager.getInstance().removeFromOtherQueues(player, -1);
        return true;
    }

    @Override
    public String help(Player p) {
        return "/hg lq - " + SettingsManager.getInstance().getMessageConfig().getString("messages.help.leavequeue", "Leave the queue for any queued games");
    }

	@Override
	public String permission() {
		return null;
	}

}

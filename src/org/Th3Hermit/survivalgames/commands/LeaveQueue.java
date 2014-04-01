package org.Th3Hermit.survivalgames.commands;

import org.Th3Hermit.survivalgames.GameManager;
import org.Th3Hermit.survivalgames.SettingsManager;
import org.bukkit.entity.Player;



public class LeaveQueue implements SubCommand{

    @Override
    public boolean onCommand(Player player, String[] args) {
        GameManager.getInstance().removeFromOtherQueues(player, -1);
        return true;
    }

    @Override
    public String help(Player p) {
        return "/sg lq - " + SettingsManager.getInstance().getMessageConfig().getString("messages.help.leavequeue", "Leave the queue for any queued games");
    }

	@Override
	public String permission() {
		return null;
	}

}

package org.Th3Hermit.survivalgames.commands;


import org.Th3Hermit.survivalgames.GameManager;
import org.Th3Hermit.survivalgames.MessageManager;
import org.Th3Hermit.survivalgames.SettingsManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class Vote implements SubCommand {
    
    public boolean onCommand(Player player, String[] args) {
        if (!player.hasPermission(permission()) && !player.isOp()) {
            MessageManager.getInstance().sendFMessage(MessageManager.PrefixType.ERROR, "error.nopermission", player);
            return false;
        }
        int game = GameManager.getInstance().getPlayerGameId(player);
        if(game == -1){
            MessageManager.getInstance().sendMessage(MessageManager.PrefixType.ERROR, "error.notinarena", player);
            return true;
        }

        GameManager.getInstance().getGame(GameManager.getInstance().getPlayerGameId(player)).vote(player);

        return true;
    }
    
    @Override
    public String help(Player p) {
        return "/sg vote - " + SettingsManager.getInstance().getMessageConfig().getString("messages.help.vote", "Votes to start the game");
    }

	@Override
	public String permission() {
		return "sg.arena.vote";
	}
}
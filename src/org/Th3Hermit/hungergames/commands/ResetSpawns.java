package org.Th3Hermit.hungergames.commands;

import org.Th3Hermit.hungergames.MessageManager;
import org.Th3Hermit.hungergames.SettingsManager;
import org.Th3Hermit.hungergames.MessageManager.PrefixType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;



public class ResetSpawns implements SubCommand{

    public boolean onCommand(Player player, String[] args) {
        
        if (!player.hasPermission(permission()) && !player.isOp()) {
            MessageManager.getInstance().sendFMessage(PrefixType.ERROR, "error.nopermission", player);
            return true;
        }
        try{
        SettingsManager.getInstance().getSpawns().set("spawns."+Integer.parseInt(args[0]), null);
        return true;
                } catch (NumberFormatException e) {
                    MessageManager.getInstance().sendFMessage(MessageManager.PrefixType.ERROR, "error.notanumber", player, "input-Arena");
                } catch (NullPointerException e) {
                    MessageManager.getInstance().sendMessage(MessageManager.PrefixType.ERROR, "error.gamenoexist", player);
                }
        return true;
    }   

    @Override
    public String help(Player p) {
        return "/sg resetspawns <id> - " + SettingsManager.getInstance().getMessageConfig().getString("messages.help.resetspawns", "Resets spawns for Arena <id>");
    }

	@Override
	public String permission() {
		return "sg.admin.resetspawns";
	}
}
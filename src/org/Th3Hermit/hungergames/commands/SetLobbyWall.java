package org.Th3Hermit.hungergames.commands;

import org.Th3Hermit.hungergames.SettingsManager;
import org.bukkit.entity.Player;

public class SetLobbyWall implements SubCommand {

	@Override
	public boolean onCommand(final Player player, final String[] args) {
		// player.sendMessage(ChatColor.RED+"This command has been replaced by /sg addwall <arenaid>");
		return true;/*
					 * if(!player.hasPermission("sg.admin.setlobby") &&
					 * !player.isOp()){
					 * player.sendMessage(ChatColor.RED+"No Permission"); return
					 * true; } else if(args.length<1){
					 * player.sendMessage("Please Specify a gameid"); return
					 * true; }
					 * LobbyManager.getInstance().setLobbySignsFromSelection
					 * (player, Integer.parseInt(args[0])); return true;
					 */
	}

	@Override
	public String help(final Player p) {
		return "/hg addwall <id> - "
				+ SettingsManager
						.getInstance()
						.getMessageConfig()
						.getString("messages.help.addwall",
								"Add a lobby stats wall for Arena <id>");
	}

	@Override
	public String permission() {
		return "hg.admin.setlobby";
	}

	// TODO: TAKE A W.E SELECTIONA AND SET THE LOBBY. ALSO SET LOBBY WALL
}
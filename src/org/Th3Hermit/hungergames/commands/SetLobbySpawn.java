package org.Th3Hermit.hungergames.commands;

import org.Th3Hermit.hungergames.MessageManager;
import org.Th3Hermit.hungergames.SettingsManager;
import org.bukkit.entity.Player;

public class SetLobbySpawn implements SubCommand {

	@Override
	public boolean onCommand(final Player player, final String[] args) {
		if (!player.hasPermission(permission()) && !player.isOp()) {
			MessageManager.getInstance().sendFMessage(
					MessageManager.PrefixType.ERROR, "error.nopermission",
					player);
			return true;
		}
		SettingsManager.getInstance().setLobbySpawn(player.getLocation());
		MessageManager.getInstance().sendMessage(
				MessageManager.PrefixType.INFO, "info.lobbyspawn", player);
		return true;
	}

	@Override
	public String help(final Player p) {
		return "/hg setlobbyspawn - "
				+ SettingsManager
						.getInstance()
						.getMessageConfig()
						.getString("messages.help.setlobbyspawn",
								"Set the lobby spawnpoint");
	}

	@Override
	public String permission() {
		return "hg.admin.setlobby";
	}
}

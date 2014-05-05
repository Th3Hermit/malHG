package org.Th3Hermit.hungergames.commands;

import org.Th3Hermit.hungergames.MessageManager;
import org.Th3Hermit.hungergames.MessageManager.PrefixType;
import org.Th3Hermit.hungergames.SettingsManager;
import org.bukkit.entity.Player;

public class ResetSpawns implements SubCommand {

	@Override
	public boolean onCommand(final Player player, final String[] args) {

		if (!player.hasPermission(permission()) && !player.isOp()) {
			MessageManager.getInstance().sendFMessage(PrefixType.ERROR,
					"error.nopermission", player);
			return true;
		}
		try {
			SettingsManager.getInstance().getSpawns()
					.set("spawns." + Integer.parseInt(args[0]), null);
			return true;
		} catch (final NumberFormatException e) {
			MessageManager.getInstance().sendFMessage(
					MessageManager.PrefixType.ERROR, "error.notanumber",
					player, "input-Arena");
		} catch (final NullPointerException e) {
			MessageManager.getInstance().sendMessage(
					MessageManager.PrefixType.ERROR, "error.gamenoexist",
					player);
		}
		return true;
	}

	@Override
	public String help(final Player p) {
		return "/hg resetspawns <id> - "
				+ SettingsManager
						.getInstance()
						.getMessageConfig()
						.getString("messages.help.resetspawns",
								"Resets spawns for Arena <id>");
	}

	@Override
	public String permission() {
		return "hg.admin.resetspawns";
	}
}
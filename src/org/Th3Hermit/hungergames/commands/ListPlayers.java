package org.Th3Hermit.hungergames.commands;

import org.Th3Hermit.hungergames.GameManager;
import org.Th3Hermit.hungergames.MessageManager;
import org.Th3Hermit.hungergames.SettingsManager;
import org.bukkit.entity.Player;

public class ListPlayers implements SubCommand {

	@Override
	public boolean onCommand(final Player player, final String[] args) {
		int gid = 0;
		try {
			if (args.length == 0) {
				gid = GameManager.getInstance().getPlayerGameId(player);
			} else {
				gid = Integer.parseInt(args[0]);
			}

			final String[] msg = GameManager.getInstance().getStringList(gid)
					.split("\n");
			player.sendMessage(msg);
			return false;
		} catch (final NumberFormatException ex) {
			MessageManager.getInstance().sendFMessage(
					MessageManager.PrefixType.ERROR, "error.notanumber",
					player, "input-Arena");
		} catch (final NullPointerException ex) {
			MessageManager.getInstance().sendMessage(
					MessageManager.PrefixType.ERROR, "error.gamenoexist",
					player);
		}
		return false;
	}

	@Override
	public String help(final Player p) {
		return "/list - "
				+ SettingsManager
						.getInstance()
						.getMessageConfig()
						.getString("messages.help.listplayers",
								"List all players in the arena you are playing in");
	}

	@Override
	public String permission() {
		return "";
	}

}
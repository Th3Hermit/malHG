package org.Th3Hermit.hungergames.commands;

import org.Th3Hermit.hungergames.GameManager;
import org.Th3Hermit.hungergames.MessageManager;
import org.Th3Hermit.hungergames.SettingsManager;
import org.bukkit.entity.Player;

public class Vote implements SubCommand {

	@Override
	public boolean onCommand(final Player player, final String[] args) {
		if (!player.hasPermission(permission()) && !player.isOp()) {
			MessageManager.getInstance().sendFMessage(
					MessageManager.PrefixType.ERROR, "error.nopermission",
					player);
			return false;
		}
		final int game = GameManager.getInstance().getPlayerGameId(player);
		if (game == -1) {
			MessageManager.getInstance()
					.sendMessage(MessageManager.PrefixType.ERROR,
							"error.notinarena", player);
			return true;
		}

		GameManager.getInstance()
				.getGame(GameManager.getInstance().getPlayerGameId(player))
				.vote(player);

		return true;
	}

	@Override
	public String help(final Player p) {
		return "/hg vote - "
				+ SettingsManager
						.getInstance()
						.getMessageConfig()
						.getString("messages.help.vote",
								"Votes to start the game");
	}

	@Override
	public String permission() {
		return "hg.arena.vote";
	}
}
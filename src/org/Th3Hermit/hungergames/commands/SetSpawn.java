package org.Th3Hermit.hungergames.commands;

import java.util.HashMap;

import org.Th3Hermit.hungergames.Game;
import org.Th3Hermit.hungergames.GameManager;
import org.Th3Hermit.hungergames.MessageManager;
import org.Th3Hermit.hungergames.SettingsManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SetSpawn implements SubCommand {

	HashMap<Integer, Integer> next = new HashMap<Integer, Integer>();

	public SetSpawn() {

	}

	public void loadNextSpawn() {
		for (final Game g : GameManager.getInstance().getGames()
				.toArray(new Game[0])) { // Avoid Concurrency problems
			next.put(g.getID(),
					SettingsManager.getInstance().getSpawnCount(g.getID()) + 1);
		}
	}

	@Override
	public boolean onCommand(final Player player, final String[] args) {
		if (!player.hasPermission(permission()) && !player.isOp()) {
			MessageManager.getInstance().sendFMessage(
					MessageManager.PrefixType.ERROR, "error.nopermission",
					player);
			return true;
		}

		loadNextSpawn();
		System.out.println("settings spawn");
		final Location l = player.getLocation();
		final int game = GameManager.getInstance().getBlockGameId(l);
		System.out.println(game + " " + next.size());
		if (game == -1) {
			MessageManager.getInstance()
					.sendMessage(MessageManager.PrefixType.ERROR,
							"error.notinarena", player);
			return true;
		}
		int i = 0;
		if (args[0].equalsIgnoreCase("next")) {
			i = next.get(game);
			next.put(game, next.get(game) + 1);
		} else {
			try {
				i = Integer.parseInt(args[0]);
				if (i > next.get(game) + 1 || i < 1) {
					MessageManager.getInstance().sendFMessage(
							MessageManager.PrefixType.ERROR, "error.between",
							player, "num-" + next.get(game));
					return true;
				}
				if (i == next.get(game)) {
					next.put(game, next.get(game) + 1);
				}
			} catch (final Exception e) {
				MessageManager.getInstance().sendMessage(
						MessageManager.PrefixType.ERROR, "error.badinput",
						player);
				return false;
			}
		}
		if (i == -1) {
			MessageManager.getInstance().sendMessage(
					MessageManager.PrefixType.ERROR, "error.notinside", player);
			return true;
		}
		SettingsManager.getInstance().setSpawn(game, i, l);
		MessageManager.getInstance().sendFMessage(
				MessageManager.PrefixType.INFO, "info.spawnset", player,
				"num-" + i, "arena-" + game);
		return true;
	}

	@Override
	public String help(final Player p) {
		return "/hg setspawn next - "
				+ SettingsManager
						.getInstance()
						.getMessageConfig()
						.getString("messages.help.setspawn",
								"Sets a spawn for the arena you are located in");
	}

	@Override
	public String permission() {
		return "hg.admin.setarenaspawns";
	}
}
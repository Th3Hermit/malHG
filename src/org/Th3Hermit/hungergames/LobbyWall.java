package org.Th3Hermit.hungergames;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class LobbyWall {

	private final ArrayList<Sign> signs = new ArrayList<Sign>();
	private final ArrayList<String> msgqueue = new ArrayList<String>();
	private final int gameid;

	public LobbyWall(final int gid) {
		gameid = gid;
	}

	public boolean loadSign(final World w, final int x1, final int x2,
			final int z1, final int z2, final int y1) {
		final boolean usingx = (x1 == x2) ? false : true;
		HungerGames.debug(w + " " + x1 + " " + x2 + " " + z1 + " " + z2 + " "
				+ y1 + " " + usingx);
		final BlockFace facing = ((org.bukkit.material.Sign) new Location(w,
				x1, y1, z1).getBlock().getState()).getFacing();

		if (usingx) {
			for (int a = Math.max(x1, x2); a >= Math.min(x1, x2); a--) {
				final Location l = new Location(w, a, y1, z1);
				final BlockState b = l.getBlock().getState();
				if (b instanceof Sign) {
					signs.add((Sign) b);
					LobbyManager.lobbychunks.add(b.getChunk());
					HungerGames.debug("usingx - " + b.getLocation().toString());
				} else {
					HungerGames.debug("Not a sign" + b.getType().toString());
					return false;
				}
			}
		} else {
			for (int a = Math.min(z1, z2); a <= Math.max(z1, z2); a++) {
				HungerGames.debug(a);
				final Location l = new Location(w, x1, y1, a);
				final BlockState b = l.getBlock().getState();
				if (b instanceof Sign) {
					signs.add((Sign) b);
					LobbyManager.lobbychunks.add(b.getChunk());
					HungerGames.debug("notx - " + b.getLocation().toString());
				} else {
					HungerGames.debug("Not a sign" + b.getType().toString());
					return false;
				}
			}
		}
		// HungerGames.debug("dir: " + dir);
		if (facing == BlockFace.NORTH || facing == BlockFace.EAST) {
			Collections.reverse(signs);
		}
		addMsg("HungerGames");
		addMsg("Minealot");
		addMsg("minealot.com");
		addMsg("Game id: " + gameid);
		update();
		return true;
	}

	public void update() {
		// System.out.println(gameid);
		if (msgqueue.size() > 0) {
			display();
			Bukkit.getScheduler().scheduleSyncDelayedTask(
					GameManager.getInstance().getPlugin(), new Runnable() {
						@Override
						public void run() {
							display();
							update();
						}
					}, 20L);
			return;
		}
		clear();
		final Game game = GameManager.getInstance().getGame(gameid);
		final Sign s0 = signs.get(0);
		final Sign s1 = signs.get(1);

		// sign 0
		s0.setLine(0, "[HungerGames]");
		s0.setLine(1, "Click to join");
		s0.setLine(2, "Arena " + gameid);

		// sign 1
		s1.setLine(0, game.getName());
		s1.setLine(1, game.getMode() + "");
		s1.setLine(
				2,
				game.getActivePlayers()
						+ "/"
						+ ChatColor.GRAY
						+ game.getInactivePlayers()
						+ ChatColor.BLACK
						+ "/"
						+ SettingsManager.getInstance().getSpawnCount(
								game.getID()));

		// live update line s1
		if (game.getMode() == Game.GameMode.STARTING) {
			s1.setLine(3, game.getCountdownTime() + "");
		} else if (game.getMode() == Game.GameMode.RESETING
				|| game.getMode() == Game.GameMode.FINISHING) {
			s1.setLine(3, game.getRBStatus());
			if (game.getRBPercent() > 100) {
				s1.setLine(1, "Saving Queue");
				s1.setLine(3, (int) game.getRBPercent() + " left");
			} else
				s1.setLine(3, (int) game.getRBPercent() + "%");
		} else {
			s1.setLine(3, "");
		}

		// live player data
		final ArrayList<String> display = new ArrayList<String>();
		for (final Player p : game.getAllPlayers()) {
			display.add((game.isPlayerActive(p) ? ChatColor.BLACK
					: ChatColor.GRAY) + p.getName());
		}

		try {
			int no = 2;
			int line = 0;
			for (final String s : display) {
				signs.get(no).setLine(line, s);
				line++;
				if (line >= 4) {
					line = 0;
					no++;
				}
			}
		} catch (final Exception e) {
		}
		for (final Sign s : signs) {
			s.update();
		}
	}

	public void clear() {
		for (final Sign s : signs) {
			for (int a = 0; a < 4; a++) {
				s.setLine(a, "");
			}
			s.update();
		}
	}

	public void addMsg(final String s) {
		msgqueue.add(s);

	}

	int displaytid = 0;

	public void display() {
		int a = 0;
		while (msgqueue.size() > 0 && a < 4) {
			final String s = msgqueue.get(0);
			for (int b = 0; b < s.length() / 16; b++) {
				try {
					signs.get(b).setLine(a, s.substring(b * 16, (b + 1) * 16));

					signs.get(b).update();
				} catch (final Exception e) {
				}
			}
			a++;
			msgqueue.remove(0);
		}

	}

	int aniline = 0;

	class AniSign implements Runnable {
		@Override
		public void run() {

		}
	}

}
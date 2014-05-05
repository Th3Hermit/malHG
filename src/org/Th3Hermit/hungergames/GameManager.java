package org.Th3Hermit.hungergames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.Th3Hermit.hungergames.Game.GameMode;
import org.Th3Hermit.hungergames.MessageManager.PrefixType;
import org.Th3Hermit.hungergames.stats.StatsManager;
import org.Th3Hermit.hungergames.util.Kit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class GameManager {

	static GameManager instance = new GameManager();
	private final ArrayList<Game> games = new ArrayList<Game>();
	private HungerGames p;
	public static HashMap<Integer, HashSet<Block>> openedChest = new HashMap<Integer, HashSet<Block>>();
	private final ArrayList<Kit> kits = new ArrayList<Kit>();
	private final HashSet<Player> kitsel = new HashSet<Player>();
	MessageManager msgmgr = MessageManager.getInstance();

	private GameManager() {

	}

	public static GameManager getInstance() {
		return instance;
	}

	public void setup(final HungerGames plugin) {
		p = plugin;
		LoadGames();
		LoadKits();
		for (final Game g : getGames()) {
			openedChest.put(g.getID(), new HashSet<Block>());
		}
	}

	public Plugin getPlugin() {
		return p;
	}

	public void reloadGames() {
		LoadGames();
	}

	public void LoadKits() {
		final Set<String> kits1 = SettingsManager.getInstance().getKits()
				.getConfigurationSection("kits").getKeys(false);
		for (final String s : kits1) {
			kits.add(new Kit(s));
		}
	}

	public void LoadGames() {
		final FileConfiguration c = SettingsManager.getInstance()
				.getSystemConfig();
		games.clear();
		final int no = c.getInt("hg-system.arenano", 0);
		int loaded = 0;
		int a = 1;
		while (loaded < no) {
			if (c.isSet("hg-system.arenas." + a + ".x1")) {
				// c.set("sg-system.arenas."+a+".enabled",c.getBoolean("sg-system.arena."+a+".enabled",
				// true));
				if (c.getBoolean("hg-system.arenas." + a + ".enabled")) {
					// SurvivalGames.$(c.getString("sg-system.arenas."+a+".enabled"));
					// c.set("sg-system.arenas."+a+".vip",c.getBoolean("sg-system.arenas."+a+".vip",
					// false));
					HungerGames.$("Loading Arena: " + a);
					loaded++;
					games.add(new Game(a));
					StatsManager.getInstance().addArena(a);
				}
			}
			a++;

		}
		LobbyManager.getInstance().clearAllSigns();

	}

	public int getBlockGameId(final Location v) {
		for (final Game g : games) {
			if (g.isBlockInArena(v)) {
				return g.getID();
			}
		}
		return -1;
	}

	public int getPlayerGameId(final Player p) {
		for (final Game g : games) {
			if (g.isPlayerActive(p)) {
				return g.getID();
			}
		}
		return -1;
	}

	public int getPlayerSpectateId(final Player p) {
		for (final Game g : games) {
			if (g.isSpectator(p)) {
				return g.getID();
			}
		}
		return -1;
	}

	public boolean isPlayerActive(final Player player) {
		for (final Game g : games) {
			if (g.isPlayerActive(player)) {
				return true;
			}
		}
		return false;
	}

	public boolean isPlayerInactive(final Player player) {
		for (final Game g : games) {
			if (g.isPlayerActive(player)) {
				return true;
			}
		}
		return false;
	}

	public boolean isSpectator(final Player player) {
		for (final Game g : games) {
			if (g.isSpectator(player)) {
				return true;
			}
		}
		return false;
	}

	public void removeFromOtherQueues(final Player p, final int id) {
		for (final Game g : getGames()) {
			if (g.isInQueue(p) && g.getID() != id) {
				g.removeFromQueue(p);
				msgmgr.sendMessage(PrefixType.INFO,
						"Removed from the queue in arena " + g.getID(), p);
			}
		}
	}

	public boolean isInKitMenu(final Player p) {
		return kitsel.contains(p);
	}

	public void leaveKitMenu(final Player p) {
		kitsel.remove(p);
	}

	public void openKitMenu(final Player p) {
		kitsel.add(p);
	}

	@SuppressWarnings("deprecation")
	public void selectKit(final Player p, final int i) {
		p.getInventory().clear();
		final ArrayList<Kit> kits = getKits(p);
		if (i <= kits.size()) {
			final Kit k = getKits(p).get(i);
			if (k != null) {
				p.getInventory().setContents(
						k.getContents().toArray(new ItemStack[0]));
			}
		}
		p.updateInventory();

	}

	public int getGameCount() {
		return games.size();
	}

	public Game getGame(final int a) {
		// int t = gamemap.get(a);
		for (final Game g : games) {
			if (g.getID() == a) {
				return g;
			}
		}
		return null;
	}

	public void removePlayer(final Player p, final boolean b) {
		getGame(getPlayerGameId(p)).removePlayer(p, b);
	}

	public void removeSpectator(final Player p) {
		getGame(getPlayerSpectateId(p)).removeSpectator(p);
	}

	public void disableGame(final int id) {
		getGame(id).disable();
	}

	public void enableGame(final int id) {
		getGame(id).enable();
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public GameMode getGameMode(final int a) {
		for (final Game g : games) {
			if (g.getID() == a) {
				return g.getMode();
			}
		}
		return null;
	}

	public ArrayList<Kit> getKits(final Player p) {
		final ArrayList<Kit> k = new ArrayList<Kit>();
		for (final Kit kit : kits) {
			if (kit.canUse(p)) {
				k.add(kit);
			}
		}
		return k;
	}

	// TODO: Actually make this count down correctly
	public void startGame(final int a) {
		getGame(a).countdown(10);
	}

	public void addPlayer(final Player p, final int g) {
		final Game game = getGame(g);
		if (game == null) {
			MessageManager.getInstance().sendFMessage(PrefixType.ERROR,
					"error.input", p, "message-No game by this ID exist!");
			return;
		}
		getGame(g).addPlayer(p);
	}

	public void autoAddPlayer(final Player pl) {
		final ArrayList<Game> qg = new ArrayList<Game>(5);
		for (final Game g : games) {
			if (g.getMode() == Game.GameMode.WAITING)
				qg.add(g);
		}
		// TODO: fancy auto balance algorithm
		if (qg.size() == 0) {
			pl.sendMessage(ChatColor.RED + "No games to join");
			msgmgr.sendMessage(PrefixType.WARNING, "No games to join!", pl);
			return;
		}
		qg.get(0).addPlayer(pl);
	}

	public WorldEditPlugin getWorldEdit() {
		return p.getWorldEdit();
	}

	public void createArenaFromSelection(final Player pl) {
		final FileConfiguration c = SettingsManager.getInstance()
				.getSystemConfig();
		// SettingsManager s = SettingsManager.getInstance();

		final WorldEditPlugin we = p.getWorldEdit();
		final Selection sel = we.getSelection(pl);
		if (sel == null) {
			msgmgr.sendMessage(PrefixType.WARNING,
					"You must make a WorldEdit Selection first!", pl);
			return;
		}
		final Location max = sel.getMaximumPoint();
		final Location min = sel.getMinimumPoint();

		/*
		 * if(max.getWorld()!=SettingsManager.getGameWorld() ||
		 * min.getWorld()!=SettingsManager.getGameWorld()){
		 * pl.sendMessage(ChatColor.RED+"Wrong World!"); return; }
		 */

		int no = c.getInt("hg-system.arenano") + 1;
		c.set("hg-system.arenano", no);
		if (games.size() == 0) {
			no = 1;
		} else
			no = games.get(games.size() - 1).getID() + 1;
		SettingsManager.getInstance().getSpawns().set(("spawns." + no), null);
		c.set("hg-system.arenas." + no + ".world", max.getWorld().getName());
		c.set("hg-system.arenas." + no + ".x1", max.getBlockX());
		c.set("hg-system.arenas." + no + ".y1", max.getBlockY());
		c.set("hg-system.arenas." + no + ".z1", max.getBlockZ());
		c.set("hg-system.arenas." + no + ".x2", min.getBlockX());
		c.set("hg-system.arenas." + no + ".y2", min.getBlockY());
		c.set("hg-system.arenas." + no + ".z2", min.getBlockZ());
		c.set("hg-system.arenas." + no + ".enabled", true);

		SettingsManager.getInstance().saveSystemConfig();
		hotAddArena(no);
		pl.sendMessage(ChatColor.GREEN + "Arena ID " + no
				+ " Succesfully added");

	}

	private void hotAddArena(final int no) {
		final Game game = new Game(no);
		games.add(game);
		StatsManager.getInstance().addArena(no);
		// SurvivalGames.$("game added "+
		// games.size()+" "+SettingsManager.getInstance().getSystemConfig().getInt("gs-system.arenano"));
	}

	public void hotRemoveArena(final int no) {
		for (final Game g : games.toArray(new Game[0])) {
			if (g.getID() == no) {
				games.remove(getGame(no));
			}
		}
	}

	public void gameEndCallBack(final int id) {
		getGame(id).setRBStatus("clearing chest");
		openedChest.put(id, new HashSet<Block>());
	}

	public String getStringList(final int gid) {
		final Game g = getGame(gid);
		final StringBuilder sb = new StringBuilder();
		final Player[][] players = g.getPlayers();

		sb.append(ChatColor.GREEN + "<---------------------[ Alive: "
				+ players[0].length + " ]--------------------->\n"
				+ ChatColor.GREEN + " ");
		for (final Player p : players[0]) {
			sb.append(p.getName() + ",");
		}
		sb.append("\n\n");
		sb.append(ChatColor.RED + "<---------------------[ Dead: "
				+ players[1].length + " ]---------------------->\n"
				+ ChatColor.GREEN + " ");
		for (final Player p : players[1]) {
			sb.append(p.getName() + ",");
		}
		sb.append("\n\n");

		return sb.toString();
	}

}
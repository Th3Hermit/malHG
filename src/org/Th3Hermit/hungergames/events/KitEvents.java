package org.Th3Hermit.hungergames.events;

import org.Th3Hermit.hungergames.api.PlayerJoinArenaEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class KitEvents implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void onArenaJoin(final PlayerJoinArenaEvent e) {
		e.getPlayer().getInventory().clear();
	}

}

package org.Th3Hermit.hungergames.events;

import org.Th3Hermit.hungergames.GameManager;
import org.Th3Hermit.hungergames.api.PlayerJoinArenaEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class KitEvents implements Listener  {

	@EventHandler(priority=EventPriority.LOWEST)
	public void onArenaJoin(PlayerJoinArenaEvent e){
		e.getPlayer().getInventory().clear();
	}

}

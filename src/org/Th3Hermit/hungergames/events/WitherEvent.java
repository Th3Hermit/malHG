package org.Th3Hermit.hungergames.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WitherEvent implements Listener{
	@EventHandler
	public void onWitherEvent(final EntityDamageByEntityEvent w) {
	if(w.getEntity() instanceof Player && w.getDamager() instanceof Player){
	Player damager = (Player)w.getDamager(); //now we cast the damager
	Player damaged = (Player)w.getEntity(); //now we cast the damaged player
	if(damager.getItemInHand().getItemMeta().getLore().contains(ChatColor.stripColor("Wither I")));
	damaged.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 0)); 
	}       
	}
}

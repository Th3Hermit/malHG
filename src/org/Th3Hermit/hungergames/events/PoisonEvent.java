package org.Th3Hermit.hungergames.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PoisonEvent implements Listener {
	@EventHandler
	public void onPoisonEvent(final EntityDamageByEntityEvent p) {
	if(p.getEntity() instanceof Player && p.getDamager() instanceof Player){
	Player damager = (Player)p.getDamager(); //now we cast the damager
	Player damaged = (Player)p.getEntity(); //now we cast the damaged player
	if(damager.getItemInHand().getItemMeta().getLore().contains(ChatColor.stripColor("Poison I")));
	damaged.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0)); 
	}       
	}

}

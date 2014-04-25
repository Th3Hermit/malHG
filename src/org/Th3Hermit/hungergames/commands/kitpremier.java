package org.Th3Hermit.hungergames.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class kitpremier {
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(commandLabel.equalsIgnoreCase("premier")){
				player.getInventory().clear();
				player.getInventory().addItem(new ItemStack(Material.BOW));
				player.getInventory().addItem(new ItemStack(Material.COOKED_CHICKEN, 2));
				player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 3));
				player.getInventory().addItem(new ItemStack(Material.APPLE, 5));
				player.getInventory().addItem(new ItemStack(Material.ARROW, 64));
			}
		}
		return false;
	}
}	
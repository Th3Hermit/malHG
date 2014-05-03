package org.Th3Hermit.hungergames.util;

import java.util.ArrayList;
import java.util.List;

import org.Th3Hermit.hungergames.SettingsManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Kit {

	private String name;
	private ArrayList<ItemStack>items = new ArrayList<ItemStack>();
	private ItemStack icon;

	
	public Kit(String name){
		this.name = name;
		load();
	}
	
	
	public void load(){
		FileConfiguration c = SettingsManager.getInstance().getKits();
		List<String>cont = c.getStringList("kits."+name+".contents");
		for(String s:cont){
			items.add(ItemReader.read(s));
		}
		
	}
	
	public ArrayList<ItemStack> getContents(){
		return items;
	}
	
	
	public boolean canUse(Player p){
		return p.hasPermission("hg.kit."+name);
	}


	public String getName() {
		return name;
	}
	
	public ItemStack getIcon(){
		return icon;
	}

}

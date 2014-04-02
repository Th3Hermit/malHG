package org.Th3Hermit.hungergames.util;

import java.util.Arrays;
import java.util.HashMap;

import org.Th3Hermit.hungergames.HungerGames;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemReader {

	
	private static HashMap<String, Enchantment>encids;
	

	
	private static void loadIds(){
		
		encids =  new HashMap<String, Enchantment>();
		
		for(Enchantment e:Enchantment.values()){
			encids.put(e.toString().toLowerCase().replace("_", ""), e);
		}
		
		encids.put("punch", Enchantment.ARROW_KNOCKBACK);
		encids.put("power", Enchantment.ARROW_DAMAGE);
		encids.put("flame", Enchantment.ARROW_FIRE);
		encids.put("infinite", Enchantment.ARROW_INFINITE);
		encids.put("sharpness", Enchantment.DAMAGE_ALL);
		encids.put("dmg", Enchantment.DAMAGE_ALL);
		encids.put("fire", Enchantment.FIRE_ASPECT);
		encids.put("knockback", Enchantment.KNOCKBACK);
		encids.put("protect", Enchantment.PROTECTION_ENVIRONMENTAL);
		encids.put("fireprotect", Enchantment.PROTECTION_FIRE);
		encids.put("blastprotect", Enchantment.PROTECTION_EXPLOSIONS);
		encids.put("projectileprotect", Enchantment.PROTECTION_PROJECTILE);
		encids.put("thorns", Enchantment.THORNS);
		encids.put("respiration", Enchantment.WATER_WORKER);
		encids.put("featherfalling", Enchantment.PROTECTION_FALL);
		encids.put("aquaaffinity", Enchantment.OXYGEN);

	}
	
	
	
	public static ItemStack read(String str){
		if(encids == null){
			loadIds();
		}
		String split[] = str.split(",");
		HungerGames.debug("ItemReader: reading : "+Arrays.toString(split));
		for(int a = 0; a < split.length; a++){
			split[a] = split[a].toLowerCase().trim();
		}
		if(split.length < 1){
			return null;
		}else if(split.length == 1){
			return new ItemStack(Integer.parseInt(split[0]));  //Material.enum has replaced ItemStack(int) ex. Material.STONE_SWORD
		}else if(split.length == 2){
			return new ItemStack(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
		}else if(split.length == 3){
			return new ItemStack(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Short.parseShort(split[2]));
		}else{
			ItemStack i =  new ItemStack(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Short.parseShort(split[2]));
			String encs[] = split[3].split(" ");
			for(String enc: encs){
				System.out.println(enc);
				String e[] = enc.split(":");
				i.addUnsafeEnchantment(encids.get(e[0]), Integer.parseInt(e[1]));
			}
			if(split.length == 5){
				ItemMeta im = i.getItemMeta();
				im.setDisplayName(MessageUtil.replaceColors(split[4]));
				i.setItemMeta(im);
			}
			return i;
		}
	}
	
	public static String getFriendlyItemName(Material m){
		String str = m.toString();
		str = str.replace('_',' ');
		str = str.substring(0, 1).toUpperCase() +
				str.substring(1).toLowerCase();
		return str;
	}
	
	
}

package org.Th3Hermit.hungergames.commands;

import java.util.ArrayList;
import java.util.List;

import org.Th3Hermit.hungergames.GameManager;
import org.Th3Hermit.hungergames.MessageManager;
import org.Th3Hermit.hungergames.SettingsManager;
import org.Th3Hermit.hungergames.MessageManager.PrefixType;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class KitOp implements SubCommand{
	public boolean onCommand(Player player, String[] args){
			if(!player.hasPermission(permission()) && !player.isOp()){
				MessageManager.getInstance().sendFMessage(PrefixType.ERROR, "error.nopermission", player);
				return false;
			}
			PlayerInventory pi = player.getInventory();
	        int game = GameManager.getInstance().getPlayerGameId(player);
	        if(game == -1){
	            MessageManager.getInstance().sendMessage(MessageManager.PrefixType.ERROR, "error.notinarena", player);
	        }else{

				pi.clear();
				ItemStack OpBow = new ItemStack(Material.BOW, 1);
				OpBow.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
				OpBow.addEnchantment(Enchantment.ARROW_FIRE, 1);
				OpBow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
				OpBow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
				ItemMeta OpBowmeta = OpBow.getItemMeta();
				OpBowmeta.setDisplayName("Whicked's Justice");
				List<String> bowlore = new ArrayList<String>();
				bowlore.add(ChatColor.RED + "Whicked's mighty bow,");
				bowlore.add(ChatColor.RED + "used to slay 1000 trolls");
				OpBowmeta.setLore(bowlore);
				OpBow.setItemMeta(OpBowmeta);
				ItemStack OpSword = new ItemStack(Material.IRON_SWORD, 1);
				OpSword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
				OpSword.addEnchantment(Enchantment.KNOCKBACK , 2);
				ItemMeta OpSwordmeta = OpSword.getItemMeta();
				OpSwordmeta.setDisplayName("WidowMaker");
				List<String> swordlore = new ArrayList<String>();
				swordlore.add(ChatColor.GRAY + "Poison I");
				swordlore.add(ChatColor.RED + "A mighty sword");
				swordlore.add(ChatColor.RED + "can smite the");
				swordlore.add(ChatColor.RED + "strongest of foes");
				OpSwordmeta.setLore(swordlore);
				OpSword.setItemMeta(OpSwordmeta);	
				
				ItemStack OpSword2 = new ItemStack(Material.IRON_SWORD, 1);
				OpSword2.addEnchantment(Enchantment.DAMAGE_ALL, 5);
				OpSword2.addEnchantment(Enchantment.KNOCKBACK , 2);
				ItemMeta OpSword2meta = OpSword.getItemMeta();
				OpSword2meta.setDisplayName("Wither");
				List<String> sword2lore = new ArrayList<String>();
				sword2lore.add(ChatColor.GRAY + "Wither I");
				OpSword2meta.setLore(sword2lore);
				OpSword2.setItemMeta(OpSword2meta);
				
				Potion nightvision = new Potion(PotionType.NIGHT_VISION, 1);
				Potion swiftness2 = new Potion(PotionType.SPEED, 2);
				Potion regen2 = new Potion(PotionType.REGEN, 2);
				Potion slowness = new Potion(PotionType.SLOWNESS, 1);
				Potion weakness = new Potion(PotionType.WEAKNESS, 1);
				ItemStack OpHelmet = new ItemStack(Material.LEATHER_HELMET, 1);
				OpHelmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,4);
				OpHelmet.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
				OpHelmet.addEnchantment(Enchantment.WATER_WORKER, 1);
				OpHelmet.addEnchantment(Enchantment.OXYGEN, 3);
				OpHelmet.addEnchantment(Enchantment.THORNS, 3);
				OpHelmet.addEnchantment(Enchantment.DURABILITY, 3);
				LeatherArmorMeta OpHelmetmeta = (LeatherArmorMeta) OpHelmet.getItemMeta();
				OpHelmetmeta.setDisplayName("Force User's Helm");
				List<String> Helmetlore = new ArrayList<String>();
				bowlore.add(ChatColor.RED + "A long time ago in a");
				bowlore.add(ChatColor.RED + "galaxy far far away...");
				OpHelmetmeta.setLore(Helmetlore);
				OpHelmetmeta.setColor(Color.BLACK);
				OpHelmet.setItemMeta(OpHelmetmeta);
				ItemStack OpChest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
				OpChest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
				OpChest.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
				OpChest.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
				OpChest.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
				OpChest.addEnchantment(Enchantment.THORNS, 3);
				OpChest.addEnchantment(Enchantment.DURABILITY, 3);
				LeatherArmorMeta OpChestmeta = (LeatherArmorMeta) OpChest.getItemMeta();
				OpChestmeta.setDisplayName("Force User's Robes");
				List<String> chestlore = new ArrayList<String>();
				chestlore.add(ChatColor.RED + "A long time ago in a");
				chestlore.add(ChatColor.RED + "galaxy far far away...");
				OpChestmeta.setLore(chestlore);
				OpChestmeta.setColor(Color.BLACK);
				OpChest.setItemMeta(OpChestmeta);
				ItemStack OpPants = new ItemStack(Material.LEATHER_LEGGINGS, 1);
				OpPants.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
				OpPants.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
				OpPants.addEnchantment(Enchantment.THORNS, 3);
				OpPants.addEnchantment(Enchantment.DURABILITY, 3);
				LeatherArmorMeta OpPantsmeta = (LeatherArmorMeta) OpPants.getItemMeta();
				OpPantsmeta.setDisplayName("Force User's Pants");
				List<String> pantslore = new ArrayList<String>();
				pantslore.add(ChatColor.RED + "A long time ago in a");
				pantslore.add(ChatColor.RED + "galaxy far far away...");
				OpPantsmeta.setLore(pantslore);
				OpPantsmeta.setColor(Color.BLACK);
				OpPants.setItemMeta(OpPantsmeta);
				ItemStack OpBoots = new ItemStack(Material.LEATHER_BOOTS, 1);
				OpBoots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
				OpBoots.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
				OpBoots.addEnchantment(Enchantment.PROTECTION_FALL, 4);
				OpBoots.addEnchantment(Enchantment.THORNS, 3);
				OpBoots.addEnchantment(Enchantment.DURABILITY, 3);
				LeatherArmorMeta OpBootsmeta = (LeatherArmorMeta) OpBoots.getItemMeta();
				OpHelmetmeta.setDisplayName("Force User's Boots");
				List<String> bootslore = new ArrayList<String>();
				bootslore.add(ChatColor.RED + "A long time ago in a");
				bootslore.add(ChatColor.RED + "galaxy far far away...");
				OpBootsmeta.setLore(bootslore);
				OpBootsmeta.setColor(Color.BLACK);
				OpBoots.setItemMeta(OpBootsmeta);
				pi.addItem(OpBow);
				pi.addItem(OpSword);
				pi.addItem(OpSword2);
				pi.setHelmet(OpHelmet);
				pi.setChestplate(OpChest);
				pi.setLeggings(OpPants);
				pi.setBoots(OpBoots);
				pi.addItem(swiftness2.toItemStack(5));
				pi.addItem(regen2.toItemStack(5));
				pi.addItem(slowness.splash().toItemStack(5));
				pi.addItem(weakness.splash().toItemStack(5));
				pi.addItem(new ItemStack(Material.ENDER_PEARL, 5));
				pi.addItem(new ItemStack(Material.GOLDEN_APPLE, 5, (short) 1));
				pi.addItem(new ItemStack(Material.GOLDEN_CARROT, 10));
				pi.addItem(new ItemStack(Material.ARROW, 1));
				pi.addItem(nightvision.toItemStack(3));
	        }
			return true;
			}
	/*Doesn't Work because this class doesn't implement Listener		
	@EventHandler
	public void onDamageEvent(final EntityDamageByEntityEvent e) {
	if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
	Player damager = (Player)e.getDamager(); //now we cast the damager
	Player damaged = (Player)e.getEntity(); //now we cast the damaged player
	if(damager.getItemInHand().getItemMeta().getLore().contains(ChatColor.stripColor("Poison I")));
	damaged.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1)); 
	}       
	}
*/

    public String help(Player p) {
        return "/hg kitop - " + SettingsManager.getInstance().getMessageConfig().getString("messages.help.kitop", "Gives a kit to an Operator");
    }
	
	public String permission() {
		return "hg.kitop";
	}

}	
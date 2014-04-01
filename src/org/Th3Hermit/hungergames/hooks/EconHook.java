package org.Th3Hermit.survivalgames.hooks;

import net.milkbowl.vault.economy.Economy;

import org.Th3Hermit.survivalgames.util.EconomyManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * 
 * 
 * something wrote by pogo.
 *
 */
public class EconHook implements HookBase {

	@Override
	public void executehook(String player, String[] s2) {
		if(EconomyManager.getInstance().econPresent()){
			Economy econ = EconomyManager.getInstance().getEcon();
			String split[] = s2[1].split(" ");
			if(split.length == 3){
				Player p = Bukkit.getServer().getPlayer(split[1]);
				int funds = Integer.parseInt(split[2]);
				if(split[0].equals("remove")){
					econ.bankWithdraw(p.getName(), funds);
				}
			}
		}
	}

}

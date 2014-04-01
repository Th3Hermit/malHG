package org.Th3Hermit.survivalgames.util;

import org.Th3Hermit.survivalgames.SurvivalGames;
import org.bukkit.ChatColor;



public class NameUtil {

	
	public static String stylize(String name, boolean s, boolean r){

		if(SurvivalGames.auth.contains(name) && r){
			name = ChatColor.DARK_RED+name;
		}
		if(SurvivalGames.auth.contains(name) && !r){
			name = ChatColor.DARK_BLUE+name;
		}
		if(s && name.equalsIgnoreCase("Double0negative")){
			name = name.replace("Double0negative", "Double0");
		}
		return name;
	}
}

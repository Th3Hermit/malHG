package org.Th3Hermit.hungergames.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;

import org.Th3Hermit.hungergames.HungerGames;

public class MessageUtil {

	private static HashMap<String, String>varcache = new HashMap<String, String>();


	public static String replaceColors(String s){
		if(s == null){
			return null;
		}
		return s.replaceAll("(&([a-fk-or0-9]))", "\u00A7$2");
	}

	public static String replaceVars(String msg, HashMap<String, String>vars){
		boolean error = false;
		for(String s:vars.keySet()){
			try{
				msg.replace("{$"+s+"}", vars.get(s));
			}catch(Exception e){
				HungerGames.$(Level.WARNING, "Failed to replace string vars. Error on "+s);
				error = true;
			}
		}
		if(error){
			HungerGames.$(Level.SEVERE, "Error replacing vars in message: "+msg);
			HungerGames.$(Level.SEVERE, "Vars: "+vars.toString());
			HungerGames.$(Level.SEVERE, "Vars Cache: "+varcache.toString());
		}
		return msg;
	}

	public static String replaceVars(String msg, String[] vars){
		for(String str: vars){
			String[] s = str.split("-");
			varcache.put(s[0], s[1]);
		}
		boolean error = false;
		for(String str: varcache.keySet()){
			try{
				msg = msg.replace("{$"+str+"}", varcache.get(str));
			}catch(Exception e){
				HungerGames.$(Level.WARNING,"Failed to replace string vars. Error on "+str);
				error = true;
			}
		}
		if(error){
			HungerGames.$(Level.SEVERE, "Error replacing vars in message: "+msg);
			HungerGames.$(Level.SEVERE, "Vars: "+Arrays.toString(vars));
			HungerGames.$(Level.SEVERE, "Vars Cache: "+varcache.toString());
		}

		return msg;
	}
}
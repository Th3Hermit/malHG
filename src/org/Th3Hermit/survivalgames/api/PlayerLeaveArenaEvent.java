package org.Th3Hermit.survivalgames.api;

import org.Th3Hermit.survivalgames.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;



public class PlayerLeaveArenaEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Game game;
    private boolean logout;
    
    public PlayerLeaveArenaEvent(Player p, Game g, boolean logout) {
        player = p;
        game = g;
    }

    public Player getPlayer() {
        return player;
    }
    
    public Game getGame() {
    	return game;
    }
 
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public boolean isLogout(){
    	return logout;
    }
 
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
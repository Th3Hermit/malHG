package org.Th3Hermit.hungergames.events;


import java.util.ArrayList;

import org.Th3Hermit.hungergames.Game;
import org.Th3Hermit.hungergames.GameManager;
import org.Th3Hermit.hungergames.SettingsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;



public class PlaceEvent implements Listener {

    public  ArrayList<Integer> allowedPlace = new ArrayList<Integer>();

    public PlaceEvent(){
        allowedPlace.addAll( SettingsManager.getInstance().getConfig().getIntegerList("block.place.whitelist"));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        Player p = event.getPlayer();
        int id  = GameManager.getInstance().getPlayerGameId(p);

        if(id == -1){
            int gameblockid = GameManager.getInstance().getBlockGameId(event.getBlock().getLocation());
            if(gameblockid != -1){
                if(GameManager.getInstance().getGame(gameblockid).getGameMode() != Game.GameMode.DISABLED){
                    event.setCancelled(true);
                }
            }
            return;
        }


        Game g = GameManager.getInstance().getGame(id);
        if(g.isPlayerinactive(p)){
            return;
        }
        if(g.getMode() == Game.GameMode.DISABLED){
            return;
        }
        if(g.getMode() != Game.GameMode.INGAME){
            event.setCancelled(true);
            return;

        }

        if(!allowedPlace.contains(event.getBlock().getTypeId())){
            event.setCancelled(true);
        }

    }
}
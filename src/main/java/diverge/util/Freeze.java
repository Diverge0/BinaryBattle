package main.java.diverge.util;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.filter.cause.First;

public class Freeze {
    public static boolean stopPlayer = false;

    @Listener
    public void onPlayerMove(MoveEntityEvent event, @First Player player){
        if(stopPlayer){
            event.setCancelled(true);
        }
    }
}

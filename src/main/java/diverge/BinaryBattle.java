package main.java.diverge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import com.google.inject.Inject;
import org.slf4j.Logger;
@Plugin(
        id = "binarybattle",
        name = "BinaryBattle"
)



public class BinaryBattle {
    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event){
        logger.info("BinaryBattle Plugin loaded successfully");
    }
}

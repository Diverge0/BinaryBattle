package main.java.diverge;
import main.java.diverge.commands.PingPongCommand;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;

@Plugin(
        id = "binarybattle",
        name = "BinaryBattle"
)

public class BinaryBattle {
    @Inject
    private Logger logger;

    //Needed to register commands
    @Inject
    private PluginContainer plugin;

    //Public method to log from anywhere
    public void log(String logtext){
        logger.info(logtext);
    }

    @Listener
    public void initializePlugin(GameInitializationEvent event){
        //Simple Ping Pong command (see commands.PingPongCommand)
        CommandSpec PingPong = CommandSpec.builder()
                .description(Text.of("Ping Pong"))
                .executor(new PingPongCommand())
                .build();
        Sponge.getCommandManager().register(plugin ,PingPong, "ping");
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event){
        logger.info("BinaryBattle Plugin loaded successfully");


    }

}

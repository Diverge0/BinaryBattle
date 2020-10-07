package main.java.diverge;
import main.java.diverge.commands.CountdownCommand;
import main.java.diverge.commands.PingPongCommand;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
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
    public PluginContainer getPlugin(){
        return plugin;
    }

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

        //Countdown command (see commands.Countdown)
        CommandSpec Countdown = CommandSpec.builder()
                .description(Text.of("/countdown [int] summons a countdown in seconds"))
                .arguments(
                        GenericArguments.onlyOne(GenericArguments.integer(Text.of("time in seconds")))
                )
                .executor(new CountdownCommand())
                .build();
        Sponge.getCommandManager().register(plugin ,Countdown, "countdown");
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event){
        logger.info("BinaryBattle Plugin loaded successfully");


    }

}

package main.java.diverge;
import main.java.diverge.commands.BattleCommand;
import main.java.diverge.commands.CountdownCommand;
import main.java.diverge.commands.PingPongCommand;
import main.java.diverge.commands.FreezeCommand;
import org.spongepowered.api.Game;
import org.spongepowered.api.Server;
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
    //instance of Logger(better console output)
    @Inject
    private Logger logger;

    //Public method to log from anywhere
    public void log(String logtext){
        logger.info(logtext);
    }

    //Needed to register commands
    @Inject
    private PluginContainer plugin;
    public PluginContainer getPlugin(){
        return plugin;
    }

    //Instance of Game, used to interact with the API
    @Inject
    private Game game;

    //Instance of Server, used to interact with the API
    private Server server;

    public Server getServer() {
        return server;
    }

    private Battle battle;

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    @Listener
    public void initializePlugin(GameInitializationEvent event){
        server = game.getServer();
        //battle = new Battle(this, "idle");
        //Simple Ping Pong command (see commands.PingPongCommand)
        CommandSpec PingPong = CommandSpec.builder()
                .description(Text.of("Ping Pong"))
                .executor(new PingPongCommand())
                .build();
        Sponge.getCommandManager().register(plugin ,PingPong, "ping");

        //Simple Test command used for trying various API features, remove later!
        /*CommandSpec Test = CommandSpec.builder()
                .description(Text.of("Test Stuff"))
                .executor(new TestCommand(this))
                .build();
        Sponge.getCommandManager().register(plugin ,Test, "test");*/

        //Freezes all players
        CommandSpec Freeze = CommandSpec.builder()
                .description(Text.of("Freeze players"))
                .executor(new FreezeCommand(this))
                .build();
        Sponge.getCommandManager().register(plugin ,Freeze, "freeze");

        //Countdown command (see commands.Countdown)
        CommandSpec Countdown = CommandSpec.builder()
                .description(Text.of("/countdown [int] summons a countdown in seconds"))
                .arguments(
                        GenericArguments.onlyOne(GenericArguments.integer(Text.of("time in seconds")))
                )
                .executor(new CountdownCommand())
                .build();
        Sponge.getCommandManager().register(plugin ,Countdown, "countdown");

        //Battle command used to initialize and start the battle

        // /battle init
        CommandSpec initCmd = CommandSpec.builder()
                .description(Text.of("initializes Battle and freezes all players"))
                .executor(new BattleCommand(this,"init"))
                .build();

        CommandSpec startCmd = CommandSpec.builder()
                .description(Text.of("unfreezes players and starts battle"))
                .arguments(
                        GenericArguments.onlyOne(GenericArguments.integer(Text.of("time in seconds")))
                )
                .executor(new BattleCommand(this,"start"))
                .build();

        CommandSpec battleCmd = CommandSpec.builder()
                .description(Text.of("/battle <init / start> [int] initializes or starts the Battle"))
                .child(initCmd, "init", "initialize")
                .child(startCmd, "start")
                .build();
        Sponge.getCommandManager().register(plugin, battleCmd, "battle");


    }

    @Listener
    public void onServerStart(GameStartedServerEvent event){
        log("BinaryBattle Plugin loaded successfully");


    }

}

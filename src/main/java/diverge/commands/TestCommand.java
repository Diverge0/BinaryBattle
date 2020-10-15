package main.java.diverge.commands;

import main.java.diverge.BinaryBattle;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.filter.cause.First;

public class TestCommand implements CommandExecutor {
    //Instance of main class
    private final BinaryBattle plugin;
    private boolean stopPlayer;
    private Player player;

    public TestCommand(BinaryBattle plugin){
        this.plugin = plugin;
        stopPlayer = false;
        Sponge.getEventManager().registerListeners(plugin, this);
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        //Put Test code here
        if(src instanceof Player) {
            this.player = (Player) src;
            stopPlayer = !stopPlayer;
        }



        return CommandResult.success();
    }

    @Listener
    public void onPlayerMove(MoveEntityEvent event, @First Player player){
        if(stopPlayer){
            event.setCancelled(true);
        }
    }
}

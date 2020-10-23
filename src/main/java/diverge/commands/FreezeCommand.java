package main.java.diverge.commands;

import main.java.diverge.BinaryBattle;

import main.java.diverge.util.Freeze;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

public class FreezeCommand implements CommandExecutor {
    //Instance of main class
    private final BinaryBattle plugin;

    public FreezeCommand(BinaryBattle plugin){
        this.plugin = plugin;
        Sponge.getEventManager().registerListeners(plugin, new Freeze());
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Freeze.stopPlayer = !Freeze.stopPlayer;
        return CommandResult.success();
    }
}

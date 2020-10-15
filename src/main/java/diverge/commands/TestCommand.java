package main.java.diverge.commands;

import main.java.diverge.BinaryBattle;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class TestCommand implements CommandExecutor {
    //Instance of main class
    private final BinaryBattle plugin;

    public TestCommand(BinaryBattle plugin){
        this.plugin = plugin;
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        //Put Test code here
        if(src instanceof Player) {
            Player player = (Player) src;
            player.getInventory().clear();
        }



        return CommandResult.success();
    }
}

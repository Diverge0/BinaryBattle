package main.java.diverge.commands;

import main.java.diverge.util.Countdown;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.channel.MessageChannel;


//Countdown command
public class CountdownCommand implements CommandExecutor {
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Countdown c = new Countdown();
        c.seconds = args.<Integer>getOne("time in seconds").get().intValue();
        c.channel = MessageChannel.TO_PLAYERS;
        new Thread(c).start();//new Thread is needed. Server stops completely when waiting in main thread
        return CommandResult.success();
    }
}

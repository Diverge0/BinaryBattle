package main.java.diverge.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.format.TextColors;
import java.util.concurrent.TimeUnit;

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

    //Private class running in thread
    private class Countdown implements Runnable{
        int seconds;
        MessageChannel channel;

        @Override
        public void run(){
            for (int i = seconds;i>=0; i--){
                channel.send(Text.builder(Integer.toString(i)).color(TextColors.GOLD).build());
                try{TimeUnit.SECONDS.sleep(1);}
                catch(Exception e){}//useless catch block
            }
        }
    }
}

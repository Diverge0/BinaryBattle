package main.java.diverge.commands;

import main.java.diverge.BinaryBattle;
import main.java.diverge.util.Countdown;
import main.java.diverge.util.Freeze;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.format.TextColors;

public class BattleCommand implements CommandExecutor {
    private BinaryBattle plugin;
    private String operationMode;
    public BattleCommand(BinaryBattle plugin, String operationMode){
        this.operationMode = operationMode;
        Sponge.getEventManager().registerListeners(plugin, new Freeze());
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if(operationMode.equals("init")){
            Freeze.stopPlayer = true;
            //plugin.getBattle().setOperationMode("init");
        }
        else if(operationMode.equals("start")){
            Countdown c = new Countdown();
            c.seconds = args.<Integer>getOne("time in seconds").get().intValue();
            c.channel = MessageChannel.TO_PLAYERS;
            Thread t = new Thread(c);//new Thread is needed. Server stops completely when waiting in main thread
            t.start();
            try{
                t.join();
            }
            catch(InterruptedException e){
                plugin.log("InterruptedException");
            }
            Freeze.stopPlayer = false;
            MessageChannel.TO_ALL.send(Text.builder("Battle beginnt").color(TextColors.RED).build());

        }

        return CommandResult.success();
    }
}

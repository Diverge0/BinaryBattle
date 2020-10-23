package main.java.diverge.commands;

import main.java.diverge.BinaryBattle;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.scoreboard.Score;
import org.spongepowered.api.scoreboard.Scoreboard;
import org.spongepowered.api.scoreboard.critieria.Criteria;
import org.spongepowered.api.scoreboard.displayslot.DisplaySlots;
import org.spongepowered.api.scoreboard.objective.Objective;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.Optional;

import static org.spongepowered.api.scoreboard.displayslot.DisplaySlots.SIDEBAR;

public class TestCommand implements CommandExecutor {
    BinaryBattle plugin;
    //Optional<Scoreboard> scoreboard;
    public TestCommand(BinaryBattle plugin){
        this.plugin = plugin;
        //scoreboard = plugin.getServer().getServerScoreboard();
    }

    @Override
    /*public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if(scoreboard.isPresent()){
            plugin.log("Scoreboard present");
            Scoreboard scoreboard = this.scoreboard.get();
            Objective aTestObjective;
            aTestObjective = Objective.builder()
                    .name("test")
                    .displayName(Text.of("TestObjective"))
                    .build();
            scoreboard.addObjective(aTestObjective);
            scoreboard.updateDisplaySlot(aTestObjective, DisplaySlots.SIDEBAR);

        }
        else{
            plugin.log("Scoreboard is null");
        }


        return CommandResult.success();
    }*/
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Player player = (Player) src;
        Scoreboard scoreboard = Scoreboard.builder().build();

        //aTestObjective
        Objective aTestObjective;
        aTestObjective = Objective.builder()
                .name("test")
                .displayName(Text.of("TestObjective"))
                .criterion(Criteria.DUMMY)
                .build();
        scoreboard.addObjective(aTestObjective);
        scoreboard.updateDisplaySlot(aTestObjective, DisplaySlots.SIDEBAR);
        aTestObjective.getOrCreateScore(Text.of("Foo")).setScore(123);
        aTestObjective.getOrCreateScore(Text.of("Bar")).setScore(456);

        //anotherTestObjective
        Objective anotherTestObjective;
        anotherTestObjective = Objective.builder()
                .name("test2")
                .displayName(Text.of("AnotherTestObjective"))
                .criterion(Criteria.DUMMY)
                .build();
        scoreboard.addObjective(anotherTestObjective);
        scoreboard.updateDisplaySlot(anotherTestObjective, DisplaySlots.LIST);
        anotherTestObjective.getOrCreateScore(Text.builder("Baz").color(TextColors.DARK_PURPLE).build()).setScore(420);

        player.setScoreboard(scoreboard);

        return CommandResult.success();
    }
}

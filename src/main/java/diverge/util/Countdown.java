package main.java.diverge.util;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.format.TextColors;

import java.util.concurrent.TimeUnit;

public class Countdown implements Runnable{
    public int seconds;
    public MessageChannel channel;

    @Override
    public void run(){
        for (int i = seconds;i>=0; i--){
            channel.send(Text.builder(Integer.toString(i)).color(TextColors.GOLD).build());
            try{
                TimeUnit.SECONDS.sleep(1);}
            catch(Exception e){}//useless catch block
        }
    }
}

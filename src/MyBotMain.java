// Jithin Lucas
// API Project

import org.jibble.pircbot.*;

public class MyBotMain {

    public static void main (String [] args) throws Exception {

        // creates an object of the MyBot class
        MyBot bot = new MyBot();

        // sets bot verbose to true
        bot.setVerbose(true);

        // sets bot to connect to the specified address
        bot.connect("irc.freenode.net");

        // sets the channel for the bot to join
        bot.joinChannel("#pircbot");
    }
}

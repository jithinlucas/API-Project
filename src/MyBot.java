// Jithin Lucas
// API Project

import org.jibble.pircbot.*;

public class MyBot extends PircBot {

    public MyBot() {
        // sets the name of the bot
        this.setName("RatingsBot");
    }

    // this checks to see if a specific word is found in a message sent from a user in the channel
    public void onMessage(String channel, String sender, String login, String hostname, String message) {

        // this if statement runs if the word "weather" is found
        if (message.contains("weather")) {

            // creates a weather object of the weather class
            Weather weatherTemp = new Weather();

            // splits the message sent using a space delimeter and stores into an array
            String[] messageSplit = message.split(" ");

            // retrieves the last two indexes of the array in order to retrieve the city and state
            String city = messageSplit[messageSplit.length - 2];
            String state = messageSplit[messageSplit.length - 1];

            // outputs the temperature using sendMessage and calling the weatherAPI method in the weather class
            sendMessage(channel, sender + weatherTemp.weatherAPI(city, state));
        }
        // this if statement runs if the word "ratings" is found
        else if (message.contains("ratings"))
        {
            // creates an object named movieRating of the movie class
            Movie movieRating = new Movie();

            // splits the message using a space delimeter and stores into a string array
            String[] messageSplit = message.split(" ");

            // creates a temporary result string to store a temporary result
            String temporaryResult = "";
            int i = 1;

            // stores the name of the movie into temporary result (at this point, any spaces between the movie
            // title will be stored as pluses
            while (i < messageSplit.length)
            {
                temporaryResult += messageSplit[i]+ "+";
                i += 1;
            }

            // creates a result string to store the final result
            String result = "";

            // runs a for loop to store the movie title into
            for (int j = 0; j < temporaryResult.length() - 1; j++)
            {
                result += temporaryResult.charAt(j);
            }

            // outputs the ratings of the movie title that the user inputted
            sendMessage(channel, sender + movieRating.movieAPI(result));
        }

    }

}

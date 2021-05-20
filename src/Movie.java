// Jithin Lucas
// API Project

import java.util.*;
import java.net.URL;
import com.google.gson.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;


public class Movie {

    public static String movieAPI(String movieTitle)
    {
        try
        {
            // creates a url String that takes in the movieTitle string from the parameter to finish the url
            String url= "http://www.omdbapi.com/?t=" + movieTitle + "&apikey=[API Key]";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // creates a responseCode and BufferedReader
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            // creates an inputLine String and a response StringBuffer
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }
            in.close();

            //
            String movieString1 = "";

            // prints out a hashmap that prints out the keys and its values
            String yourJson = response.toString();
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(yourJson);
            JsonObject objt = element.getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entries = objt.entrySet();

            // checks the datatype to see if it is an array, if successful, it moves into the second if statement and if it matches "Ratings", then
            // it is appended to a string
            for (Map.Entry<String, JsonElement> entry: entries) {
                if("class com.google.gson.JsonArray".equals(entry.getValue().getClass().toString()))
                {
                    if(entry.getKey().toString().equals("Ratings"))
                    {
                        movieString1 += entry.getValue();
                        break;
                    }
                }
            }

            // movieString1 is now being formatted in order to retrieve the specific info from the API call
            // Between lines 59 - 107, movieString1 is being adjusted and placed into multiple different
            // strings and string arrays in order to ultimately get the ratings for the specified movie title
            ArrayList<String> movieArray1 = new ArrayList <String>();

            // replaces any brackets, curly braces, slashes, with a space in movieString1 and stores in movieString2
            String movieString2 = movieString1.replace("}","").replace("{","").replace("[","").replace("]","").replace("\"", "");

            // splits movieString2 using a comma delimeter and stores into movieArray2
            String[] movieArray2 = movieString2.split(",");
            String movieString3 = "";

            // runs a for loop to store all contents of each index in movieArray2 with a colon between each index
            // into movieString3
            for(int x = 0; x < movieArray2.length; x++)
            {
                if(x % 2 != 0)
                {
                    movieString3 += movieArray2[x] + ":";
                }
            }

            // splits movieString3 using a colon delimeter and stores into movieArray3
            String[] movieArray3 = movieString3.split(":");

            // sets movieArray4 to the following; these are the different rating sources being used within the API
            String[] movieArray4 = {"Internet Movie Database:","Rotten Tomatoes:","Metacritic:"};

            // for loop to append the element at movieArray3[z] to the end of movieArray1
            for(int z = 0; z < movieArray3.length; z++)
            {
                if(z % 2 != 0)
                {
                    movieArray1.add(movieArray3[z]);
                }
            }
            String movieString4 = "";

            // for loop to format the display of the movieString using commas and spaces to make it more readable
            for(int i = 0; i < movieArray4.length; i++)
            {
                movieString4 += movieArray4[i] + " " + movieArray1.get(i) + ", ";
            }
            String movieString5 = movieString4.trim();

            String movieString6 = "";

            // for loop to eliminate the extraneous comma at the end of the string and stores it in movieString6
            for (int i = 0; i < movieString5.length() - 1; i++)
            {
                movieString6 += movieString5.charAt(i);
            }

            // returns movieString6 with a space in front for a user friendly response
            return " " + movieString6;

        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        // returns this if the movie title is not found
        return " Movie title not found. Please enter a new movie title.";

    }
}

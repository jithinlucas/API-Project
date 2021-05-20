// Jithin Lucas
// API Project

import java.util.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.google.gson.*;


public class Weather {

    public static String weatherAPI (String cityIn, String stateIn) {

        try {

            // creates a url String that takes in the city and state strings from the parameters to finish the url
            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + cityIn + "," + stateIn + "&appid=[API Key]";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // creates a responseCode and BufferedReader
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            // creates an inputLine String and a response StringBuffer
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }
            in.close();

            // creates a new gson, uses json to parse the data and to get the data in main
            Gson gson = new Gson();
            JsonElement jelement = new JsonParser().parse(response.toString());
            JsonObject jobject = jelement.getAsJsonObject();
            jobject = jobject.getAsJsonObject("main");
            String data = gson.toJson(jobject);

            // creates a string to simplify the data string by removing any brackets or commas
            String simplifiedData = data.replace("{","").replace("}","").replace(","," ").replace(","," ");

            // splits the string using a space as a delimeter
            String[] spaceDelim = simplifiedData.split(" ");

            // splits the string using a colon as a delimeter
            String[] colonDelim = spaceDelim[0].split(":");

            // retrieves the kelvin temperature value and converts it to fahrenheight
            double kelvin = Double.parseDouble(colonDelim[1]);
            double fahrenheit = kelvin * 9/5 - 459.67;
            int roundedFahrenheit = (int)fahrenheit;

            String fahrenheitDegree = " Temperature: " + roundedFahrenheit + "°";

            // returns the fahrenheight value calculated
            return fahrenheitDegree;

        } catch (Exception e)
        {
            System.out.println(e);
        }

        // returns this if the city or state is not found
        return " City or state not found. Please enter the city and state again.";

    }
}

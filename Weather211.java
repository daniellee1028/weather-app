package Weather211;

//Daniel Lee
//950738849
//Feb 9, 2022
//Description: The purpose of this program is to receive a city name. If an invalid city name is entered, the program 
//will prompt to input a valid city name. If it is a valid city name, the program will provide real-time weather
//data for the city. Real-time weather data includes current weather, current temperature, low temperature,
//high temperature, humidity, wind speed, sunrise time, and sunset date. This program implements big data and HashMap 
//techniques such as API's and saving values to keys. 

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Weather211 {

    private static HashMap<String, String> cityWeather = new HashMap<>();

    static Date date;
    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    static String nameOfCity;    
    
    public static boolean CityWeather(String cityName) throws Exception {

    	nameOfCity = cityName;
    	
        boolean validCityName = false;

        try {
        	//JSON parser object to parse read file
            JSONParser jsonParser = new JSONParser();

            //Create a URL instance
            String firstPartURL = "https://api.openweathermap.org/data/2.5/weather?q=";
            String secondPartURL = "&appid=e3bc6987d57ec6bc869b7a30ad7d8f85";
            String theURL = firstPartURL + cityName + secondPartURL;
            URL url = new URL(theURL);
            
            //Reads information from URL
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            
            //Read JSON file. All the data for the city is stored in "jsonObj"
            JSONObject jsonObj = (JSONObject) jsonParser.parse(br);
            JSONObject main = (JSONObject) jsonObj.get("main");
            JSONObject wind = (JSONObject) jsonObj.get("wind");
            JSONObject sys = (JSONObject) jsonObj.get("sys");      
            
//1. Weather
            JSONArray weatherArray = (JSONArray)jsonObj.get("weather");  // read and save it to an Array
            JSONObject w = (JSONObject) weatherArray.get(0);  // Read the first item in the array

            String weatherNow = w.get("description").toString();

            // save key and value to cityWeather hashMap
            String key="1. Current Weather";
            String value= weatherNow;
            cityWeather.put(key, value);
            //key "1. Current Weather" paired with the value of weatherNow in HashMap cityWeather
            
//2. Temperature
            double cityTemp = Double.parseDouble(main.get("temp").toString());

            //convert from kelvin to Fahrenheit
            cityTemp = ((cityTemp - 273.15)*9)/5 + 32;

            //save key and value to cityWeather hashMap
            key="2. Current Temperature";
            value= (String.format("%.1f", cityTemp)+"\u00B0"+"F");

            cityWeather.put(key, value);
            //key "2. Current Temperature" paired with the value of cityTemp in HashMap cityWeather
            
//3. Temp_min
            double cityTempMin = Double.parseDouble(main.get("temp_min").toString());
            cityTempMin = ((cityTempMin - 273.15)*9)/5 + 32;

            //save key and value to cityWeather hashMap
            key="3. Low Temperature";
            value= (String.format("%.1f", cityTempMin)+"\u00B0"+"F");
            cityWeather.put(key, value);
            //key "3. Low Temperature" paired with the value of cityTempMin in HashMap in cityWeather
            
//4. Temp_max
            cityWeather.put(key, value);
            double cityTempMax = Double.parseDouble(main.get("temp_max").toString());
            cityTempMax = ((cityTempMax - 273.15)*9)/5 + 32;
            key="4. Max Temperature";
            value= (String.format("%.1f", cityTempMax)+"\u00B0"+"F");
            cityWeather.put(key, value);
            //key "4. Max Temperature" paired with the value of cityTempMax in HashMap in cityWeather
            
//5. Humidity 
            long cityHumidity = Long.parseLong(main.get("humidity").toString());
            // save key and value to cityWeather hashMap
            key="5. Humidity";
            value= (String.format("%d", cityHumidity)+"%");
            cityWeather.put(key, value);
            //key "5. Humidity" paired with the value of cityHumidity in HashMap in cityWeather
            
//6. Wind Speed
            double cityWindSpeed =  Double.parseDouble(wind.get("speed").toString());

            //save key and value to cityWeather hashMap
            key="6. Wind Speed";
            value= (String.format("%.1f", cityWindSpeed)+" meter/sec");
            cityWeather.put(key, value);
            //key "6. Wind Speed" paired with the value of cityWindSpeed in HashMap in cityWeather
            
//7. Sunrise
            long citySunrise = Long.parseLong(sys.get("sunrise").toString());
            date = new Date(citySunrise* 1000L);

            //save key and value to cityWeather hashMap
            value= sdf.format(date);  // convert to hh:mm format
            key="7. Sunrise";
            cityWeather.put(key, value);
            //key "7. Sunrise" paired with the value of citySunrise in HashMap in cityWeather

//8. Sunset
            long citySunset = Long.parseLong(sys.get("sunset").toString());
            date = new Date(citySunset* 1000L);

            value= sdf.format(date);  // convert to hh:mm format
            key="8. Sunset";
            cityWeather.put(key, value);
            //key "8. Sunset" paired with the value of citySunset in HashMap in cityWeather
            
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public static void printWeatherData() {
    	System.out.println();
    	System.out.println("Current Weather [" + nameOfCity + "]");
        System.out.println();
        System.out.println("1. Current Weather: " + cityWeather.get("1. Current Weather"));
        System.out.println("2. Current Temperature: " + cityWeather.get("2. Current Temperature"));
        System.out.println("3. Low Temperature: " + cityWeather.get("3. Low Temperature"));
        System.out.println("4. High Temperature: " + cityWeather.get("4. Max Temperature"));
        System.out.println("5. Humidity: " + cityWeather.get("5. Humidity"));
        System.out.println("6. Wind: " + cityWeather.get("6. Wind Speed"));
        System.out.println("7. Sunrise: " + cityWeather.get("7. Sunrise"));
        System.out.println("8. Sunset: " + cityWeather.get("8. Sunset"));
        //Formatting the result to be the same as Professor Kim's format
    }
    }


package Weather211;

//Daniel Lee
//950738849
//Feb 9, 2022

import java.util.HashMap;
import java.util.Scanner;

public class myWeatherApp {


    static Scanner console = new Scanner(System.in);
    //Create a Scanner input for the city name
    
    static HashMap<String, String> weatherNow = new HashMap<>();

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Weather 211");
        System.out.println();
        System.out.println("Input a city name:");
 
        inputCityName();
        printWeatherData();
        //Once prompted to input a city name, check for cityName validity
        //once cityName is valid, print the weather data
    }

    public static void inputCityName() throws Exception{

        boolean validCityName=false;
        while (!validCityName) {
            Scanner sc = new Scanner(System.in);
            String cityName = sc.nextLine();

            boolean valid = Weather211.CityWeather(cityName);

            // input city name

            // pass this city name to Weather211
            // receive true or false
            // true means Weather211 has successfully read the city's weather data.
            // false means Weather211 could not read the weather data because it could not find the city name.

            if (valid) {
                validCityName = true;
                break;
            } else {
                System.out.println("\nInvalid city name. Type again.\n");
                System.out.println("Input a city name:");
                //If cityName is invalid, program will prompt the user to input a valid city  name again
            }
        }
        
    }
    public static void printWeatherData() {
        Weather211.printWeatherData();
        
    }
}

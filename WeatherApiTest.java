
package com.weather;  

import java.io.IOException;
import java.util.Scanner;  

public class WeatherApiTest {  
    public static void main(String[] args) {  
        WeatherApiService weatherApiService = new WeatherApiService();  
        Scanner scanner = new Scanner(System.in);  
        System.out.print("Enter the city name: ");
        String city = scanner.nextLine();

        scanner.close();

        try {  
            WeatherResponse weather = weatherApiService.getCurrentWeather(city);  
            System.out.println("Current temperature in " + weather.getName() + ": " + weather.getMain().getTemp() + "°C");  
        } catch (IOException e) {  
            System.err.println("Error fetching weather data: " + e.getMessage());  
        }  
    }  
}
package com.weather;  

import java.io.IOException;  

import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;  

public class WeatherApiService {  
    private static final String API_KEY = "e0523b1fd6d4295956985ffa5b727dc7"; 
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";  

    public WeatherResponse getCurrentWeather(String city) throws IOException {  
        String urlString = String.format("%s?q=%s&appid=%s&units=metric", BASE_URL, city, API_KEY);  
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {  
            HttpGet request = new HttpGet(urlString);  
            try (CloseableHttpResponse response = httpClient.execute(request)) {  
                if (response.getStatusLine().getStatusCode() != 200) {  
                    throw new IOException("Failed to fetch weather data: " + response.getStatusLine());  
                }  
                String jsonResponse = EntityUtils.toString(response.getEntity());  
                Gson gson = new Gson();  
                return gson.fromJson(jsonResponse, WeatherResponse.class);  
            }  
        }  
    }  
}
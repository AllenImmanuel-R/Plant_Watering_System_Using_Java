package com.plantwatering;

import com.fasterxml.jackson.databind.JsonNode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        try {

            double lat = 11.3391202;
            double lon = 77.7105902;

            // Fetch the weather data based on lat and lon
            JsonNode weatherData = WeatherService.getWeather(lat, lon);

            // Extract temperature, humidity, and rain information
            double temperature = weatherData.get("main").get("temp").asDouble();
            double humidity = weatherData.get("main").get("humidity").asDouble();
            boolean isRaining = weatherData.get("weather").get(0).get("main").asText().equalsIgnoreCase("Rain");

            // Make watering decision based on weather conditions
            boolean shouldWater = WateringDecisionMaker.shouldWaterPlant(temperature, humidity, isRaining);
            String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Log the watering decision to the MongoDB
            MongoDBService dbService = new MongoDBService();
            dbService.logWatering(dateTime, shouldWater);

            // Output weather information and watering decision
            System.out.println("Weather: Temp=" + temperature + "°C, Humidity=" + humidity + "%, Rain=" + isRaining);
            System.out.println("Water Plants: " + (shouldWater ? "Yes" : "No"));

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

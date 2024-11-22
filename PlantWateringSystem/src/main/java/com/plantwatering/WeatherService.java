package com.plantwatering;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherService {
    private static final String API_KEY = "YOUR_API";  // Use your OpenWeatherMap API key here
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    // Modify the method to accept latitude and longitude
    public static JsonNode getWeather(double lat, double lon) throws Exception {
        OkHttpClient client = new OkHttpClient();

        // Construct the URL with lat and lon instead of city
        String url = API_URL + "?lat=" + lat + "&lon=" + lon + "&appid=" + API_KEY + "&units=metric";

        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("Failed to fetch weather data");
            }

            // Use Jackson to parse the response into a JsonNode
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(response.body().string());
        }
    }
}

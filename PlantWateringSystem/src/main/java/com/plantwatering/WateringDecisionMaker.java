package com.plantwatering;

public class WateringDecisionMaker {
    public static boolean shouldWaterPlant(double temperature, double humidity, boolean isRaining) {
        // Conditions to water plants:
        // - Temperature > 30°C
        // - Humidity < 50%
        // - Not raining
        return temperature > 25 && humidity < 80 && !isRaining;
    }
}

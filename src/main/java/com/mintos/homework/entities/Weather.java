package com.mintos.homework.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
//@Entity
public class Weather {
    private double latitude;
    private double longitude;
    @JsonProperty("generationtime_ms")
    private double generationTimeMs;
    @JsonProperty("utc_offset_seconds")
    private int utcOffsetSeconds;
    private String timezone;
    @JsonProperty("timezone_abbreviation")
    private String timezoneAbbreviation;
    private double elevation;
    @JsonProperty("current_weather")
    private CurrentWeather currentWeather;
}

package com.mintos.homework.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mintos.homework.entities.Geolocation;
import com.mintos.homework.entities.Weather;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class WeatherApiService {

    private static final String url = "https://api.open-meteo.com/v1/forecast?";

    public Weather getCurrentWeatherByLocation(Geolocation geolocation) throws MalformedURLException {

        try {
            URL fullUrl = new URL( url + "latitude=" + geolocation.getLat() + "&longitude=" + geolocation.getLon() + "&current_weather=true");
            return new ObjectMapper().readValue(fullUrl, Weather.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

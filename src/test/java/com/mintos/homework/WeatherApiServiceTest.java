package com.mintos.homework;

import com.mintos.homework.api.WeatherApiService;
import com.mintos.homework.entities.Geolocation;
import com.mintos.homework.entities.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

public class WeatherApiServiceTest {

    @Test
    public void testEqualCoordinatesByWeatehrApi() throws MalformedURLException, UnknownHostException {
        String locationLatitude = "56.954216";
        String locationLongitude = "24.095154";
        Geolocation currentLocation = new Geolocation();
        currentLocation.setLon(Double.parseDouble(locationLongitude));
        currentLocation.setLat(Double.parseDouble(locationLatitude));

        WeatherApiService weatherApiService = new WeatherApiService();
        Weather weather = weatherApiService.getCurrentWeatherByLocation(currentLocation);

        Assertions.assertEquals(String.valueOf(weather.getLongitude()), locationLongitude);
        Assertions.assertEquals(String.valueOf(weather.getLatitude()), locationLatitude);
    }
}

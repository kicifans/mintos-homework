package com.mintos.homework.controllers;

import com.mintos.homework.entities.CurrentWeather;
import com.mintos.homework.entities.Geolocation;
import com.mintos.homework.entities.Weather;
import com.mintos.homework.api.LocationByIpApiService;
import com.mintos.homework.api.WeatherApiService;
import com.mintos.homework.exceptions.InvalidIpException;
import com.mintos.homework.exceptions.InvalidLatitudeLongitudeException;
import com.mintos.homework.exceptions.LocationNotFoundException;
import com.mintos.homework.services.CurrentWeatherService;
import com.mintos.homework.services.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


@RestController
public class WeatherController {

    @Autowired
    LocationByIpApiService locationByIpApiService;
    @Autowired
    WeatherApiService weatherApiService;
    @Autowired
    CurrentWeatherService currentWeatherService;
    @Autowired
    GeolocationService geolocationService;

    @GetMapping("/weather")
    public Weather findCountryByIp() throws MalformedURLException, LocationNotFoundException {
        Geolocation currentLocation = locationByIpApiService.getLocation();
        if(currentLocation != null) {
            Weather weather = weatherApiService.getCurrentWeatherByLocation(currentLocation);
            CurrentWeather currentWeather = weather.getCurrentWeather();
            currentWeather.setIpLocationId(currentLocation.getId());
            currentWeatherService.save(currentWeather);
            return weather;
        } else {
            throw new LocationNotFoundException();
        }

    }

    @GetMapping("/weather/weather-history-by-ip/{ip}")
    public List<CurrentWeather> getWeatherHistoryByIp(@PathVariable String ip) throws InvalidIpException {

        boolean isValidIp = Pattern.matches("^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$", ip);

        if(isValidIp) {
            Geolocation geolocation = geolocationService.getByIp(ip);
            return geolocation != null ? currentWeatherService.getByIpLocationId(geolocation.getId()) : new ArrayList<>();
        } else {
            throw new InvalidIpException();
        }
    }

    @GetMapping("/weather/weather-history-by-location/lat={lat}&lon={lon}")
    public List<CurrentWeather> getWeatherHistoryByLocation(@PathVariable String lat, @PathVariable String lon) throws InvalidLatitudeLongitudeException {

        boolean isValidLongitude = Pattern.matches("^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$",lon);
        boolean isValidLatitude = Pattern.matches("^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$",lat);

        if(isValidLatitude && isValidLongitude) {
            Geolocation geolocation = geolocationService.getByLatitudeAndLongitude(lat, lon);
            return geolocation != null ? currentWeatherService.getByIpLocationId(geolocation.getId()) : new ArrayList<>();
        } else {
            throw new InvalidLatitudeLongitudeException();
        }

    }

    @ExceptionHandler({InvalidIpException.class})
    public String handleInvalidIdException() {
        return "Invalid IP address!";
    }

    @ExceptionHandler({InvalidLatitudeLongitudeException.class})
    public String handleLatitudeLongitudeException() {
        return "Invalid latitude and/or longitude!";
    }

    @ExceptionHandler({LocationNotFoundException.class})
    public String handleLocatonNotFoundException() {
        return "Error! Location could not be detected!";
    }
}

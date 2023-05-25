package com.mintos.homework.services;

import com.mintos.homework.entities.CurrentWeather;
import com.mintos.homework.repositories.CurrentWeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CurrentWeatherService {

    @Autowired
    CurrentWeatherRepository currentWeatherRepository;

    public List<CurrentWeather> getAll(){
        return currentWeatherRepository.findAll();
    }

    public List<CurrentWeather> getByIpLocationId(int ip){
        return currentWeatherRepository.findByIpLocationId(ip);
    }


    public CurrentWeather save(CurrentWeather currentWeather) {
        return currentWeatherRepository.save(currentWeather);
    }
}

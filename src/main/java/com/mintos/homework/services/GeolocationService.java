package com.mintos.homework.services;

import com.mintos.homework.entities.Geolocation;
import com.mintos.homework.repositories.GeolocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeolocationService {

    @Autowired
    private GeolocationRepository geolocationRepository;

    public Geolocation save(Geolocation geolocation) {
        return geolocationRepository.save(geolocation);
    }

    public List<Geolocation> getAll(){
        return geolocationRepository.findAll();
    }

    public Geolocation getByHostName(String hostName){
        return geolocationRepository.findByHostName(hostName);
    }

    public Geolocation getByIp(String ip){
        return geolocationRepository.findByQuery(ip);
    }

    public Geolocation getByLatitudeAndLongitude(String lat, String lon){
        return geolocationRepository.findByLatAndLon(lat, lon);
    }

}

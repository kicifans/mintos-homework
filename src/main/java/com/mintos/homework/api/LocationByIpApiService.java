package com.mintos.homework.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.mintos.homework.entities.Geolocation;
import com.mintos.homework.services.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

@Service
public class LocationByIpApiService {

    @Autowired
    GeolocationService geolocationService;

    private static final String url = "http://ip-api.com/json/";

    private static Cache<String, Geolocation> cache = null;

    public Geolocation getLocation(){
        String localMachineHostName = "";
        try {
            localMachineHostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        if(cache == null) {
            cache = Caffeine.newBuilder()
                    .maximumSize(1000).expireAfterWrite(1, TimeUnit.DAYS)
                    .build();
        }

        Geolocation geolocation;
        geolocation = getLocationFromCache(cache,localMachineHostName);
        if(geolocation != null) {
            return geolocation;
        } else {
            geolocation = geolocationService.getByHostName(localMachineHostName);
        }
        if(geolocation != null){
            cache.put(localMachineHostName, geolocation);
            return geolocation;
        }
        try {
            geolocation = getLocationFromIp(localMachineHostName, true);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return geolocation != null ? geolocationService.save(geolocation) : null;
    }

    public Geolocation getLocationFromIp(String localMachineHostName, boolean withCaching) throws MalformedURLException {
        URL url = null;
        try {
            url = new URL(LocationByIpApiService.url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try {
            Geolocation geolocation = new ObjectMapper().readValue(url, Geolocation.class);
            geolocation.setHostName(localMachineHostName);
            if(withCaching)cache.put(localMachineHostName,geolocation);
            return geolocation;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Geolocation getLocationFromCache(Cache<String, Geolocation> cache, String localMachineHostName) {
        return cache.getIfPresent(localMachineHostName);
    }
}

package com.mintos.homework.repositories;

import com.mintos.homework.entities.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeolocationRepository extends JpaRepository<Geolocation, Integer> {

    Geolocation findByHostName(String hostName);

    Geolocation findByQuery(String ip);

    Geolocation findByLatAndLon(String lat, String lon);
}

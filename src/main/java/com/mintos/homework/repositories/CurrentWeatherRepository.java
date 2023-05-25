package com.mintos.homework.repositories;

import com.mintos.homework.entities.CurrentWeather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrentWeatherRepository extends JpaRepository<CurrentWeather, Integer> {

    List<CurrentWeather> findByIpLocationId(int ipLocationId);
}

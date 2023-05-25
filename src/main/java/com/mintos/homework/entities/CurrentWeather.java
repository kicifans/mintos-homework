package com.mintos.homework.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="current_weather")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeather {
    @Id
    @GeneratedValue
    private int id;
    private int ipLocationId;
    private double temperature;
    private double windSpeed;
    private double windDirection;
    private int weathercode;
    private int isDay;
    private String time;
}

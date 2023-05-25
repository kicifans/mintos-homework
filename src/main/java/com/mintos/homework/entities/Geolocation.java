package com.mintos.homework.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="ip_location")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geolocation {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "ip")
    private String query;
    private String hostName;
    private String country;
    private String countryCode;
    private String region;
    private String city;
    private double lat;
    private double lon;
}

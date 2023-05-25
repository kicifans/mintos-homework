package com.mintos.homework;

import com.mintos.homework.api.LocationByIpApiService;
import com.mintos.homework.entities.Geolocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;


public class LocationByIpServiceTest {
    @Test
    public void testEqualCoordinatesByIp() throws MalformedURLException, UnknownHostException {
        String locationLatitude = "56.9496";
        String locationLongitude = "24.0978";
        LocationByIpApiService locationByIpApiService = new LocationByIpApiService();
        Geolocation geolocation = locationByIpApiService.getLocationFromIp(InetAddress.getLocalHost().getHostName(),false);

        Assertions.assertEquals(String.valueOf(geolocation.getLon()), locationLongitude);
        Assertions.assertEquals(String.valueOf(geolocation.getLat()), locationLatitude);
    }
}

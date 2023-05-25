package com.mintos.homework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidationTest {

    @Test
    public void testIpValidation(){
        String ipPattern = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";

        String correctIp = "174.144.120.231";
        String incorretIp = "174.1420.231";

        Assertions.assertTrue(correctIp.matches(ipPattern));
        Assertions.assertFalse(incorretIp.matches(ipPattern));
    }

    @Test
    public void testCoordinatesValidation() {
        String longitudeValidation = "^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$";
        String latitudeValidation = "^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$";

        String correctLongitude = "120";
        String incorrectLongitude = "190";

        String correctLatitude = "70.22";
        String incorrectLatitude = "aa";

        Assertions.assertTrue(correctLongitude.matches(longitudeValidation));
        Assertions.assertFalse(incorrectLongitude.matches(longitudeValidation));

        Assertions.assertTrue(correctLatitude.matches(latitudeValidation));
        Assertions.assertFalse(incorrectLatitude.matches(latitudeValidation));
    }
}

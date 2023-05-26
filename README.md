# mintos-homework
SpringBoot application, to receive weather conditions, based on your location.

# Possible endpoints:
1. '/weather' - returns current weather conditions of your location, which is tracked by public ip address;
2. '/weather/weather-history-by-ip/{ip}' - returns weather search history by ip address;
3. '/weather/weather-history-by-location/lat={lat}&lon={lon}' returns weather searh history by location.

# About application:
It uses API service 'ip-api.com' to retrieve location and public IP address.
Weather service 'api.open-meteo.com' is used to retrieve weather conditions by latitude and longitude, received by previous API call.

When trying to gather current weather data , first we search if In-memory cache already contains our location. 
If that is true, we use it to call weather API. If cache doesnt contain it, we search database for location data.
If all above doesnt contain location data, ip-api is called.


Before starting application, database connection properties should be set up in application.properties file.

# Tech used:
SpringBoot

Java-17

Maven

MySQL

Lombok

JUnit


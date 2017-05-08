package com.example.aasviken.weatherapp;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class WeatherData {
    @Element(required = false)
    Forecast forecast;

    public Forecast getForecast() {
        return forecast;
    }


    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}

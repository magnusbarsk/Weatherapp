package com.example.aasviken.weatherapp;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict=false)
public class Forecast {
    @ElementList(name="tabular")
    List<Time> timeList;

    @Element(required = false)
    Location location;

    public List<Time> getTimeList() {
        return timeList;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setTimeList(List<Time> timeList) {
        this.timeList = timeList;




    }


}


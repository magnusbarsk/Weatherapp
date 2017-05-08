package com.example.aasviken.weatherapp;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by aasviken on 26/04/2017.
 */
@Root(strict = false)
public class Pressure {
    @Attribute(required = false)
    String unit;

    @Attribute(required = false)
    double value;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

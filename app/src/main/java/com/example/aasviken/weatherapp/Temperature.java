package com.example.aasviken.weatherapp;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by aasviken on 26/04/2017.
 */
@Root(strict = false)
public class Temperature {
    @Attribute(required = false)
    String unit;

    @Attribute(required = false)
    int value;

    public String getUnit() {
        return unit;
    }

    public int getValue() {
        return value;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

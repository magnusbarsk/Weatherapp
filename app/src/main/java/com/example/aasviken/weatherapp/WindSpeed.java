package com.example.aasviken.weatherapp;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by aasviken on 26/04/2017.
 */
@Root(strict = false)
public class WindSpeed {
     @Attribute(required = false)
     String name;

    @Attribute(required = false)
    double mps;

    public double getMps() {
        return mps;
    }

    public void setMps(double mps) {
        this.mps = mps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

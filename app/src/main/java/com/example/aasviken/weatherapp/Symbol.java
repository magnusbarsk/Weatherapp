package com.example.aasviken.weatherapp;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by aasviken on 26/04/2017.
 */
@Root(strict = false)
public class Symbol {
    @Attribute
    int number;

    @Attribute
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

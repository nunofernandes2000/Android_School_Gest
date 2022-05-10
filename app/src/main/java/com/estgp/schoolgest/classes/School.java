package com.estgp.schoolgest.classes;

import java.io.Serializable;

public class School implements Serializable {

    private String name;
    private String initials;
    private String city;


    public School() {
    }

    public School(String name, String initials, String city) {
        this.name = name;
        this.initials = initials;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return this.name;
    }
}



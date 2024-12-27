package org.tommap.tomlearnspring.sample_beans.model;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Manufacturer {
    private String name;
    private final Vehicle vehicle;

    @Autowired
    public Manufacturer(@Qualifier("jerryVehicle") Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @PostConstruct
    void init() {
        this.name = "Tom's Manufacturer";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

package org.tommap.tomlearnspring.aop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tommap.tomlearnspring.aop.aspects.LogAspect;
import org.tommap.tomlearnspring.aop.interfaces.Speakers;
import org.tommap.tomlearnspring.aop.interfaces.Tyres;
import org.tommap.tomlearnspring.aop.model.Song;

@Component
public class VehicleServices {
    private final Speakers speakers;
    private final Tyres tyres;

    @Autowired
    public VehicleServices(Speakers speakers, Tyres tyres) {
        this.speakers = speakers;
        this.tyres = tyres;
    }

    @LogAspect
    public String playMusic(boolean vehicleStarted, Song song) {
        return speakers.makeSound(song);
    }

    public String moveVehicle(boolean vehicleStarted) {
        return tyres.rotate();
    }

    public String applyBrake(boolean vehicleStarted) {
        return tyres.stop();
    }

    public Speakers getSpeakers() {
        return speakers;
    }

    public Tyres getTyres() {
        return tyres;
    }
}

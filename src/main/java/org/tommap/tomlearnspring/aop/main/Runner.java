package org.tommap.tomlearnspring.aop.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.tommap.tomlearnspring.aop.config.ProjectConfiguration;
import org.tommap.tomlearnspring.aop.model.Song;
import org.tommap.tomlearnspring.aop.services.VehicleServices;

public class Runner {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class)) {
            var vehicleServices = context.getBean(VehicleServices.class);
            System.out.println(vehicleServices.getClass());

            Song song = new Song();
            song.setTitle("Pickleball");
            song.setSingerName("Do Phu Qui");

            boolean vehicleStarted = true;

            String moveVehicleStatus = vehicleServices.moveVehicle(vehicleStarted);
            System.out.println(moveVehicleStatus);

            String playMusicStatus = vehicleServices.playMusic(vehicleStarted, song);
            System.out.println(playMusicStatus);

            String applyBrakeStatus = vehicleServices.applyBrake(vehicleStarted);
            System.out.println(applyBrakeStatus);
        } catch (Exception ex) {
            System.out.println("Error occurred: " + ex.getMessage());
        }
    }
}

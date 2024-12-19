package org.tommap.tomlearnspring.beans.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.tommap.tomlearnspring.beans.config.ProjectConfiguration;
import org.tommap.tomlearnspring.beans.model.Vehicle;

public class Runner {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle(); //simple POJO object - not Spring bean
        vehicle.setName("Pun");
        System.out.println("Vehicle name from non-Spring context: " + vehicle.getName());

        //initialize IoC container
        var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        Vehicle veh = context.getBean(Vehicle.class);
        System.out.println("Vehicle name from Spring context: " + veh.getName());

        String hello = context.getBean(String.class);
        System.out.println("String value from Spring context: " + hello);

        Integer number = context.getBean(Integer.class);
        System.out.println("Integer value from Spring context: " + number);
    }
}

package org.tommap.tomlearnspring.beans.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.tommap.tomlearnspring.beans.config.ProjectConfiguration;
import org.tommap.tomlearnspring.beans.model.Animal;
import org.tommap.tomlearnspring.beans.model.Vehicle;

public class Runner {
    public static void main(String[] args) {
        //initialize IoC container
        var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        Vehicle tomVehicle = context.getBean(Vehicle.class);
        System.out.println("Vehicle name from Spring context: " + tomVehicle.getName());

        Animal animal = context.getBean(Animal.class);
        animal.setName("Tom");
        animal.sayHello();
    }
}

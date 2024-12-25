package org.tommap.tomlearnspring.beans.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.tommap.tomlearnspring.beans.model.Manufacturer;
import org.tommap.tomlearnspring.beans.model.Vehicle;

/*
    - @Configuration
        + indicates that the class has @Bean definition methods
        + the content of this class will be scanned and beans will be generated during IoC container initialization
 */
@Configuration
@ComponentScan("org.tommap.tomlearnspring.beans")
public class ProjectConfiguration {
    /*
        - @Bean
            + lets Spring know that it needs to call this method when it initializes IoC container and adds
                the returned value as a bean to the context
     */
    @Bean(name = "tomVehicle")
    /*
        - @Primary
            + default bean to be considered in the scenarios of ambiguity - when there are multiple beans of the same type
     */
    @Primary
        /*
        - method names usually follow verbs notation: moveVehicle, sayHello, ...
        - however, for methods which we use to create beans, can use nouns as names
            + this will be a good practice as the method names will become bean names as well in the context
     */
    public Vehicle vehicle() {
        var vehicle = new Vehicle();
        vehicle.setName("Tom");

        return vehicle;
    }

    @Bean(name = "jerryVehicle")
    public Vehicle vehicle2() {
        var vehicle = new Vehicle();
        vehicle.setName("Jerry");

        return vehicle;
    }

//    @Bean
//    public Manufacturer manufacturer() {
//        Manufacturer manufacturer = new Manufacturer();
//        manufacturer.setName("Jerry's Manufacturer");
//        manufacturer.setVehicle(vehicle2()); //method call
//
//        return manufacturer;
//    }

//    @Bean
//    public Manufacturer manufacturer(@Qualifier("jerryVehicle") Vehicle vehicle) {
//        Manufacturer manufacturer = new Manufacturer();
//        manufacturer.setName("Jerry's Manufacturer");
//        manufacturer.setVehicle(vehicle);
//
//        return manufacturer;
//    }
}

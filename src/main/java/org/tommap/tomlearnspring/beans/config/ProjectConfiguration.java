package org.tommap.tomlearnspring.beans.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tommap.tomlearnspring.beans.model.Vehicle;

/*
    - @Configuration
        + indicates that the class has @Bean definition methods
        + the content of this class will be scanned and beans will be generated during IoC container initialization
 */
@Configuration
public class ProjectConfiguration {
    /*
        - @Bean
            + lets Spring know that it needs to call this method when it initializes IoC container and adds
                the returned value as a bean to the context
     */
    @Bean
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

    @Bean
    public String hello() {
        return "Hello Tom";
    }

    @Bean
    public Integer number() {
        return 123456;
    }
}
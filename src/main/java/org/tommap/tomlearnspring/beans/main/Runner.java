package org.tommap.tomlearnspring.beans.main;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.tommap.tomlearnspring.beans.config.ProjectConfiguration;
import org.tommap.tomlearnspring.beans.model.Animal;
import org.tommap.tomlearnspring.beans.model.Company;
import org.tommap.tomlearnspring.beans.model.Manufacturer;
import org.tommap.tomlearnspring.beans.model.TestSingleton;
import org.tommap.tomlearnspring.beans.model.Vehicle;

import java.util.Random;
import java.util.function.Supplier;

public class Runner {
    public static void main(String[] args) {
        //initialize IoC container
        var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        var testSingletonI = context.getBean(TestSingleton.class);
        var testSingletonII = context.getBean(TestSingleton.class);

        System.out.println("hashcode of testSingletonI: " + testSingletonI.hashCode());
        System.out.println("hashcode of testSingletonII: " + testSingletonII.hashCode());

        if (testSingletonI == testSingletonII) {
            System.out.println("TestSingletonBean is a singleton bean scope");
        }

//        Vehicle tomVehicle = context.getBean(Vehicle.class);
//        System.out.println("Vehicle name from Spring context: " + tomVehicle.getName());
//
//        Animal animal = context.getBean(Animal.class);
//        animal.sayHello();

//        Manufacturer manufacturer = context.getBean(Manufacturer.class);
//        System.out.println("Manufacturer name from Spring context: " + manufacturer.getName());
//        System.out.println("Vehicle of Tom's Manufacturer: " + manufacturer.getVehicle().getName());

        //creating beans programmatically
//        Supplier<Company> saigonTechSupplier = () -> {
//            var saigonTech = new Company();
//            saigonTech.setName("SaigonTech");
//
//            return saigonTech;
//        };
//
//        Supplier<Company> tamaraSupplier = () -> {
//            var tamara = new Company();
//            tamara.setName("Tamara");
//
//            return tamara;
//        };
//
//        Random random = new Random();
//        int randomNum = random.nextInt(10);
//        System.out.println("Random number: " + randomNum);
//
//        if (0 == (randomNum % 2)) {
//            context.registerBean("saigonTech", Company.class, saigonTechSupplier);
//        } else {
//            context.registerBean("tamara", Company.class, tamaraSupplier);
//        }
//
//        Company saigonTech = null, tamara = null;
//
//        try {
//            saigonTech = context.getBean("saigonTech", Company.class);
//        } catch (NoSuchBeanDefinitionException ex) {
//            System.out.println("Error while creating SaigonTech bean: " + ex.getMessage());
//        }
//
//        try {
//            tamara = context.getBean("tamara", Company.class);
//        } catch (NoSuchBeanDefinitionException ex) {
//            System.out.println("Error while creating Tamara bean: " + ex.getMessage());
//        }
//
//        if (null != saigonTech) {
//            saigonTech.sayHello();
//        } else if (null != tamara){
//            tamara.sayHello();
//        } else {
//            System.out.println("neither SaigonTech nor Tamara bean is created");
//        }

        context.close();
    }
}

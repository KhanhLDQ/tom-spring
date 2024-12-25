package org.tommap.tomlearnspring.beans.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tommap.tomlearnspring.beans.model.CoffeeStore;

public class XmlRunner {
    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("beans.xml");
        CoffeeStore coffeeStore = context.getBean("coffeeStore", CoffeeStore.class);
        System.out.println("Coffee Store Name: " + coffeeStore.getName() + " - Address: " + coffeeStore.getAddress());
    }
}

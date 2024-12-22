package org.tommap.tomlearnspring.beans.model;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class Animal {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct //help to initialize the code that we want after the bean is created by Spring IoC container
    public void init() {
        this.name = "Tom";
    }

    @PreDestroy
    public void destroy() { //help to perform cleanup operations before closing application context and destroying beans
        System.out.println("Destroying Animal bean");
    }

    public void sayHello() {
        System.out.println("Animal name from Spring context: " + name);
    }
}

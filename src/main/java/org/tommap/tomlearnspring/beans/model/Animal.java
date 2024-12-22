package org.tommap.tomlearnspring.beans.model;

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

    public void sayHello() {
        System.out.println("Animal name from Spring context: " + name);
    }
}

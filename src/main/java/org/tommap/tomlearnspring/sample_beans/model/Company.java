package org.tommap.tomlearnspring.sample_beans.model;

public class Company {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println("Company name from Spring context: " + name);
    }
}

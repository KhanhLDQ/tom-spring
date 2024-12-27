package org.tommap.tomlearnspring.sample_beans.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*
    - Prototype
        + everytime we request a bean reference - we will get a brand-new object instance
        + no concept of eager or lazy instantiation in the prototype
 */
@Component("testPrototype")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class TestPrototype {
    public TestPrototype() {
        System.out.println("TestPrototype bean is created");
    }
}

package org.tommap.tomlearnspring.sample_beans.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*
    - Singleton
        + default bean scope of Spring
        + returns the same bean instance for every request
    - Singleton Bean Instantiation
        + EAGER (default behavior) - Spring IoC Container will try to initialize all singleton beans eagerly during the startup itself, regardless of whether those beans are used or not inside the application
        + LAZY
            - only be initialized when the bean is requested for the first time
            - problem: suppose if there is an exception during the bean creation then we can get the runtime exception
 */
@Component("testSingleton")
@Scope(BeanDefinition.SCOPE_SINGLETON)
//@Lazy
public class TestSingleton {
    public TestSingleton() {
        System.out.println("TestSingleton bean is created");
    }
}

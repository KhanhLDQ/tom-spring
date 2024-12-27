package org.tommap.tomlearnspring.beans.model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*
    - Singleton
        + default bean scope of Spring
        + returns the same bean instance for every request
 */
@Component("testSingleton")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class TestSingleton {
}

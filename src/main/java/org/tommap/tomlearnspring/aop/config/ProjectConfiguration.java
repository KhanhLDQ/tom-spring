package org.tommap.tomlearnspring.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("org.tommap.tomlearnspring.aop")
@EnableAspectJAutoProxy //enable AOP inside application
public class ProjectConfiguration {
}

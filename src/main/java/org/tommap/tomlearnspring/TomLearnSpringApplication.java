package org.tommap.tomlearnspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class TomLearnSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TomLearnSpringApplication.class, args);
    }

}

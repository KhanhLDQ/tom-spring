package org.tommap.tomlearnspring.aop.interfaces.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.tommap.tomlearnspring.aop.interfaces.Tyres;

@Component
@Primary
public class MichelinTyres implements Tyres {
    @Override
    public String rotate() {
        return "Vehicle moving with the help of Michelin tyres";
    }

    @Override
    public String stop() {
        return "Vehicle stopped with the help of Michelin tyres";
    }
}

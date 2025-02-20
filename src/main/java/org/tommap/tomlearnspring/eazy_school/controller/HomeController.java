package org.tommap.tomlearnspring.eazy_school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    /*
        - provides routing information
        - tells Spring that any HTTP request with the given path should be mapped to corresponding method
     */
    @RequestMapping(value = {"", "/", "/home"})
    public String displayHomePage() {
        return "home.html";
    }
}

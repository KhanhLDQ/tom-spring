package org.tommap.tomlearnspring.eazy_school.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tommap.tomlearnspring.eazy_school.model.Person;

@Controller
@RequestMapping("/student")
public class StudentController {
    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model, HttpSession httpSession) {
        var person = (Person) httpSession.getAttribute("loggedInPerson");

        ModelAndView modelAndView = new ModelAndView("courses_enrolled.html");
        modelAndView.addObject("person", person);

        return modelAndView;
    }
}

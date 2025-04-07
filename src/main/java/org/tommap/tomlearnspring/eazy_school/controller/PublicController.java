package org.tommap.tomlearnspring.eazy_school.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tommap.tomlearnspring.eazy_school.model.Person;
import org.tommap.tomlearnspring.eazy_school.service.IPersonService;

@RequestMapping("/public")
@Controller
@RequiredArgsConstructor
public class PublicController {
    private final IPersonService personService;

    @GetMapping("/register")
    public String displayRegisterPage(Model model) {
        model.addAttribute("person", new Person());
        return "register.html";
    }

    @PostMapping("/createUser")
    public String createUser(
            @Valid @ModelAttribute("person") Person person,
            Errors errors
    ) {
        if (errors.hasErrors()) {
            return "register.html";
        }

        boolean isSaved = personService.createPerson(person);
        if (isSaved) {
            return "redirect:/login?register=true";
        } else {
            return "register.html";
        }
    }
}

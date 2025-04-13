package org.tommap.tomlearnspring.eazy_school.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tommap.tomlearnspring.eazy_school.repository.PersonRepository;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DashboardController {
    private final PersonRepository personRepository;

    @RequestMapping(value = "/dashboard", method = {RequestMethod.GET, RequestMethod.POST})
    public String displayDashboardPage(Model model, Authentication authentication, HttpSession httpSession) {
        var person = personRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());

        if (null != person.getEazyClass()) {
            model.addAttribute("enrolledClass", person.getEazyClass().getName());
        }

        httpSession.setAttribute("loggedInPerson", person);

        return "dashboard.html";
    }
}

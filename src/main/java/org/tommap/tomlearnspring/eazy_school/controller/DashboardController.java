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
        //only for testing log level
        logMessage();

        return "dashboard.html";
    }

    private void logMessage() {
        log.error("Error message from the dashboard page");
        log.warn("Warning message from the dashboard page");
        log.info("Info message from the dashboard page");
        log.debug("Debug message from the dashboard page");
        log.trace("Trace message from the dashboard page");
    }
}

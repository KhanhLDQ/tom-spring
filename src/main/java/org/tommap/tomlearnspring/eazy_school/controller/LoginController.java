package org.tommap.tomlearnspring.eazy_school.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class LoginController {
    @RequestMapping(path = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String displayLoginPage(
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout,
            Model model
    ) {
        String errorMsg = null;
        if (null != error) {
            errorMsg = "Username or password is incorrect";
        }

        if (null != logout) {
            errorMsg = "You have been successfully logged out";
        }

        model.addAttribute("errorMsg", errorMsg);
        return "login.html";
    }
}

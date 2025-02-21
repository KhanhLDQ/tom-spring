package org.tommap.tomlearnspring.eazy_school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @RequestMapping("/contact")
    public String displayContactPage() {
        return "contact.html";
    }

    @RequestMapping(path = "/saveMsg", method = POST)
    public ModelAndView saveMsg(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "mobileNum") String mobileNumber,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "subject") String subject,
            @RequestParam(name = "message") String message
    ) {
        logger.info("Name: " + name);
        logger.info("Mobile Number: " + mobileNumber);
        logger.info("Email: " + email);
        logger.info("Subject: " + subject);
        logger.info("Message: " + message);

        return new ModelAndView("redirect:/contact"); //club model data and view information
    }
}

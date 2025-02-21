package org.tommap.tomlearnspring.eazy_school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tommap.tomlearnspring.eazy_school.model.Contact;
import org.tommap.tomlearnspring.eazy_school.service.IContactService;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ContactController {
    private final IContactService contactService;

    @Autowired
    public ContactController(IContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage() {
        return "contact.html";
    }

    @RequestMapping(path = "/saveMsg", method = POST)
    public ModelAndView saveMsg(
            Contact contact
    ) {
        contactService.saveMsgDetails(contact);
        return new ModelAndView("redirect:/contact"); //club model data and view information
    }
}

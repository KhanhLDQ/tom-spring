package org.tommap.tomlearnspring.eazy_school.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.tommap.tomlearnspring.eazy_school.model.Contact;
import org.tommap.tomlearnspring.eazy_school.service.IContactService;

@Controller
@Slf4j
public class ContactController {
    private final IContactService contactService;

    @Autowired
    public ContactController(IContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact")
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    @PostMapping("/saveMsg")
    public String saveMsg(
            @Valid @ModelAttribute("contact") Contact contact,
            Errors errors
    ) {
        if (errors.hasErrors()) {
            log.error("contact form validation failed due to: {}", errors);
            return "contact.html";
        }

        contactService.saveMsgDetails(contact);

        contactService.setCounter(contactService.getCounter() + 1);
        log.info("number of times contact form submitted: {}", contactService.getCounter());

        return "redirect:/contact";
    }
}

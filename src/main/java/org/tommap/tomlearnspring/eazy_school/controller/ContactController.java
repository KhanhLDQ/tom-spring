package org.tommap.tomlearnspring.eazy_school.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.tommap.tomlearnspring.eazy_school.model.Contact;
import org.tommap.tomlearnspring.eazy_school.service.IContactService;

import java.util.List;

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
        return "redirect:/contact";
    }

    @GetMapping("/displayMessages/page/{pageNum}")
    public ModelAndView displayMessages(
            Model model,
            @PathVariable(name = "pageNum") int pageNumber,
            @RequestParam(name = "sortField") String sortField,
            @RequestParam(name = "sortDir") String sortDir
    ) {
        //only for testing log level
        log.debug("requested page number: {}, sort field: {}, sort direction: {}", pageNumber, sortField, sortDir);

        Page<Contact> msgPage = contactService.findMessagesWithOpenStatus(pageNumber, sortField, sortDir);
        List<Contact> contactMessages = msgPage.getContent();

        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject("contactMessages", contactMessages);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", msgPage.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return modelAndView;
    }

    @GetMapping("/closeMsg")
    public String closeMsg(@RequestParam("id") int contactId) {
        contactService.updateMsgStatus(contactId);
        return "redirect:/displayMessages/page/1?sortField=name&sortDir=asc";
    }
}

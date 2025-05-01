package org.tommap.tomlearnspring.eazy_school.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tommap.tomlearnspring.eazy_school.model.Contact;
import org.tommap.tomlearnspring.eazy_school.repository.ContactRepository;

import java.util.List;

@Controller
@RequestMapping("/v1/api/rest-contact")
@RequiredArgsConstructor
@Slf4j
public class ContactRestController {
    private final ContactRepository contactRepository;

    @GetMapping("/get-messages-by-status")
    @ResponseBody
    public List<Contact> getMessagesByStatus(
        @RequestParam(name = "status") String status
    ) {
        return contactRepository.findByStatus(status);
    }

    /*
        - not recommended -> GET endpoints should not accept a request body / use path variables or query params instead
        - only used to demo @RequestBody
     */
    @GetMapping("/get-messages-by-contact-object")
    @ResponseBody
    public List<Contact> getMessagesByContactObject(@RequestBody Contact contact) {
        if (null == contact) return List.of();
        return contactRepository.findByStatus(contact.getStatus());
    }
}

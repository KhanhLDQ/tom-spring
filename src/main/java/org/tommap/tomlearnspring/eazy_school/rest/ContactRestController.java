package org.tommap.tomlearnspring.eazy_school.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tommap.tomlearnspring.eazy_school.model.ApiResponse;
import org.tommap.tomlearnspring.eazy_school.model.Contact;
import org.tommap.tomlearnspring.eazy_school.repository.ContactRepository;

import java.util.List;

import static org.tommap.tomlearnspring.eazy_school.constants.EazySchoolConstants.CLOSE;
import static org.tommap.tomlearnspring.eazy_school.constants.EazySchoolConstants.OPEN;

@RestController
@RequestMapping(
    path = "/v1/api/rest-contact",
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
)
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*") //not recommended on production - please define specified domain
public class ContactRestController {
    private final ContactRepository contactRepository;

    @GetMapping("/get-messages-by-status")
    public ResponseEntity<ApiResponse<List<Contact>>> getMessagesByStatus(
        @RequestParam(name = "status") String status
    ) {
        List<Contact> contacts = contactRepository.findByStatus(status);
        ApiResponse<List<Contact>> resp = ApiResponse.<List<Contact>>builder()
                .code(200)
                .message("Get Messages By Status Successfully!")
                .data(contacts)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    /*
        - not recommended -> GET endpoints should not accept a request body / use path variables or query params instead
        - only used to demo @RequestBody
     */
    @GetMapping("/get-messages-by-contact-object")
    public List<Contact> getMessagesByContactObject(@RequestBody Contact contact) {
        if (null == contact) return List.of();
        return contactRepository.findByStatus(contact.getStatus());
    }

    @PostMapping("/create-message")
    public ResponseEntity<ApiResponse<Void>> createMessage(
        @RequestHeader("invocation-from") String invocationFrom,
        @RequestBody @Valid Contact contact
    ) {
        log.debug("Header Invocation From: [{}]", invocationFrom);

        contact.setStatus(OPEN);
        Contact newSavedContact = contactRepository.save(contact);

        if (newSavedContact.getContactId() > 0) {
            ApiResponse<Void> resp = ApiResponse.<Void>builder()
                    .code(201)
                    .message("Message created successfully!")
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        }

        ApiResponse<Void> resp = ApiResponse.<Void>builder()
                .code(500)
                .message("Something went wrong!")
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
    }

    @DeleteMapping("/delete-message/{messageId}")
    public ResponseEntity<ApiResponse<Void>> deleteMessage(
        @PathVariable(name = "messageId") int id
    ) {
        contactRepository.deleteById(id);
        ApiResponse<Void> resp = ApiResponse.<Void>builder()
                .code(200)
                .message("Message deleted successfully!")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @PatchMapping("/close-message/{messageId}") //partial update
    public ResponseEntity<ApiResponse<Void>> closeMessage(
        @PathVariable(name = "messageId") int id
    ) {
        Contact contact = contactRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contact not found"));

        contact.setStatus(CLOSE);
        contactRepository.save(contact);

        ApiResponse<Void> resp = ApiResponse.<Void>builder()
                .code(200)
                .message("Message closed successfully!")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
}

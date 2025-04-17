package org.tommap.tomlearnspring.eazy_school.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.tommap.tomlearnspring.eazy_school.model.Contact;
import org.tommap.tomlearnspring.eazy_school.repository.ContactRepository;
import org.tommap.tomlearnspring.eazy_school.service.IContactService;

import java.util.List;

import static org.tommap.tomlearnspring.eazy_school.constants.EazySchoolConstants.CLOSE;
import static org.tommap.tomlearnspring.eazy_school.constants.EazySchoolConstants.OPEN;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {
    private final ContactRepository contactRepository;

    public static final int PAGE_SIZE = 5;

    @Override
    public boolean saveMsgDetails(Contact contact) {
        boolean isSaved = false;

        contact.setStatus(OPEN);
        var savedContact = contactRepository.save(contact);

        if (savedContact.getContactId() > 0) {
            isSaved = true;
        }

        return isSaved;
    }

    @Override
    public List<Contact> findMessagesWithOpenStatus() {
        return contactRepository.findByStatus(OPEN);
    }

    @Override
    public boolean updateMsgStatus(int contactId) {
        boolean isUpdated = false;

//        var contact = contactRepository.findById(contactId)
//                .orElseThrow(() -> new RuntimeException("Contact not found"));
//
//        contact.setStatus(CLOSE);
//        var updatedContact = contactRepository.save(contact);
//
//        if (null != updatedContact.getUpdatedBy()) {
//            isUpdated = true;
//        }

        int rowsAffected = contactRepository.updateStatusById(CLOSE, contactId);

//        int rowsAffected = contactRepository.updateMessageStatus(CLOSE, contactId);
//        int rowsAffected = contactRepository.updateMessageStatusNative(CLOSE, contactId);

        if (rowsAffected > 0) {
            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public Page<Contact> findMessagesWithOpenStatus(int pageNumber, String sortField, String sortDir) {
        Sort sort = sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, PAGE_SIZE, sort);

        return contactRepository.findByStatus(OPEN, pageable);

//        return contactRepository.findByStatusWithJPQL(OPEN, pageable);
//        return contactRepository.findByStatusWithNativeQuery(OPEN, pageable);
//        return contactRepository.findOpenMessages(OPEN, pageable);
//        return contactRepository.findOpenMessagesNative(OPEN, pageable);
    }
}

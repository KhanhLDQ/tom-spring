package org.tommap.tomlearnspring.eazy_school.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tommap.tomlearnspring.eazy_school.constants.EazySchoolConstants;
import org.tommap.tomlearnspring.eazy_school.model.Contact;
import org.tommap.tomlearnspring.eazy_school.repository.ContactRepository;
import org.tommap.tomlearnspring.eazy_school.service.IContactService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {
    private final ContactRepository contactRepository;

    @Override
    public boolean saveMsgDetails(Contact contact) {
        boolean isSaved = false;

        contact.setStatus(EazySchoolConstants.OPEN);
        contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());

        var savedContact = contactRepository.save(contact);
        if (savedContact.getContactId() > 0) {
            isSaved = true;
        }

        return isSaved;
    }

    @Override
    public List<Contact> findMessagesWithOpenStatus() {
        return contactRepository.findByStatus(EazySchoolConstants.OPEN);
    }

    @Override
    public boolean updateMsgStatus(int contactId, String updatedBy) {
        boolean isUpdated = false;

        var contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        contact.setStatus(EazySchoolConstants.CLOSE);
        contact.setUpdatedBy(updatedBy);
        contact.setUpdatedAt(LocalDateTime.now());

        var updatedContact = contactRepository.save(contact);
        if (null != updatedContact.getUpdatedBy()) {
            isUpdated = true;
        }

        return isUpdated;
    }
}

package org.tommap.tomlearnspring.eazy_school.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tommap.tomlearnspring.eazy_school.constants.EazySchoolConstants;
import org.tommap.tomlearnspring.eazy_school.model.Contact;
import org.tommap.tomlearnspring.eazy_school.repository.ContactRepository;
import org.tommap.tomlearnspring.eazy_school.service.IContactService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {
    private final ContactRepository contactRepository;

    @Override
    public boolean saveMsgDetails(Contact contact) {
        boolean isSaved = false;

        contact.setStatus(EazySchoolConstants.OPEN);
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
    public boolean updateMsgStatus(int contactId) {
        boolean isUpdated = false;

        var contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        contact.setStatus(EazySchoolConstants.CLOSE);
        var updatedContact = contactRepository.save(contact);

        if (null != updatedContact.getUpdatedBy()) {
            isUpdated = true;
        }

        return isUpdated;
    }
}

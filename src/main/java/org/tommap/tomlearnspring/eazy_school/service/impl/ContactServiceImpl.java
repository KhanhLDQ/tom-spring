package org.tommap.tomlearnspring.eazy_school.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tommap.tomlearnspring.eazy_school.constants.EazySchoolConstants;
import org.tommap.tomlearnspring.eazy_school.model.Contact;
import org.tommap.tomlearnspring.eazy_school.repository.IContactRepository;
import org.tommap.tomlearnspring.eazy_school.service.IContactService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {
    private final IContactRepository contactRepository;

    @Override
    public boolean saveMsgDetails(Contact contact) {
        boolean isSaved = false;

        contact.setStatus(EazySchoolConstants.OPEN);
        contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());

        int result = contactRepository.saveContactMsg(contact);
        if (result > 0) {
            isSaved = true;
        }

        return isSaved;
    }

    @Override
    public List<Contact> findMessagesWithOpenStatus() {
        return contactRepository.findMessagesWithStatus(EazySchoolConstants.OPEN);
    }

    @Override
    public boolean updateMsgStatus(int contactId, String updatedBy) {
        boolean isUpdated = false;
        int result = contactRepository.updateMsgStatus(contactId, EazySchoolConstants.CLOSE, updatedBy);

        if (result > 0) {
            isUpdated = true;
        }

        return isUpdated;
    }
}

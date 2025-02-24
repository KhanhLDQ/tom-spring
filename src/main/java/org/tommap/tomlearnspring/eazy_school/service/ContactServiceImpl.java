package org.tommap.tomlearnspring.eazy_school.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tommap.tomlearnspring.eazy_school.model.Contact;

@Service
@Slf4j
public class ContactServiceImpl implements IContactService {
    @Override
    public boolean saveMsgDetails(Contact contact) {
        boolean isSaved = false;
        log.info(contact.toString());

        return isSaved;
    }
}

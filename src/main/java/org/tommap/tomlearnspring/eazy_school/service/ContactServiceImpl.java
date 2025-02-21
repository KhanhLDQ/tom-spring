package org.tommap.tomlearnspring.eazy_school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tommap.tomlearnspring.eazy_school.model.Contact;

@Service
public class ContactServiceImpl implements IContactService {
    private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

    @Override
    public boolean saveMsgDetails(Contact contact) {
        boolean isSaved = false;
        //TODO: need to persist the data into the database table
        logger.info(contact.toString());

        return isSaved;
    }
}

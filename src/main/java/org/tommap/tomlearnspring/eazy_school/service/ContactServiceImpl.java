package org.tommap.tomlearnspring.eazy_school.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.tommap.tomlearnspring.eazy_school.model.Contact;

@Service
@Slf4j
@Getter @Setter
//@RequestScope
//@SessionScope
@ApplicationScope
public class ContactServiceImpl implements IContactService {
    private int counter = 0;

    public ContactServiceImpl() {
        log.info("ContactServiceImpl instance created");
    }

    @Override
    public boolean saveMsgDetails(Contact contact) {
        boolean isSaved = false;
        log.info(contact.toString());

        return isSaved;
    }
}

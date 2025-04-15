package org.tommap.tomlearnspring.eazy_school.service;

import org.springframework.data.domain.Page;
import org.tommap.tomlearnspring.eazy_school.model.Contact;

import java.util.List;

public interface IContactService {
    boolean saveMsgDetails(Contact contact);
    List<Contact> findMessagesWithOpenStatus();
    boolean updateMsgStatus(int contactId);
    Page<Contact> findMessagesWithOpenStatus(int pageNumber, String sortField, String sortDir);
}

package org.tommap.tomlearnspring.eazy_school.repository;

import org.tommap.tomlearnspring.eazy_school.model.Contact;

import java.util.List;

public interface IContactRepository {
    int saveContactMsg(Contact contact);
    List<Contact> findMessagesWithStatus(String status);
    int updateMsgStatus(int contactId, String status, String updatedBy);
}

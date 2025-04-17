package org.tommap.tomlearnspring.eazy_school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tommap.tomlearnspring.eazy_school.model.Contact;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findByStatus(String status);
    Page<Contact> findByStatus(String status, Pageable pageable);
    @Query(value = "SELECT c FROM Contact c WHERE c.status = :status")
    Page<Contact> findByStatusWithJPQL(String status, Pageable pageable);

    @Query(
            value = "SELECT * FROM contact_msg cm WHERE cm.status = :status",
            countQuery = "SELECT COUNT(*) FROM contact_msg cm WHERE cm.status = :status",
            nativeQuery = true
    )
    Page<Contact> findByStatusWithNativeQuery(String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
    int updateStatusById(String status, int id); //not automatically update auditing columns
}

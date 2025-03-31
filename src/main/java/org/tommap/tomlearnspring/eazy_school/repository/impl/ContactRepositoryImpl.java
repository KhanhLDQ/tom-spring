package org.tommap.tomlearnspring.eazy_school.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.tommap.tomlearnspring.eazy_school.model.Contact;
import org.tommap.tomlearnspring.eazy_school.repository.IContactRepository;
import org.tommap.tomlearnspring.eazy_school.rowmappers.ContactRowMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContactRepositoryImpl implements IContactRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int saveContactMsg(Contact contact) {
        String sql = "INSERT INTO contact_msg (name, mobile_num, email, subject, message, status, created_at, created_by) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                contact.getName(), contact.getMobileNum(), contact.getEmail(),
                contact.getSubject(), contact.getMessage(), contact.getStatus(),
                contact.getCreatedAt(), contact.getCreatedBy()
        );
    }

    @Override
    public List<Contact> findMessagesWithStatus(String status) {
        String sql = "SELECT * FROM contact_msg WHERE status = ?";

        return jdbcTemplate.query(sql, ps -> ps.setString(1, status), new ContactRowMapper());
    }

    @Override
    public int updateMsgStatus(int contactId, String status, String updatedBy) {
        String sql = "UPDATE contact_msg SET status = ?, updated_by = ?, updated_at = ? WHERE contact_id = ?";

        return jdbcTemplate.update(sql, ps -> {
            ps.setString(1, status);
            ps.setString(2, updatedBy);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(4, contactId);
        });
    }
}

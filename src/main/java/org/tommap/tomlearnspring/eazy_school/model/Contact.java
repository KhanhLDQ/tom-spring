package org.tommap.tomlearnspring.eazy_school.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.SqlResultSetMappings;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contact_msg")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SqlResultSetMapping.count", columns = @ColumnResult(name = "cnt"))
})
/*
    - spring data JPA does not support dynamic sorting for @NamedQueries | @NamedNativeQueries
 */
@NamedQueries({ //support JPQL
        @NamedQuery(name = "Contact.findOpenMessages", query = "SELECT c FROM Contact c WHERE c.status = :status"),
        @NamedQuery(name = "Contact.updateMessageStatus", query = "UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
})
@NamedNativeQueries({ //support native SQL
        @NamedNativeQuery(
                name = "Contact.findOpenMessagesNative",
                query = "SELECT * FROM contact_msg cm WHERE cm.status = :status",
                resultClass = Contact.class
        ),
        @NamedNativeQuery(
                name = "Contact.findOpenMessagesNative.count",
                query = "SELECT COUNT(*) AS cnt FROM contact_msg cm WHERE cm.status = :status",
                resultSetMapping = "SqlResultSetMapping.count"
        ),
        @NamedNativeQuery(
                name = "Contact.updateMessageStatusNative",
                query = "UPDATE contact_msg cm SET cm.status = ?1 WHERE cm.contact_id = ?2"
        )
})
public class Contact extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("contact_id")
    private int contactId;

    @NotBlank(message = "name must not be blank")
    @Size(min = 3, message = "name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "mobile number must not be blank")
    @Pattern(regexp = "(^$|\\d{10})", message = "mobile number must be 10 digits")
    @JsonProperty("mobile_number")
    private String mobileNum;

    @NotBlank(message = "email must not be blank")
    @Email(message = "please provide a valid email address")
    private String email;

    @NotBlank(message = "subject must not be blank")
    @Size(min = 5, message = "subject must be at least 5 characters long")
    private String subject;

    @NotBlank(message = "message must not be blank")
    @Size(min = 10, message = "message must be at least 10 characters long")
    private String message;

    private String status;
}

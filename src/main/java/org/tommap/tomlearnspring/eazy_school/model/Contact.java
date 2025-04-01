package org.tommap.tomlearnspring.eazy_school.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Contact extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;

    @NotBlank(message = "name must not be blank")
    @Size(min = 3, message = "name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "mobile number must not be blank")
    @Pattern(regexp = "(^$|\\d{10})", message = "mobile number must be 10 digits")
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

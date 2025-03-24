package org.tommap.tomlearnspring.eazy_school.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class Contact extends BaseEntity {
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

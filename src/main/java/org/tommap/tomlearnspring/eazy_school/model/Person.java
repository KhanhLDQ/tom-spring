package org.tommap.tomlearnspring.eazy_school.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tommap.tomlearnspring.eazy_school.annotations.FieldsMatching;
import org.tommap.tomlearnspring.eazy_school.annotations.PasswordStrength;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldsMatching(
        field = "email",
        fieldMatch = "confirmEmail",
        message = "Email addresses do not match!"
)
@FieldsMatching(
        field = "pwd",
        fieldMatch = "confirmPwd",
        message = "Passwords do not match!"
)
public class Person extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @NotBlank(message = "name must not be blank")
    @Size(min = 3, message = "name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "mobile number must not be blank")
    @Pattern(regexp = "(^$|\\d{10})", message = "mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message = "email must not be blank")
    @Email(message = "please provide a valid email address")
    private String email;

    @NotBlank(message = "confirm email must not be blank")
    @Email(message = "please provide a valid email address")
    @Transient //not to be persisted in database
    private String confirmEmail;

    @NotBlank(message = "password must not be blank")
    @Size(min = 5, message = "password must be at least 5 characters long")
    @PasswordStrength
    private String pwd;

    @NotBlank(message = "confirm password must not be blank")
    @Size(min = 5, message = "confirm password must be at least 5 characters long")
    @Transient
    private String confirmPwd;
}

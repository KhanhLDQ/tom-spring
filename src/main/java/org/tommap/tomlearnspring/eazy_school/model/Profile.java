package org.tommap.tomlearnspring.eazy_school.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Profile {
    @NotBlank(message = "name must not be blank")
    @Size(min = 3, message = "name must be at least 3 characters")
    private String name;

    @NotBlank(message = "mobile number must not be blank")
    @Pattern(regexp = "(^$|\\d{10})", message = "mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message = "email must not be blank")
    @Email(message = "please provide a valid email address")
    private String email;

    @NotBlank(message = "address I must not be blank")
    @Size(min = 5, message = "address I must be at least 5 characters long")
    private String address1;

    private String address2;

    @NotBlank(message = "city must not be blank")
    @Size(min = 5, message = "city must be at least 5 characters long")
    private String city;

    @NotBlank(message = "state must not be blank")
    @Size(min = 5, message = "state must be at least 5 characters long")
    private String state;

    @NotNull(message = "zip code must not be null")
    @Pattern(regexp = "(^$|\\d{5})", message = "zip code must be 5 digits")
    private String zipCode;
}

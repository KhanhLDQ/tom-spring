package org.tommap.tomlearnspring.eazy_school.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

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
    private int zipCode;
}

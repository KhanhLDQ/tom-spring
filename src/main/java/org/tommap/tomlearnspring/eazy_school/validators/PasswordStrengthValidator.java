package org.tommap.tomlearnspring.eazy_school.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.tommap.tomlearnspring.eazy_school.annotations.PasswordStrength;

import java.util.Arrays;
import java.util.List;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordStrength, String> {
    List<String> weakPasswords;

    @Override
    public void initialize(PasswordStrength constraintAnnotation) {
        weakPasswords = Arrays.asList("12345", "password", "qwerty");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return null != value && !weakPasswords.contains(value);
    }
}

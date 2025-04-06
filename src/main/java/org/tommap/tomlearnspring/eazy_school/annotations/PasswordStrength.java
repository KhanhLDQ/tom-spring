package org.tommap.tomlearnspring.eazy_school.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.tommap.tomlearnspring.eazy_school.validators.PasswordStrengthValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordStrength {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String message() default "Please choose a strong password!";
}

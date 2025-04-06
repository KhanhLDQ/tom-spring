package org.tommap.tomlearnspring.eazy_school.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.tommap.tomlearnspring.eazy_school.validators.FieldsMatchingValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FieldsMatchingValidator.class)
@Target({ElementType.TYPE}) //class | interface | enum declaration
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(FieldsMatching.List.class)
public @interface FieldsMatching {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String message() default "Fields values don't match!";

    //only needed when comparing two fields
    String field();
    String fieldMatch();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List { //allow to use multiple annotations on the same class
        FieldsMatching[] value();
    }
}

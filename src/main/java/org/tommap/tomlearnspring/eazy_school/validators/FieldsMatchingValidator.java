package org.tommap.tomlearnspring.eazy_school.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import org.tommap.tomlearnspring.eazy_school.annotations.FieldsMatching;

public class FieldsMatchingValidator implements ConstraintValidator<FieldsMatching, Object> {
    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldsMatching constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);

        if (null == fieldValue || null == fieldMatchValue) {
            return false;
        }

        return fieldValue.equals(fieldMatchValue);
    }
}

package com.example.produmano.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    String message() default "Некорректный формат номера телефона. Используйте формат +7 123 456-78-90";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

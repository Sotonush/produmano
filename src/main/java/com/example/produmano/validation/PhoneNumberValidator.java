package com.example.produmano.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private static final String PHONE_REGEX = "^\\+7 \\d{3} \\d{3}-\\d{2}-\\d{2}$";
    private static final Pattern PATTERN = Pattern.compile(PHONE_REGEX);

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        return phone != null && PATTERN.matcher(phone).matches();
    }
}
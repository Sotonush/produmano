package com.example.produmano.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private static final String PHONE_REGEX = "^\\+7\\d{10}$";
    private static final Pattern PATTERN = Pattern.compile(PHONE_REGEX);

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if (phone == null) {
            return false;
        }

        String normalizedPhone = phone.replaceAll("[^\\d+]", "");

        System.out.println("📞 Проверка номера: " + phone + " → " + normalizedPhone);

        return PATTERN.matcher(normalizedPhone).matches();
    }
}

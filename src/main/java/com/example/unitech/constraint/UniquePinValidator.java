package com.example.unitech.constraint;

import com.example.unitech.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniquePinValidator implements ConstraintValidator<UniquePin,String> {
    private final UserRepository userRepository;

    public UniquePinValidator( UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniquePin constraintAnnotation) {
        // Initialize logic, if needed
    }

    @Override
    public boolean isValid(String pin, ConstraintValidatorContext context) {
        boolean isPinUnique = userRepository.findByPin(pin) == null;
        return isPinUnique;
    }
}

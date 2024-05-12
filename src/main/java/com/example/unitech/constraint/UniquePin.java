package com.example.unitech.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniquePinValidator.class)
@Size(min = 4,message = " Length of pin must be at least 4 characters")
@NotBlank(message = "Please enter pin")
@Documented
public @interface UniquePin {

    String message() default "Pin is already in use";
    Class <?>[] groups() default {};
    Class<?extends Payload>[] payload() default{};
}

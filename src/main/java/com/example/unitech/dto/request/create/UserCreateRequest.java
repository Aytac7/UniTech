package com.example.unitech.dto.request.create;
import com.example.unitech.constraint.UniquePin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserCreateRequest {

    @UniquePin
    String pin;
    @Email(message = "Please provide a valid email address")
    String email;
    @NotBlank(message ="Please enter username" )
    String username;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$", message = "Password must be at least 8 characters long and contain at least one digit, one lowercase letter, one uppercase letter, and one special character.")
    String password;


}

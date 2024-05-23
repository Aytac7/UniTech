package com.example.unitech.dto.request.login;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForgotPasswordRequest {

    private String email;
    private String pin;
    

}

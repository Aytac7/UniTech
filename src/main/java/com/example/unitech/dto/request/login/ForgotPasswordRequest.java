package com.example.unitech.dto.request.login;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ForgotPasswordRequest {

    private String email;

}

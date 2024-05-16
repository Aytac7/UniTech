package com.example.unitech.dto.response.create;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateResponse {

    String pin;
    String email;
    String username;
    String password;
}

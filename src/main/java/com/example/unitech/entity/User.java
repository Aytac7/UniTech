package com.example.unitech.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "users_info")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String pin;
    String email;
    String username;
    String password;
}

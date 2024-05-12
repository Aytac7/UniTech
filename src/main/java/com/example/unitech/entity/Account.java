package com.example.unitech.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "accounts")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String status;
    Long fkUserId;
    BigDecimal balance;
    Long accountNumber;

    @PrePersist
    public void pre(){
        if(status==null) {
            status = "A";
        }
    }

}

package com.example.unitech.entity;

import com.example.unitech.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.unitech.enums.Status.ACTIVE;


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
    @Enumerated(EnumType.STRING)
    Status status;
    Long fkUserId;
    BigDecimal balance;
    Long accountNumber;

    @PrePersist
    public void pre(){
        if(status==null) {
            status = ACTIVE;
        }
    }

}

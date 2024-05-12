package com.example.unitech.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "currencies")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Currency {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String currencyType;
    Double rate;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd-mm-yyyy'T'HH:mm")
    private LocalDateTime updatedDate;

}
